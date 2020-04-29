package SpecialistLexicon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import specialistLexiconLexicalSemantics.Sense;

public class SpecialistLexiconTree {

	private SpecialistLexiconNormalNode root;
	private Map<String, SpecialistLexiconNormalNode> tree;
	private Map<String, specialistLexiconLexicalSemantics.LexicalEntry> lexicalEntries;
	private Map<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>> synsets;

	public SpecialistLexiconTree() {
		String treeNumber = "rootSpecialistLexicon";
		String id = "SpecialistLexicon";
		String term = "rootSpecialistLexicon";
		root = new SpecialistLexiconNormalNode(treeNumber, id, term, "00001740"); //Location in WN (Entity)
		tree = new HashMap<String, SpecialistLexiconNormalNode>();
		tree.put(treeNumber, root);
		lexicalEntries = new HashMap<String,specialistLexiconLexicalSemantics.LexicalEntry>();
		synsets = new HashMap<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>>();
		
	}
		
	
	public Map<String, specialistLexiconLexicalSemantics.LexicalEntry> getLexicalEntries() {
		return lexicalEntries;
	}


	public void setLexicalEntries(Map<String, specialistLexiconLexicalSemantics.LexicalEntry> lexicalEntries) {
		this.lexicalEntries = lexicalEntries;
	}


	public Map<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>> getSynsets() {
		return synsets;
	}


	public void setSynsets(Map<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>> synsets) {
		this.synsets = synsets;
	}


	public SpecialistLexiconNormalNode getRoot() {
		return root;
	}

	public void setRoot(SpecialistLexiconNormalNode root) {
		this.root = root;
	}

	public Map<String, SpecialistLexiconNormalNode> getTree() {
		return tree;
	}

	public void setTree(Map<String, SpecialistLexiconNormalNode> tree) {
		this.tree = tree;
	}

	public void extractFields(String archivo) {



		String cadena;
		char textoBuscado = '*';
		String content;
		FileReader f;
		try {
			f = new FileReader(archivo);

			BufferedReader b = new BufferedReader(f);
			int cont=0;
			while ((cadena = b.readLine()) != null) {
				int ini = 0;
				int campo = 0;
				String[] campos = new String[6];
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.charAt(i) == textoBuscado) {

						campos[campo] = (String)cadena.substring(ini, i);
						ini = i + 1;
						campo++;
					}
				}
				campos[campo] = cadena.substring(ini, cadena.length());
				// Filtro sólo los nombres
				int typeCat1 = Integer.parseInt(campos[2]);
				int typeCat2 = Integer.parseInt(campos[4]);
				
				if(typeCat1==128 && typeCat2==128) {
					// Añade a lexicalMap
					if(!lexicalEntries.containsValue(campos[0])){
						
						String firstId = cleanSpaces(campos[0]);
						// Limpia caracteres raros
						firstId= firstId.replaceAll("[^A-Za-z0-9]", "");
						firstId= firstId.toLowerCase();
						specialistLexiconLexicalSemantics.LexicalEntry lexicalEntry1 = new specialistLexiconLexicalSemantics.LexicalEntry (firstId,campos[0]);
						// Añado como nombre el id
						//lexicalEntry1.setSynsetName("rootSpL");
						lexicalEntry1.setSynsetName(firstId);
						lexicalEntries.put(firstId, lexicalEntry1);
						synsets.put(firstId, new ArrayList<>());
						String synonym = campos[3];
						String cleanSynonym = cleanSpaces(synonym.toLowerCase());
						specialistLexiconLexicalSemantics.LexicalEntry lexicalEntry2;
						if(!lexicalEntries.containsValue(cleanSynonym)) {
							lexicalEntry2 = new specialistLexiconLexicalSemantics.LexicalEntry (cleanSynonym, synonym, firstId);
							lexicalEntry2.setSynsetName(firstId);
							lexicalEntries.put(cleanSynonym, lexicalEntry2);
						} else {
							lexicalEntry2 = lexicalEntries.get(cleanSynonym);
						}
						synsets.get(firstId).add(lexicalEntry2);
						cont++;
						System.out.println(cont+" : "+firstId+" | "+ synonym);;
					}
					
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

//	public void extractSubTrees(Map<String, String[]> meshMap) {
//
//		String treeNumber;
//		String idParent;
//		String term;
//		String id;
//		String definition;
//
//		for (String key : meshMap.keySet()) {
//
//			treeNumber = meshMap.get(key)[0];
//			id = meshMap.get(key)[1];
//			term = meshMap.get(key)[2];
//			definition = meshMap.get(key)[3];
//			definition= removeAspersan(definition);
//
//			SpecialistLexiconNormalNode nodo = new SpecialistLexiconNormalNode(treeNumber, id, term, definition);
//			tree.put(treeNumber, nodo);
//
//		}
//
//		 for (String key : tree.keySet()) {
//		 System.out.println(key);
//		 }
//		set_ISA_Relation();
//
//	}

	

	public void set_ISA_Relation() {

		char textoBuscado = '.';
		int j=0;
		for (String key : tree.keySet()) {
			j++;
			int ini = 0;
			String treeNumber = tree.get(key).getTreeNumber();
			String father = "rootMesh";
			int punto = 0;
			for (int i = 0; i < treeNumber.length(); i++) {
				if (treeNumber.charAt(i) == textoBuscado) {
					if (punto == 0) {
						father = treeNumber.substring(ini, i);
					} else {
						father = father + "." + treeNumber.substring(ini, i);
					}
					punto++;
					ini = i + 1;
				}
			}
			if (treeNumber != "rootMesh" && treeNumber != " ") {

				tree.get(father).setChild(treeNumber);
				tree.get(key).setParent(father);

			}
			
			//System.out.println(j+".- "+key + " parent is: " + father);
			

		}

	}

	public String cleanWeirdChars(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace("[^a-zA-Z0-9./]", "");

		return cleanDescription;
	}
	
	public String cleanSpaces(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace("'", "");
		return cleanDescription;
	}
	
	 private String removeAspersan(String definition) {
		String cleanDefinition;

		cleanDefinition = definition;
		cleanDefinition = cleanDefinition.replace("&", " and ");

		return cleanDefinition;
	}
	

}
