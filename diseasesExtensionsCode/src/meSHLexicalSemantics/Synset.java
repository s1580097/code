package meSHLexicalSemantics;

import java.util.ArrayList;
import java.util.List;

public class Synset {

	private String id;
	private String languageIdentifier;
	private String writtenText;
	private String externalSystem;
	private String externalReference;
	// private List<Sense> senses;
	private Sense sense;

	public Synset(String name, String definition, String id) {
		super();
		String nameNoSpaces = cleanSpaces(name);
	//	this.id = "meshLex_ss_" + nameNoSpaces;
		this.id = "meshL_ss_" + id;
		
		this.languageIdentifier = "eng";
		this.writtenText = definition;
		this.externalReference = "MeSH.csv";
		// this.senses = new ArrayList<Sense>();
		this.sense = null;
		this.externalSystem = "MeSH";
	}

	public String getExternalSystem() {
		return externalSystem;
	}

	public void setExternalSystem(String externalSystem) {
		this.externalSystem = externalSystem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanguageIdentifier() {
		return languageIdentifier;
	}

	public void setLanguageIdentifier(String languageIdentifier) {
		this.languageIdentifier = languageIdentifier;
	}

	public String getWrittenText() {
		return writtenText;
	}

	public void setWrittenText(String writtenText) {
		this.writtenText = writtenText;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	// public List<Sense> getSenses() {
	// return senses;
	// }
	// public void setSenses(List<Sense> senses) {
	// this.senses = senses;
	// }

	// public void addSenses(Sense senses) {
	// this.senses.add(senses);
	// }

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
	}
	
	public String cleanSpaces(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace(" ", "");
		cleanDescription = cleanDescription.replace(",", "");

		return cleanDescription;
	}

}
