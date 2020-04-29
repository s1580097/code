package SpecialistLexicon;
import java.util.ArrayList;
import java.util.List;

public class SpecialistLexiconNode {
	
	private String treeNumber;
	private String id;
	private String term;
	private List<String> children;
	
	
	public SpecialistLexiconNode(String treeNumber, String id, String term) {
		super();
		this.treeNumber = treeNumber;
		this.id = id;
		this.term = term;
		this.children = new ArrayList<String>();
	}


	public String getTreeNumber() {
		return treeNumber;
	}


	public void setTreeNumber(String treeNumber) {
		this.treeNumber = treeNumber;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}


	public List<String> getChildren() {
		return children;
	}


	public void setChild(String child) {
		this.children.add(child);
	}
	
	
	
}
