package specialistLexiconLexicalSemantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import MeSH.MeshNormalNode;
import MeSH.MeshTree;
import SpecialistLexicon.SpecialistGrammar;
import SpecialistLexicon.SpecialistLexiconNormalNode;
import SpecialistLexicon.SpecialistLexiconTree;
import meSHLexicalSemantics.Sense;

public class Lexicon {

	List<LexicalEntry> lexicalEntries;
	List<Synset> synsets;

	public Lexicon() {
		super();
		this.lexicalEntries = new ArrayList<LexicalEntry>();
		this.synsets = new ArrayList<Synset>();
	}

	public List<LexicalEntry> getLexicalEntries() {
		return lexicalEntries;
	}

	public void setLexicalEntries(List<LexicalEntry> lexicalEntries) {
		this.lexicalEntries = lexicalEntries;
	}

	public List<Synset> getSynsets() {
		return synsets;
	}

	public void setSynsets(List<Synset> synsets) {
		this.synsets = synsets;
	}

	public void inicializa(SpecialistLexiconTree tree) {

		Map<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>> synsetsList = tree.getSynsets();
		Map<String, specialistLexiconLexicalSemantics.LexicalEntry> lexicalEntriesList = tree.getLexicalEntries();
//		for (Entry<String, specialistLexiconLexicalSemantics.LexicalEntry> key : lexicalEntriesList.entrySet()) {
//			lexicalEntries.add(key.getValue());
//		}
		for (Entry<String, ArrayList<specialistLexiconLexicalSemantics.LexicalEntry>> key : synsetsList.entrySet()) {
			String id = lexicalEntriesList.get(key.getKey()).getId();
			String cleanId = cleanSpaces(id);
			String definition = "";
			String term = lexicalEntriesList.get(key.getKey()).getWrittenForm();
			String cleanTerm = cleanSpaces(term);
			Sense sense = new Sense(cleanId);
			Synset synset = new Synset(cleanId, definition, cleanTerm);

//			String parentName = "rootSpL";
			String parentName = "wn31_ss_n1740";
			specialistLexiconLexicalSemantics.SynsetRelation synsetRelation = new specialistLexiconLexicalSemantics.SynsetRelation(
					parentName);
			synset.setSynsetRelation(synsetRelation);

			synsets.add(synset);
		}
		/*****
		 * Añadir grammar
		 */
		SpecialistGrammar grammar = new SpecialistGrammar();
//		grammar.processFile("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/grammarExtensions/allRules/allGrammarRules.txt");
//		grammar.processFile("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/grammarExtensions/reducedGrammarRules.txt");
//		grammar.processFile("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/grammarExtensions/secondSetRules/rulesSecondSubset.txt");
		grammar.processFile("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/grammarExtensions/reducedRules/reducedRules.txt");
//		grammar.processFile("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/grammarExtensions/secondSetRules/testDuplication2.txt");
		
		HashMap<String,String[]> derivations= grammar.getDerivations();
		
		for (Entry<String,String[]> entry: derivations.entrySet()) {
			
			String key = entry.getKey().toLowerCase();
			String elem1= entry.getValue()[0];
			String posElem1= entry.getValue()[1];
			String elem2 = entry.getValue()[2];
			String idElem2= elem2.replace(" ", ""); 
			String posElem2 = entry.getValue()[3];
//			System.out.println("Esta es la clave "+entry.getKey());
//			System.out.println("Este es el id "+entry.getValue()[0]);
//			System.out.println("Esta es el pos "+entry.getValue()[1]);
//			System.out.println("Este es el elem2 "+entry.getValue()[2]);
//			System.out.println("Esta es el pos del elem2 "+entry.getValue()[3]);
			
			
			// If the synset is defined
			if(synsetsList.containsKey(key)) {
				
				boolean lexicalEntryDefined = false;
				for(LexicalEntry entrada: synsetsList.get(key)) {
					if(entrada.getWrittenForm().equalsIgnoreCase(elem2)) {
						lexicalEntryDefined = true;
						break;
					}
				}
				// if the lexicalEntry is not defined
				if(lexicalEntryDefined== false) {
					LexicalEntry newLexEnt = new LexicalEntry(idElem2,elem2,posElem2,key);
//					lexicalEntries.add(newLexEnt);
					lexicalEntriesList.put(newLexEnt.getKey(), newLexEnt);
				}
				
			} else if (synsetsList.containsKey(idElem2)) {
				boolean lexicalEntryDefined = false;
				for(LexicalEntry entrada: synsetsList.get(idElem2)) {
					if(entrada.getWrittenForm().equalsIgnoreCase(elem1)) {
						lexicalEntryDefined = true;
						break;
					}
				}
				// if the lexicalEntry is not defined
				if(lexicalEntryDefined== false) {
					LexicalEntry newLexEnt = new LexicalEntry(key,elem1,posElem1,idElem2);
//					lexicalEntries.add(newLexEnt);
					lexicalEntriesList.put(newLexEnt.getKey(), newLexEnt);
				}
				
			} else {
				Synset newSynset = new Synset(elem1, "", key);
//				String parentName = "rootSpL";
				String parentName = "wn31_ss_n1740";
				specialistLexiconLexicalSemantics.SynsetRelation synsetRelation = new specialistLexiconLexicalSemantics.SynsetRelation(
						parentName);
				newSynset.setSynsetRelation(synsetRelation);
				synsets.add(newSynset);
				LexicalEntry  newLexEnt1 = new LexicalEntry(key, elem1);
				if(!lexicalEntriesList.containsKey(newLexEnt1.getKey())) {
//				lexicalEntries.add(newLexEnt1);
				lexicalEntriesList.put(key, newLexEnt1);
				}
				
				LexicalEntry newLexEnt2 = new LexicalEntry(idElem2,elem2,posElem2,key);
				if(!lexicalEntriesList.containsKey(newLexEnt2.getKey())) {
					
//					lexicalEntries.add(newLexEnt2);
					lexicalEntriesList.put(newLexEnt2.getKey(), newLexEnt2);
					
					ArrayList<LexicalEntry> lexEnt = new ArrayList<LexicalEntry>();
					lexEnt.add(newLexEnt2);
					synsetsList.put(newSynset.getKey(), lexEnt);
				}
				
			}
			
			
			
		}
		for(Entry<String,LexicalEntry> lexEnt: lexicalEntriesList.entrySet()) {
			lexicalEntries.add(lexEnt.getValue());
		}

		
	}

