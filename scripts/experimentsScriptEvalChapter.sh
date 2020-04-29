#! /bin/bash
# Script to execute all the experiments in a row


# # # # Experiment 1.1.- DSM-5 vs ICD-10 200 entries S-Match TOP
# python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10.xml	 -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/200entriesDSM5vsICD10S-Match.xml -n Experiment1_1

# # # # Experiment 1.1.- DSM-5 vs ICD-10 200 entries S-Match TOP DIVERSICON CON WN
# python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10.xml	 -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/200entriesDSM5vsICD10S-MatchMinimal.xml -n Experiment1_1

# # # # Experiment 1.2.- DSM-5 vs ICD-10 200 entries Symbolic TOP
# python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/200entriesDSM5vsICD10S-MatchSymbolic.xml -n Experiment1_2

# # # # Experiment 1.2.- DSM-5 vs ICD-10 200 entries Specialist TOP
# python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10_2noCommas.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/200entriesDSM5vsICD10S-MatchGrammar.xml -n Experiment1_3

# # # Experiment 1.3.- DSM-5 vs ICD-10 200 entries SPECIALIST Reduced Grammar TOP
# python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10_2noCommas.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/200entriesDSM5vsICD10S-MatchSymbolicGrammar.xml -n Experiment1_4



# # Experiment 1.4.- DSM-5 vs ICD-10 200 entries LogMap
 python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/logmap2_mappingsDSM5vsICD10.xml -n Experiment1_1

# # # Experiment 1.5.- DSM-5 vs ICD-10 200 entries LogMap Specialist
 python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/logmap2_mappingsDSM5vsICD10Symbolic.xml -n Experiment1_2

 # Experiment 1.6.- DSM-5 vs ICD-10 200 entries LogMap SPECIALIST Reduced Grammar
 python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10_2noCommas.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/logmap2_mappingsDSM5vsICD10Grammar.xml -n Experiment1_3

# # Experiment 1.6.- DSM-5 vs ICD-10 200 entries LogMap SPECIALIST Reduced Grammar
 python evaluate.py -a ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/goldStandard/goldStandard200entriesDSM5vsICD10_2noCommas.xml -b ~/MarieCurieESR/PhD/Domain-aware\ matching/Medical\ Domain\ Experiments/Results/aFINAL/logmap2_mappingsDSM5vsICD10SymbolicGrammar.xml -n Experiment1_4



