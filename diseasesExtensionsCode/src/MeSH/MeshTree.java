package MeSH;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MeshTree {

	private MeshNormalNode root;
	private Map<String, MeshNormalNode> tree;

	public MeshTree() {
		String treeNumber = "rootMesh";
		String id = "Mesh";
		String term = "rootMesh";
		root = new MeshNormalNode(treeNumber, id, term, "00001740"); //Location in WN (Entity)
		tree = new HashMap<String, MeshNormalNode>();
		tree.put(treeNumber, root);
	}

	public MeshNormalNode getRoot() {
		return root;
	}

	public void setRoot(MeshNormalNode root) {
		this.root = root;
	}

	public Map<String, MeshNormalNode> getTree() {
		return tree;
	}

	public void setTree(Map<String, MeshNormalNode> tree) {
		this.tree = tree;
	}

	public Map<String, String[]> extractTreeNumberFields(String archivo) {

		SortedMap meshMap = new TreeMap<String, String[]>();

		String cadena;
		char textoBuscado = '*';
		String content;
		FileReader f;
		try {
			f = new FileReader(archivo);

			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				int ini = 0;
				int campo = 0;
				String[] campos = new String[4];
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.charAt(i) == textoBuscado) {

						campos[campo] = (String)cadena.substring(ini, i);
						ini = i + 1;
						campo++;
					}
				}
				campos[campo] = cadena.substring(ini, cadena.length());
				meshMap.put(campos[0], campos);
				
			}
			b.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return meshMap;

	}

	public void extractSubTrees(Map<String, String[]> meshMap) {

		String treeNumber;
		String idParent;
		String term;
		String id;
		String definition;

		for (String key : meshMap.keySet()) {

			treeNumber = meshMap.get(key)[0];
			id = meshMap.get(key)[1];
			term = meshMap.get(key)[2];
			definition = meshMap.get(key)[3];
			definition= removeAspersan(definition);

			MeshNormalNode nodo = new MeshNormalNode(treeNumber, id, term, definition);
			tree.put(treeNumber, nodo);

		}

		 for (String key : tree.keySet()) {
		 System.out.println(key);
		 }
		set_ISA_Relation();

	}

	

	public void set_ISA_Relation() {

		char textoBuscado = '.';
		int j=0;
		for (String key : tree.keySet()) {
			j++;
			int ini = 0;
			String treeNumber = tree.get(key).getTreeNumber();
			String father = "rootMesh";
			int punto = 0;
			for (int i = 0; i < treeNumber.length(); i++) {
				if (treeNumber.charAt(i) == textoBuscado) {
					if (punto == 0) {
						father = treeNumber.substring(ini, i);
					} else {
						father = father + "." + treeNumber.substring(ini, i);
					}
					punto++;
					ini = i + 1;
				}
			}
			if (treeNumber != "rootMesh" && treeNumber != " ") {

				tree.get(father).setChild(treeNumber);
				tree.get(key).setParent(father);

			}
			
			//System.out.println(j+".- "+key + " parent is: " + father);
			

		}

	}

	public String cleanWeirdChars(String description) {
		String cleanDescription;

		cleanDescription = description;
		cleanDescription = cleanDescription.replace("[^a-zA-Z0-9./]", "");

		return cleanDescription;
	}
	
	 private String removeAspersan(String definition) {
		String cleanDefinition;

		cleanDefinition = definition;
		cleanDefinition = cleanDefinition.replace("&", " and ");

		return cleanDefinition;
	}
	

}
