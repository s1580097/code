package eval;

public class MainEvaluation {

	public static void main(String[] args) {

		Evaluation evaluation;
	/*small dataset*/ 	String goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardDSM5vsICD10(noCommas).xml";
	/*large dataset*/ //String goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandarddescriptionsDSM-5vsICD-10Large(no commas).xml";
 		String goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardDSM5vsICD10(noCommas).xml";
 	/*small dataset*/ 		String matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-match(no commas)v1.xml";
 	/*large dataset*/ //String matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-match(no commas)Large.xml";
	/*small dataset*/  //String matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardDSM5vsICD10(noCommas).xml";
 	/*small dataset*/  String LogMapMatching = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/scripts/LogMap/mappingsConverted.xml";
	/*large dataset*/ //String LogMapMatching = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/scripts/LogMap/mappings2Converted.xml";
	
		
//		System.out.println("****************************************************************************");
//		System.out.println("************************** DSM5 vs ICD10 s-match ***************************");
//		System.out.println("****************************************************************************");
//		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
//		evaluation.evaluateEquivalence();
////		evaluation.evaluateSubsumption();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");
		
 	// /* Small dataset */	goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardDSM5vsICD10(noCommas).xml";
 	/* Large dataset */ 		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandarddescriptionsDSM-5vsICD-10Large(no commas).xml";
		// small dataset goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardDSM5vsICD10(noCommas).xml";
		//	/* Small dataset */ matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-matchWithMeSH(no commas).xml";
			/* Large dataset */ 	matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-matchWithMesh(no commas)Large.xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** DSM5 vs ICD10 MeSH extension ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
//		evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
 	/* Small dataset */	//goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardDSM5vsICD10(noCommas).xml";
//	/* Large dataset */	 goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandarddescriptionsDSM-5vsICD-10Large(no commas).xml";
////		// small dataset goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardDSM5vsICD10(noCommas).xml";
//// 	/* Small dataset */ matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-matchSpecialistLexicon(no commas)Small-v2.xml";
// 	/* Large dataset */		 matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/DSM5VsICD10s-matchSpecialistLexicon(no commas)Large-v2.xml";
//		System.out.println("****************************************************************************");
//		System.out.println("************************** DSM5 vs ICD10 Specialist Lexicon ***************************");
//		System.out.println("****************************************************************************");
//		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
//		evaluation.evaluateEquivalence();
////		evaluation.evaluateSubsumption();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");
//		
		
//		System.out.println("****************************************************************************");
//		System.out.println("************************** DSM5 vs ICD10 LogMap ***************************");
//		System.out.println("****************************************************************************");
//		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,LogMapMatching);
//		evaluation.evaluateEquivalence();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");
		
		/*
		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardSNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardSNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/SNOMEDvsICD-10v1s-match(no-commas).xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT vs ICD10 s-match ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardSNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardSNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/SNOMEDvsICD-10v1MeSH(no-commas).xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT vs ICD10 MeSH extension ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandardSNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandardSNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/SNOMEDvsICD-10v1SpecialistLexiconv1(no-commas).xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT vs ICD10 Specialist Lexicon ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");

		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/(Structured)SNOMEDvsICD-10v1s-match.xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT structured vs ICD10 s-match ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		//evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/(Structured)DSM5VsICD10s-matchWithMeSH(no commas).xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT structured vs ICD10 MeSH extension ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		//evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
		goldStandardEquivalence = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/equivalenceGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		goldStandardSubsumption = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/subsumptionGoldStandard(Structured)SNOMEDvsICD10v1(no commas).xml";
		matching = "/Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/Double-checked/(Structured)DSM5VsICD10s-matchWithSpecialistLexicon(no commas).xml";
		System.out.println("****************************************************************************");
		System.out.println("************************** SNOMED-CT structured vs ICD10 Specialist Lexicon ***************************");
		System.out.println("****************************************************************************");
		evaluation = new Evaluation(goldStandardEquivalence,goldStandardSubsumption,matching);
		evaluation.evaluateEquivalence();
		//evaluation.evaluateSubsumption();
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		
		
		*/
	
	}
}
