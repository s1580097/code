package MeSH;

public class MeshNormalNode extends MeshNode {
	
	private String parent;
	private String definition;
	
	public MeshNormalNode(String treeNumber, String id, String term, String definition) {
		super(treeNumber, id, term);
		this.definition=definition;
		this.parent = "nada";
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
