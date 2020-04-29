package meSHLexicalSemantics;

public class LexicalEntry {

	private String id;
	private String language;
	private String writtenForm;
	private String partOfSpeech;
	private String externalSystem;
	private String externalReference;
	private String synsetName;
	private String sense; // This should be a list
	
	
	public LexicalEntry(String writtenForm, String treeName) {
		super();
		String writtenFormClean = cleanSpaces(writtenForm);
		//this.id = "meshLex_le_"+writtenFormClean;
		this.id = "meshL_le_"+treeName;
		this.language = "eng";
		this.partOfSpeech= "noun";
		this.writtenForm = writtenForm;
		this.externalSystem = "MeSH";
		this.externalReference = "MeSH.csv";
		this.synsetName = "meshL_ss_"+treeName;
		this.sense="meshL_s_"+treeName;
	}
	public String getSynsetName() {
		return synsetName;
	}
	public void setSynsetName(String synsetName) {
		this.synsetName = synsetName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getWrittenForm() {
		return writtenForm;
	}
	public void setWrittenForm(String writtenForm) {
		this.writtenForm = writtenForm;
	}
	public String getExternalSystem() {
		return externalSystem;
	}
	public void setExternalSystem(String externalSystem) {
		this.externalSystem = externalSystem;
	}
	public String getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public String getSense() {
		return sense;
	}
	public void setSense(String sense) {
		this.sense = sense;
	}
	
	public String cleanSpaces(String description) {
		String cleanDescription="";
		
		for (int i = 0; i < description.length(); i++) {
			if (description.charAt(i) != ' ') {
				cleanDescription = cleanDescription + description.charAt(i);
			}
		}

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace(",", "");

		return cleanDescription;
	}
	

	
}