	public String cleanSpaces(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace(",", "");

		return cleanDescription;
	}

	public SpecialistLexiconTree ordenaMapa(SpecialistLexiconTree specialistLexiconTree) {

		SpecialistLexiconTree sortedSpecialistLexiconTree = new SpecialistLexiconTree();

		Map<String, SpecialistLexiconNormalNode> tree = new TreeMap<String, SpecialistLexiconNormalNode>(
				specialistLexiconTree.getTree());

		sortedSpecialistLexiconTree.setRoot(specialistLexiconTree.getRoot());
		sortedSpecialistLexiconTree.setTree(tree);

		return sortedSpecialistLexiconTree;
	}

	public void appendGrammaticalLexicalEntries(List<LexicalEntry> grammaticalLexicalEntries) {
		List<LexicalEntry> unionLexicalEntries = new ArrayList<LexicalEntry>();
		unionLexicalEntries.addAll(lexicalEntries);
		unionLexicalEntries.addAll(grammaticalLexicalEntries);
		lexicalEntries= unionLexicalEntries;
		
	}

	public void appendGrammaticalSynsets(List<Synset> grammaticalSynsets) {
		
		List<Synset> unionSynsets = new ArrayList<Synset>();
		unionSynsets.addAll(synsets);
		unionSynsets.addAll(grammaticalSynsets);
		synsets = unionSynsets;

	}
}
