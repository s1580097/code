package eval;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Evaluation {

	private int total;
	private int tp;
	private int fp;
	private int fn;
	private Map<String, Map<String, String>> goldStandardEquivalence;
	private Map<String, Map<String, String>> goldStandardSubsumption;
	private Map<String, Map<String, String>> matching;

	public Evaluation(String goldStandardEquivalence, String goldStandardSubsumption, String matching) {
		total = 0;
		tp = 0;
		fp = 0;
		fn = 0;
		cargaGoldStandardEquivalence(goldStandardEquivalence);
		cargaGoldStandardSubsumption(goldStandardSubsumption);
		cargaMatching(matching);

	}

	public void cargaGoldStandardEquivalence(String path) {

		goldStandardEquivalence = new HashMap<String, Map<String, String>>();

		String cadena;
		char textoBuscado = '\t';
		FileReader f;
		try {
			f = new FileReader(path);

			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				int ini = 0;
				int campo = 0;
				String[] campos = new String[3];
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.charAt(i) == textoBuscado) {

						campos[campo] = (String) cadena.substring(ini, i);
						ini = i + 1;
						campo++;
					}
				}
				campos[campo] = cadena.substring(ini, cadena.length());
				String comp1 = extractTerm(campos[0]);
				String comp2 = extractTerm(campos[2]);
				String rel = campos[1];
				if (goldStandardEquivalence.containsKey(comp1)) {
					goldStandardEquivalence.get(comp1).put(comp2, rel);
				} else {
					Map<String, String> mapa = new HashMap<String, String>();
					mapa.put(comp2, rel);
					goldStandardEquivalence.put(comp1, mapa);
				}

			}
			b.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargaGoldStandardSubsumption(String path) {

		goldStandardSubsumption = new HashMap<String, Map<String, String>>();

		String cadena;
		char textoBuscado = '\t';
		FileReader f;
		try {
			f = new FileReader(path);

			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				int ini = 0;
				int campo = 0;
				String[] campos = new String[3];
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.charAt(i) == textoBuscado) {

						campos[campo] = (String) cadena.substring(ini, i);
						ini = i + 1;
						campo++;
					}
				}
				campos[campo] = cadena.substring(ini, cadena.length());
				String comp1 = extractTerm(campos[0]);
				String comp2 = extractTerm(campos[2]);
				String rel = campos[1];
				if (goldStandardSubsumption.containsKey(comp1)) {
					goldStandardSubsumption.get(comp1).put(comp2, rel);
				} else {
					Map<String, String> mapa = new HashMap<String, String>();
					mapa.put(comp2, rel);
					goldStandardSubsumption.put(comp1, mapa);
				}

			}
			b.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargaMatching(String path) {

		matching = new HashMap<String, Map<String, String>>();

		String cadena;
		char textoBuscado = '\t';
		FileReader f;
		try {
			f = new FileReader(path);

			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				int ini = 0;
				int campo = 0;
				String[] campos = new String[3];
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.charAt(i) == textoBuscado) {

						campos[campo] = (String) cadena.substring(ini, i);
						ini = i + 1;
						campo++;
					}
				}
				campos[campo] = cadena.substring(ini, cadena.length());
				String comp1 = extractTerm(campos[0]);
				String comp2 = extractTerm(campos[2]);
				String rel = campos[1];
				if (!comp1.equals("Top")) {
					// if (!comp2.equals("Top")) {
					if (matching.containsKey(comp1)) {
						matching.get(comp1).put(comp2, rel);
					} else {
						Map<String, String> mapa = new HashMap<String, String>();
						mapa.put(comp2, rel);
						matching.put(comp1, mapa);
					}
					// }
				}

			}
			b.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int computeFn() {

		fn = total - tp;

		return fn;
	}

	public double computePrecision() {

		double precision;

		precision = (double) tp / (tp + fp);

		return precision;
	}

	public double computeRecall() {

		double recall;

		recall = (double) tp / total;

		return recall;
	}

	public double computeFScore(double precision, double recall) {

		double fScore;

		fScore = (double) 2 / ((1 / recall) + (1 / precision));

		return fScore;
	}

	public String extractTerm(String cadena) {
		String term = "";

		char textoBuscado = '\\';

		int ini = cadena.length() - 1;
		for (int i = ini; i > -1; i--) {
			if (cadena.charAt(i) == textoBuscado) {
				term = (String) cadena.substring(i + 1, ini + 1);
				break;
			}
		}

		return term;
	}

	public void evaluateEquivalence() {

		for (Map.Entry<String, Map<String, String>> entry1 : goldStandardEquivalence.entrySet()) {
			for (Map.Entry<String, String> entry2 : goldStandardEquivalence.get(entry1.getKey()).entrySet()) {
				total++;
				if ((matching.containsKey(entry1.getKey())) && (matching.get(entry1.getKey()).containsKey(entry2.getKey()))) {
					if (matching.get(entry1.getKey()).get(entry2.getKey()).equals(entry2.getValue())) {
						tp++;
						System.out.println("---------------------- " + tp + "----------------------------");
						System.out.println("True positive (matching): " + entry1.getKey() + " "
								+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
						System.out.println("True positive (goldStandard): " + entry1.getKey() + " "
								+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
								+ entry2.getKey());
					} else {
						// Añadido nueva evaluación
						if ((matching.get(entry1.getKey()).get(entry2.getKey()).equals("<"))
								|| ((matching.get(entry1.getKey()).get(entry2.getKey()).equals(">")))) {

							tp++;
							System.out.println("----------------------  New mapping ----------------------------");
							System.out.println("---------------------- " + tp + "----------------------------");
							System.out.println("True positive (matching): " + entry1.getKey() + " "
									+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
							System.out.println("True positive (goldStandard): " + entry1.getKey() + " "
									+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
									+ entry2.getKey());

						}
					}
				}

			}
		}
		for (

		Map.Entry<String, Map<String, String>> entry1 : matching.entrySet()) {
			for (Map.Entry<String, String> entry2 : matching.get(entry1.getKey()).entrySet()) {
				if (entry2.getValue().equals("=")) {
					if (!goldStandardEquivalence.get(entry1.getKey()).containsKey(entry2.getKey())) {
						fp++;
						System.out.println("---------------------- " + fp + "----------------------------");
						System.out.println("False positive (matching): " + entry1.getKey() + " "
								+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
						System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
								+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
								+ entry2.getKey());
					} else {
						if (!goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()).equals("=")) {
							fp++;
							System.out.println("---------------------- " + fp + "----------------------------");
							System.out.println("False positive (matching): " + entry1.getKey() + " "
									+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
							System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
									+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
									+ entry2.getKey());
						}
					}
					// Código nuevo
				} else {
					if (((entry2.getValue().equals("<"))
							|| (entry2.getValue().equals(">"))) && (!entry2.getKey().equals("Top"))) {
						if (!goldStandardEquivalence.get(entry1.getKey()).containsKey(entry2.getKey())) {
							fp++;
							System.out.println("---------------------- " + fp + "----------------------------");
							System.out.println("False positive (matching): " + entry1.getKey() + " "
									+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
							System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
									+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
									+ entry2.getKey());
						} else {
							if (!goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()).equals("=")) {
								fp++;
								System.out.println("---------------------- " + fp + "----------------------------");
								System.out.println("False positive (matching): " + entry1.getKey() + " "
										+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
								System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
										+ goldStandardEquivalence.get(entry1.getKey()).get(entry2.getKey()) + " "
										+ entry2.getKey());
							}
						}
					}
				}

			}
		}

		computeFn();

		double precision = computePrecision();
		double recall = computeRecall();
		double fscore = computeFScore(precision, recall);

		System.out.println("***************** Equivalence ********************");

		printParam();

		printValues(precision, recall, fscore);
		System.out.println("**************************************************");
		resetValues();
	}

	public void evaluateSubsumption() {
		for (Map.Entry<String, Map<String, String>> entry1 : goldStandardSubsumption.entrySet()) {
			for (Map.Entry<String, String> entry2 : goldStandardSubsumption.get(entry1.getKey()).entrySet()) {
				total++;
				if (!entry2.getValue().equals("=")) {
					if (matching.get(entry1.getKey()).containsKey(entry2.getKey())) {
						if (matching.get(entry1.getKey()).get(entry2.getKey()).equals(entry2.getValue())) {
							tp++;
							System.out.println("---------------------- " + tp + "----------------------------");
							System.out.println("True positive (matching): " + entry1.getKey() + " "
									+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
							System.out.println("True positive (goldStandard): " + entry1.getKey() + " "
									+ goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()) + " "
									+ entry2.getKey());
						}
					}
				}

			}
		}
		for (Map.Entry<String, Map<String, String>> entry1 : matching.entrySet()) {
			for (Map.Entry<String, String> entry2 : matching.get(entry1.getKey()).entrySet()) {
				if (!entry2.getKey().equals("Top")) {
					if (!entry2.getValue().equals("=")) {
						if (entry2.getValue().equals("<")) {
							if (!goldStandardSubsumption.get(entry1.getKey()).containsKey(entry2.getKey())) {
								fp++;
								System.out.println("---------------------- " + fp + "----------------------------");
								System.out.println("False positive (matching): " + entry1.getKey() + " "
										+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
								System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
										+ goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()) + " "
										+ entry2.getKey());
							} else {
								if (goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()).equals(">")) {
									fp++;
									System.out.println("---------------------- " + fp + "----------------------------");
									System.out.println("False positive (matching): " + entry1.getKey() + " "
											+ matching.get(entry1.getKey()).get(entry2.getKey()) + " "
											+ entry2.getKey());
									System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
											+ goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()) + " "
											+ entry2.getKey());
								}
							}

						} else {
							if (!goldStandardSubsumption.get(entry1.getKey()).containsKey(entry2.getKey())) {
								fp++;
								System.out.println("---------------------- " + fp + "----------------------------");
								System.out.println("False positive (matching): " + entry1.getKey() + " "
										+ matching.get(entry1.getKey()).get(entry2.getKey()) + " " + entry2.getKey());
								System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
										+ goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()) + " "
										+ entry2.getKey());
							} else {
								if (entry2.getValue().equals(">")) {
									if (goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey()).equals("<")) {
										fp++;
										System.out.println(
												"---------------------- " + fp + "----------------------------");
										System.out.println("False positive (matching): " + entry1.getKey() + " "
												+ matching.get(entry1.getKey()).get(entry2.getKey()) + " "
												+ entry2.getKey());
										System.out.println("False positive (goldStandard): " + entry1.getKey() + " "
												+ goldStandardSubsumption.get(entry1.getKey()).get(entry2.getKey())
												+ " " + entry2.getKey());
									}
								}
							}

						}

					}

				}

			}
		}

		computeFn();
		double precision = computePrecision();
		double recall = computeRecall();
		double fscore = computeFScore(precision, recall);

		System.out.println("***************** Subsumption ********************");
		printParam();
		printValues(precision, recall, fscore);
		System.out.println("**************************************************");
		resetValues();
	}

	public void resetValues() {
		total = 0;
		tp = 0;
		fp = 0;
		fn = 0;
	}

	public void printValues(double precision, double recall, double fscore) {
		System.out.println("Precision: " + precision);
		System.out.println("Recall: " + recall);
		System.out.println("Fscore: " + fscore);
	}

	public void printParam() {
		System.out.println("Total: " + total);
		System.out.println("True positives: " + tp);
		System.out.println("False positives: " + fp);
		System.out.println("False negatives: " + fn);
	}
}
