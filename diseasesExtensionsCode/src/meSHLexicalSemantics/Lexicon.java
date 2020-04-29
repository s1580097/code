package meSHLexicalSemantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import MeSH.MeshNormalNode;
import MeSH.MeshTree;

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

	public void inicializa(MeshTree tree) {
		tree = ordenaMapa(tree);
		for (Entry<String, MeshNormalNode> key : tree.getTree().entrySet()) {

			String id = key.getValue().getTreeNumber();
			String term = key.getValue().getTerm();
			String definition = key.getValue().getDefinition();
			String parentId = key.getValue().getParent();

			// if (term != null && term != "rootMesh") {
			if (term != null) {

				LexicalEntry lexicalEntry = new LexicalEntry(term, id);
				Synset synset = new Synset(term, definition, id);
				// Sólo se permite un único padre. Esto habría que cambiarlo para darle más
				// flexibilidad
				// String parentName = tree.getTree().get(parentId).getTerm();
				String parentName;
				if (term == "rootMesh") {
					parentName = "wn31_ss_n1740";
				} else {
					parentName = tree.getTree().get(parentId).getTreeNumber();
					parentName = cleanSpaces(parentName);
				}
				lexicalEntries.add(lexicalEntry);
				Sense sense = new Sense(parentName);
				synset.setSense(sense);
				synsets.add(synset);

			}
		}

	}

	public String cleanSpaces(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace(",", "");

		return cleanDescription;
	}

	public MeshTree ordenaMapa(MeshTree meshTree) {

		MeshTree sortedMeshTree = new MeshTree();

		Map<String, MeshNormalNode> tree = new TreeMap<String, MeshNormalNode>(meshTree.getTree());

		sortedMeshTree.setRoot(meshTree.getRoot());
		sortedMeshTree.setTree(tree);

		return sortedMeshTree;
	}
}
