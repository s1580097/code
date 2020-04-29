import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.csvreader.CsvReader;

public class LeerFichero {

	public static Map<String, List<String>> construyeMapa(String archivo) throws FileNotFoundException, IOException {
		// public static void construyeMapa(String archivo) throws
		// FileNotFoundException, IOException{
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		String cadena;
		String codigo = null;
		String description = null;

		String textoBuscado = ";";
		String codigoSinPunto;

		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			codigo = cadena.substring(0, cadena.indexOf(textoBuscado));

			// eliminar punto
			codigoSinPunto = codigo.replace(".", "");
			if (!mapa.containsKey(codigoSinPunto)) {
				mapa.put(codigoSinPunto, new ArrayList());
			}

			// Preprocessing
			description = cadena.substring(cadena.indexOf(textoBuscado) + 1, cadena.length());
			description = cleanSpaces(description);
			// description = removeCharacters(description);
			description = setLowCase(description);
			mapa.get(codigoSinPunto).add(description);
			// mapa.put(codigoSinPunto, descripcion);
			// System.out.println("Description: " + descripcion);
		}

		b.close();
		// Muestra el mapa
		/*
		 * Iterator it = mapa.keySet().iterator(); while (it.hasNext()) { String code =
		 * (String) it.next(); Iterator itList = mapa.get(code).iterator(); for (int i =
		 * 0; i < mapa.get(code).size(); i++) {
		 * 
		 * System.out.println("Codigo: " + code + " -> Description: " +
		 * mapa.get(code).get(i));
		 * 
		 * }
		 * 
		 * }
		 */

