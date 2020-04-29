package meSHLexicalSemantics;

public class Sense {
	
	private String target;  
	private String relType;
	private String relName;
	
	public Sense(String target) {
		super();
		this.target = "meshL_ss_"+target;
		this.relType = "taxonomic";
		this.relName = "hypernym";
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}
	
	
	
}
