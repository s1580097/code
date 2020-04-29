package SpecialistLexicon;

import specialistLexiconLexicalSemantics.*;

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

public class FTLSpecialistLexicon {

	public static void main(String[] args) {
		
		
		
		

		Date fecha = new Date();
		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("src/specialistLexicon.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("lexicalResource_Name", "SpecialistLexicon-extensionReducedGrammar");
			data.put("lexicalResource_Prefix", "splL");
			data.put("lexicalResource_Name_noNamespaceSchemaLocation",
					"http://diversicon-kb.eu/schema/1.0/diversicon.xsd");
			data.put("lexicalResource_GlobalInformation", "Specialist Lexicon Grammar (no semantic relations)");
			data.put("lexicalResource_MetaData_id", "splL__md");
			data.put("lexicalResource_MetaData_creationDate", fecha.toString());
			data.put("lexicalResource_MetaData_creationTool", "automatic");
			data.put("lexicalResource_MetaData_version", "1.0");
			data.put("lexicalResource_MetaData_creationProcess", "semi-automatic");
			data.put("lexicalResource_MetaData_automatic", "true");
			data.put("lexicon_Id", "splL_md_lex_eng");
			data.put("lexicon_LanguageIdentifier", "eng");
			data.put("lexicon_name", "Specialist Lexicon Grammar with no semantic relations");

			specialistLexiconLexicalSemantics.Lexicon lexicon = new specialistLexiconLexicalSemantics.Lexicon();

			SpecialistLexiconTree tree = new SpecialistLexiconTree();

			//String archivo = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/meshMentalDiseases.csv";
			String archivo = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/specialistLexiconDB10.csv";
			tree.extractFields(archivo);
			lexicon.inicializa(tree);
			// Add lexical entries
			List<specialistLexiconLexicalSemantics.LexicalEntry> lexicalEntries = lexicon.getLexicalEntries();
			data.put("lexicalEntries", lexicalEntries);
			
			//Add synsets
			List<specialistLexiconLexicalSemantics.Synset> synsets = lexicon.getSynsets();
			data.put("synsets", synsets);

			
			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();

			// File output
			Writer file = new FileWriter(new File("ReducedGrammarExtension-v1.xml"));
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