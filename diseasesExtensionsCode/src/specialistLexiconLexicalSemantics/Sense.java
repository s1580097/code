package specialistLexiconLexicalSemantics;

public class Sense {
	
	private String id;  
	private String synset;
	
	public Sense(String id) {
		super();
		this.id ="splL_s_"+id;
		this.synset = "splL_ss_"+id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSynset() {
		return synset;
	}

	public void setSynset(String synset) {
		this.synset = synset;
	}
	
}
