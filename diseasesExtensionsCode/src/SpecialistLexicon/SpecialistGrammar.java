package SpecialistLexicon;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class SpecialistGrammar {

	HashMap<String, String[]> derivations;

	public SpecialistGrammar() {

		derivations = new HashMap<String, String[]>();
	}

	public void processFile(String path) {

		try {

			File myObj = new File(path);

			Scanner myReader = new Scanner(myObj);

			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();

				if (firstCharNotHashSymbol(line)) {

					String[] parts = line.split("\\|");
					// Extract elements separated by a |
					String elem1 = parts[0];
					String typeElem1 = parts[1];
					String elem2 = parts[3];
					String typeElem2 = parts[4];

					String derivation[] = new String[4];

					if (typeElem1.contentEquals("noun")) {

						derivation[0] = elem1;
						derivation[1] = typeElem1;
						derivation[2] = elem2;
						derivation[3] = formatPos(typeElem2);

						String hash = computeHash(elem1);
						insertDerivation(hash, derivation);
					} else {

						derivation[0] = elem2;
						derivation[1] = typeElem2;
						derivation[2] = elem1;
						derivation[3] = formatPos(typeElem1);

						String hash = computeHash(elem2).toLowerCase();
						insertDerivation(hash, derivation);
					}
				}

			}

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.print("Error occurred reading the file");
			e.printStackTrace();
		}
	}

	private void insertDerivation(String hash, String[] derivation) {

		derivations.put(hash, derivation);

	}

	private String computeHash(String name) {

		name = name.replaceAll("[^A-Za-z0-9]", "");
		return name;
	}

	public boolean firstCharNotHashSymbol(String line) {
		if (line.charAt(0) != '#') {
			return true;
		} else {
			return false;
		}
	}

	public HashMap<String, String[]> getDerivations() {
		return derivations;
	}

	public void setDerivations(HashMap<String, String[]> derivations) {
		this.derivations = derivations;
	}

	
	public String formatPos(String pos) {
		
		String posFormatted="";
		
		switch(pos) {
		case "noun":
			posFormatted = "noun";
			break;
			
		case "adj":
			posFormatted = "adjective";
			break;
		case "adv":	
			posFormatted = "adverb";
			break;
		case "verb":	
			posFormatted = "verb";
			break;
		}
		
		return posFormatted;
		
	}
}