		return mapa;
	}

	public static void comparaMapas(Map<String, List<String>> a, Map<String, List<String>> b) {

		FileWriter ficheroIguales = null;
		FileWriter ficheroDistintos = null;
		FileWriter ficheroDistintos1word = null;
		FileWriter ficheroDistintos2words = null;
		FileWriter ficheroDistintos3words = null;
		FileWriter ficheroDistintosMoreThan3words = null;
		PrintWriter pw = null;
		PrintWriter pw1 = null;
		PrintWriter pw2 = null;
		PrintWriter pw3 = null;
		PrintWriter pw4 = null;
		PrintWriter pw5 = null;
		int total = 0;
		int iguales = 0;
		int distintos = 0;
		int diff1word = 0;
		int diff2words = 0;
		int diff3words = 0;
		int diffMoreThan3words = 0;
		int flag = 0;

		List<String> wordsDescription1 = new ArrayList<String>();
		List<String> wordsDescription2 = new ArrayList<String>();
		List<String> auxDifferentWords = new ArrayList<String>();
		try {

			ficheroIguales = new FileWriter("iguales.csv");
			pw = new PrintWriter(ficheroIguales);
			ficheroDistintos = new FileWriter("distintos.csv");
			pw1 = new PrintWriter(ficheroDistintos);

			ficheroDistintos1word = new FileWriter("distintos1word.csv");
			pw2 = new PrintWriter(ficheroDistintos1word);
			ficheroDistintos2words = new FileWriter("distintos2words.csv");
			pw3 = new PrintWriter(ficheroDistintos2words);
			ficheroDistintos3words = new FileWriter("distintos3words.csv");
			pw4 = new PrintWriter(ficheroDistintos3words);
			ficheroDistintosMoreThan3words = new FileWriter("distintosMoreThan3words.csv");
			pw5 = new PrintWriter(ficheroDistintosMoreThan3words);

			String codigoICD10;
			Iterator it = a.keySet().iterator();

			while (it.hasNext()) {
				String code = (String) it.next();
				for (int i = 0; i < a.get(code).size(); i++) {
					total++;
					if (!b.containsKey(code)) {
						codigoICD10 = "empty";
					} else {
						codigoICD10 = b.get(code).get(0);
						if (a.get(code).get(i).equals(b.get(code).get(0))) { // Exactly the same
							pw.println(code + "; " + a.get(code).get(i));
							iguales++;
						} else {
							// See if they have different order
							wordsDescription1 = extractWords(a.get(code).get(i));
							wordsDescription2 = extractWords(b.get(code).get(0));

							if (compareListsOfWords(wordsDescription1, wordsDescription2)) {
								pw.println(code + "; " + a.get(code).get(i));
								iguales++;
							} else {
								// it is necessary to differentiate if the descriptions has the same number of
								// words or not. 
								// I have to limit the number of words in order to get descriptions with no more than 5-6 words
								if ((numberWords(wordsDescription1)<6) || (numberWords(wordsDescription2)<6)) {
									auxDifferentWords = extractDifferentWords(wordsDescription1, wordsDescription2);

									switch (auxDifferentWords.size()) {
									case 4:
										pw2.println(code + "(DSM-5); " + a.get(code).get(i));
										pw2.println(code + "(ICD-10); " + codigoICD10);
										pw2.println();
										diff1word++;
										break;
									case 5:
										pw3.println(code + "(DSM-5); " + a.get(code).get(i));
										pw3.println(code + "(ICD-10); " + codigoICD10);
										pw3.println();
										diff2words++;
										break;
									case 6:
										pw4.println(code + "(DSM-5); " + a.get(code).get(i));
										pw4.println(code + "(ICD-10); " + codigoICD10);
										pw4.println();
										diff3words++;
										break;

									default:
										pw5.println(code + "(DSM-5); " + a.get(code).get(i));
										pw5.println(code + "(ICD-10); " + codigoICD10);
										pw5.println();
										diffMoreThan3words++;
										break;
									}
								

								// Escribir fichero distintos
								pw1.println(code + "(DSM-5); " + a.get(code).get(i));
								pw1.println(code + "(ICD-10); " + codigoICD10);
								pw1.println();
								distintos++;
								}
							}
						}

					}

					// System.out.println(code + "(DSM-5); " +
					// a.get(code).get(i));
					// System.out.println(code + "(ICD-10); " +
					// b.get(code).get(0));
					// System.out.println("Número total de registros:" + total);
					// System.out.println("Número de registros iguales:" +
					// iguales);
					// System.out.println("Número de registros distintos:" +
					// distintos);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ficheroIguales.close();
			ficheroDistintos.close();
			ficheroDistintos1word.close();
			ficheroDistintos2words.close();
			ficheroDistintos3words.close();
			ficheroDistintosMoreThan3words.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Número total de registros:" + total);
		System.out.println("Número de registros iguales:" + iguales);
		System.out.println("Número de registros distintos:" + distintos);
		System.out.println("Número de registros distintos en 1 palabra:" + diff1word);
		System.out.println("Número de registros distintos en 2 palabras:" + diff2words);
		System.out.println("Número de registros distintos en 3 palabras:" + diff3words);
		System.out.println("Número de registros distintos en más de 3 palabras:" + diffMoreThan3words);

	}

	public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
		}
		b.close();
	}

	/**
	 * 
	 * @param listWords
	 *            List of words contained in the description of a disease
	 * @return Number of words in a given description (nodes of the list)
	 */
	public static int numberWords(List<String> listWords) {

		return listWords.size();
	}

	/**
	 * 
	 * @param description
	 *            Description of disease
	 * @return Number of words in the given description
	 */
	public static int numberWords(String description) {

		int words = 0;

		for (int i = 0; i < description.length(); i++) {
			if (description.charAt(i) == ' ') {
				words++;
			}
		}
		if (description.charAt(description.length() - 1) == ' ')
			words--;
		if ((words != 0) && (description.charAt(0) != ' '))
			words++;

		return words;
	}

	/**
	 * The method removes the spaces located at the beginning and the end of the
	 * description
	 * 
	 * @param description
	 */
	public static String cleanSpaces(String description) {

		String cleanDescription;

		if (description.length() == 0) {
			cleanDescription = description;
		} else {

			while (description.charAt(0) == ' ')
				description = description.substring(1, description.length());
			while (description.charAt(description.length() - 1) == ' ')
				description = description.substring(0, description.length() - 1);
			cleanDescription = description;
		}
		return cleanDescription;

	}

	/**
	 * This method replace every symbol for a *
	 * 
	 * @param description
	 *            The description of the given disease
	 * @return The description with * instead of other symbols
	 */
	public static String removeCharacters(String description) {

		// return description.replaceAll("[^\\dA-Za-z ]", "*");
		return description.replaceAll("/", "-");

	}

	/**
	 * This method sets all description's characters in low case
	 * 
	 * @param description
	 *            The description of the given disease
	 * @return The description in low case
	 */
	public static String setLowCase(String description) {
		return description.toLowerCase();
	}

	/**
	 * 
	 * @param description
	 *            Description of a given disease
	 * @return List of Strings, with a word per node
	 */
	public static List<String> extractWords(String description) {
		List<String> list = new ArrayList<String>();
		// Now, I am considering only the spaces.
		// It is necessary to clean symbols and case sensitive
		String word;
		if (description.charAt(0) != ' ') {
			String cleanDescription = cleanSpaces(description);
			int beginIndex = 0;
			int endIndex;

			for (int i = 0; i < cleanDescription.length(); i++) {
				if (cleanDescription.charAt(i) == ' ') {
					endIndex = i;
					word = cleanDescription.substring(beginIndex, endIndex);
					list.add(word);
					beginIndex = i + 1;
				}
			}
			endIndex = cleanDescription.length();
			word = cleanDescription.substring(beginIndex, endIndex);

		} else {
			word = " ";
		}
		list.add(word);

		return list;
	}

	/**
	 * This method compare if the words of one description are in the other
	 * description (This allows subsets)
	 * 
	 * @param wordsDescription1
	 *            Description 1 list of words
	 * @param wordsDescription2
	 *            Description 2 list of words
	 * @return If both lists have the same words, this function returns true, in
	 *         other case, it returns false.
	 */
	public static boolean compareListsOfWords(List<String> wordsDescription1, List<String> wordsDescription2) {

		Iterator<String> iterator = wordsDescription1.iterator();
		boolean flag = false;
		while (iterator.hasNext()) {
			String element = (String) iterator.next();
			if (wordsDescription2.contains(element)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * This method extract the words that are not present in both lists
	 * 
	 * @param wordsDescription1
	 *            List with description1's words
	 * @param wordsDescription2
	 *            List with description2's words
	 * @return List with the words that are not in both lists
	 */
	public static List<String> extractDifferentWords(List<String> wordsDescription1, List<String> wordsDescription2) {

		List<String> differentWords = new ArrayList<String>();

		Iterator<String> iterator = wordsDescription1.iterator();
		while (iterator.hasNext()) {
			String element = (String) iterator.next();
			if (!wordsDescription2.contains(element))
				differentWords.add(element);
		}
		iterator = wordsDescription2.iterator();
		while (iterator.hasNext()) {
			String element = (String) iterator.next();
			if (!wordsDescription1.contains(element))
				differentWords.add(element);
		}
		return differentWords;
	}

}