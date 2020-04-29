package MeSH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import meSHLexicalSemantics.LexicalEntry;
import meSHLexicalSemantics.Lexicon;
import meSHLexicalSemantics.Synset;

public class FTLHelloWorld {

	public static void main(String[] args) {

		Date fecha = new Date();
		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("src/helloworld.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("lexicalResource_Name", "MesH-extension");
			data.put("lexicalResource_Prefix", "meshL");
			data.put("lexicalResource_Name_noNamespaceSchemaLocation",
					"http://diversicon-kb.eu/schema/1.0/diversicon.xsd");
			data.put("lexicalResource_GlobalInformation", "Diseases (C) + Psychiatry and Psychology (F)  (MeSH)");
			data.put("lexicalResource_MetaData_id", "meshL__md");
			data.put("lexicalResource_MetaData_creationDate", fecha.toString());
			data.put("lexicalResource_MetaData_creationTool", "automatic");
			data.put("lexicalResource_MetaData_version", "1.0");
			data.put("lexicalResource_MetaData_creationProcess", "semi-automatic");
			data.put("lexicalResource_MetaData_automatic", "true");
			data.put("lexicon_Id", "meshL_md_lex_eng");
			data.put("lexicon_LanguageIdentifier", "eng");
			data.put("lexicon_name", "English Lexicon of Mental Disorders and Diseases (MeSH)");

			Lexicon lexicon = new Lexicon();

			MeshTree tree = new MeshTree();

			//String archivo = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/meshMentalDiseases.csv";
			String archivo = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/meshMentalDisordersAndDiseases.csv";
			tree.extractSubTrees(tree.extractTreeNumberFields(archivo));
			lexicon.inicializa(tree);
			// Add lexical entries
			List<LexicalEntry> lexicalEntries = lexicon.getLexicalEntries();
			data.put("lexicalEntries", lexicalEntries);
			
			//Add synsets
			List<Synset> synsets = lexicon.getSynsets();
			data.put("synsets", synsets);


			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();

			// File output
			Writer file = new FileWriter(new File("meshExtension.xml"));
			template.process(data, file);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}