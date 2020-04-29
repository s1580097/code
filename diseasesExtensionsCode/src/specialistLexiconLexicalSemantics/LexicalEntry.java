package specialistLexiconLexicalSemantics;

public class LexicalEntry {
	
	private String key;
	private String id;
	private String language;
	private String writtenForm;
	private String partOfSpeech;
	private String externalSystem;
	private String externalReference;
	private String synsetName;
	private String sense; // This should be a list
	
	
	public LexicalEntry(String id,String writtenForm) {
		super();
		//String writtenFormClean = cleanSpaces(writtenForm);
		//this.id = "meshLex_le_"+writtenFormClean;
		// spl = SpecialistLexicon
		id = id.toLowerCase();
		String idName = cleanSpaces(id);
		this.key = idName;
		
		this.id = "splL_le_"+key;
		this.language = "eng";
		this.partOfSpeech= "noun";
		this.writtenForm = writtenForm;
		this.externalSystem = "SpecialistLexicon";
		this.externalReference = "SpecialistLexicon.csv";
		this.synsetName = "splL_ss_"+cleanSpaces(id);
		this.sense="splL_s_"+cleanSpaces(id);
	}
	
	public LexicalEntry(String id, String writtenForm, String synset) {
		super();
		id = id.toLowerCase();
		//String writtenFormClean = cleanSpaces(writtenForm);
		//this.id = "meshLex_le_"+writtenFormClean;
		// spl = SpecialistLexico

		this.key = cleanSpaces(id);
		this.id = "splL_le_"+key;
		this.language = "eng";
		this.partOfSpeech= "noun";
		this.writtenForm = writtenForm;
		this.externalSystem = "SpecialistLexicon";
		this.externalReference = "SpecialistLexicon.csv";
		String cleanSynset = cleanSpaces(synset);
		this.synsetName = "splL_ss_"+cleanSynset;
		this.sense="splL_s_"+id;
	}
	
	public LexicalEntry(String id, String writtenForm, String pos,String synset) {
		super();
		id = id.toLowerCase();
		//String writtenFormClean = cleanSpaces(writtenForm);
		//this.id = "meshLex_le_"+writtenFormClean;
		// spl = SpecialistLexicon
		this.key = cleanSpaces(id);
		this.id = "splL_le_"+key;
		
		this.language = "eng";
		this.partOfSpeech= pos;
		this.writtenForm = writtenForm;
		this.externalSystem = "SpecialistLexicon";
		this.externalReference = "SpecialistLexicon.csv";
		String cleanSynset = cleanSpaces(synset);
		this.synsetName = "splL_ss_"+cleanSynset;
		this.sense="splL_s_"+id;
	}
	
	public String getSynsetName() {
		return synsetName;
	}
	public void setSynsetName(String synsetName) {
		this.synsetName = "splL_ss_"+cleanSpaces(synsetName);
	}
	public String getId() {
		return id;
	}
	public String getKey() {
		return key;
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
		this.sense = cleanSpaces(sense);
	}
	
	public String cleanSpaces(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace("'", "");
		return cleanDescription;
	}
	

	
}
