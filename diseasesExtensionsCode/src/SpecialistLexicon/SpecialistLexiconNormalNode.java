package SpecialistLexicon;

public class SpecialistLexiconNormalNode extends SpecialistLexiconNode {
	
	private String parent;
	private String definition;
	
	public SpecialistLexiconNormalNode(String treeNumber, String id, String term, String definition) {
		super(treeNumber, id, term);
		this.definition=definition;
		this.parent = "";
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}


	
}
