#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jul 17 19:56:07 2018

@author: francis
"""
import sys, getopt
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sbn
import plotly.plotly as py
import plotly.graph_objs as go
from os import listdir
from os.path import isfile, join



# =============================================================================
# Compute False negative
# =============================================================================
def computeFalseNegatives(total, truePositives):

    falseNegatives = total - truePositives
    return falseNegatives
    
# =============================================================================
# Compute precision
# =============================================================================

def computePrecision(truePositives, falsePositives):
  
    precision = float(truePositives)/(truePositives + falsePositives)
    return precision

# =============================================================================
#  Compute recall
# =============================================================================

def computeRecall(truePositives, total):
    
    recall = float(truePositives) / total
    return recall

# =============================================================================
# Compute fscore
# =============================================================================

def computeFScore(precision, recall):
    
    fScore = float(2) / ((1/recall)+(1/precision))
    return fScore

# =============================================================================
# Extract term
# =============================================================================

def extractTerm(string):
    
    terms = string.split("\\")
    for term in terms:
        entry = term
    return entry

# =============================================================================
# Remove jump
# =============================================================================

def removeJump(string):
    
    entry = string.replace("\n","")
    
    return entry
# =============================================================================
# Compute similarity
# =============================================================================

def computeSimilarity(goldStandard, matching, experimentName):
    
    total= 0
    truePositives = 0
    falsePositives = 0
    equivalences = 0
    subsumptions = 0
    falseNegatives = 0
    
    
    
    truePositivesFile = open("../evaluationResults/"+experimentName+"truePositives.xml","w")
    falsePositivesFile = open("../evaluationResults/"+experimentName+"falsePositives.xml","w")
    falseNegativesFile = open("../evaluationResults/"+experimentName+"falseNegatives.xml","w")
    resultsFile = open("../evaluationResults/"+experimentName+"results.xml","w")
    
    # True Positives
    for entry1 in goldStandard:
        for entry2 in goldStandard[entry1]:
            total = total + 1
            if((entry1 in matching) and (entry2 in matching[entry1])): 
                if(matching[entry1][entry2] == goldStandard[entry1][entry2]):
                    truePositives = truePositives + 1
                    equivalences = equivalences + 1 
                    truePositivesFile.write("-----------------------------"+ str(truePositives) +"---------------------------\\\\\n")
                    truePositivesFile.write("----------------------------- Equivalence ---------------------------\\\\\n")
                    truePositivesFile.write("True positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                    truePositivesFile.write("True positive (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
            
                elif((matching[entry1][entry2] == '<') or (matching[entry1][entry2] == '>')):
                    truePositives = truePositives + 1
                    subsumptions = subsumptions + 1
                    truePositivesFile.write("----------------------------- Subsumption ---------------------------\\\\\n")
                    truePositivesFile.write("-----------------------------"+ str(truePositives) +"---------------------------\\\\\n")
                    truePositivesFile.write("True positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                    truePositivesFile.write("True positive (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
            else:
                    falseNegativesFile.write("-----------------------------"+ str(falseNegatives) +"---------------------------\\\\\n")
                    falseNegativesFile.write("----------------------------- False negative ---------------------------\\\\\n")
                    falseNegativesFile.write("False negative (matching):"+ entry1+ " ******* "+entry2+"\\\\\n")
                    falseNegativesFile.write("False negative (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
                    falseNegatives = falseNegatives + 1
    # False positives
    for entry1 in matching:
        for entry2 in matching[entry1]:
            if(matching[entry1][entry2]!= '!'):
                if(entry2 not in goldStandard[entry1]):
                    falsePositives = falsePositives + 1
                    falsePositivesFile.write("-----------------------------"+ str(falsePositives) +"---------------------------\\\\\\\n")
                    falsePositivesFile.write("----------------------------- False Positive ---------------------------\\\\\n")
                    falsePositivesFile.write("False positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                    falsePositivesFile.write("False positive (goldStandard):"+ entry1+ " ** not in goldStandard *** "+entry2+"\\\\\n")
#             Si está pero de manera incorrecta
            
                elif(not (goldStandard[entry1][entry2]== '=')):
                    falsePositives = falsePositives + 1
                    falsePositivesFile.write("-----------------------------"+ str(falsePositives) +"---------------------------\\\\\n")
                    falsePositivesFile.write("----------------------------- False Positive ---------------------------\\\\\n")
                    falsePositivesFile.write("False positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                    falsePositivesFile.write("False positive (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
                elif(((matching[entry1][entry2]== '<') or (matching[entry1][entry2]== '>')) and (entry2 != 'Top')):
                    if(entry2 not in goldStandard[entry1]):
                        falsePositives = falsePositives + 1
                        falsePositivesFile.write("-----------------------------"+ str(falsePositives) +"---------------------------\\\\\n")
                        falsePositivesFile.write("----------------------------- False Positive ---------------------------\\\\\n")
                        falsePositivesFile.write("False positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                        falsePositivesFile.write("False positive (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
                     
                elif(not (goldStandard[entry1][entry2]== '=')):
                    falsePositives = falsePositives + 1
                    falsePositivesFile.write("-----------------------------"+ str(falsePositives) +"---------------------------\\\\\n")
                    falsePositivesFile.write("----------------------------- False Positive ---------------------------\\\\\n")
                    falsePositivesFile.write("False positive (matching):"+ entry1+ " "+matching[entry1][entry2]+ " "+entry2+"\\\\\n")
                    falsePositivesFile.write("False positive (goldStandard):"+ entry1+ " "+goldStandard[entry1][entry2]+ " "+entry2+"\\\\\n")
#    
            
    resultsFile.write("Total = "+ str(total)+"\n")
    resultsFile.write("TruePositives = "+ str(truePositives)+"\n")
    resultsFile.write("FalsePositives = "+ str(falsePositives)+"\n")
    resultsFile.write("FalseNegatives = "+ str(falseNegatives)+"\n")       
    precision = computePrecision(truePositives, falsePositives)
    resultsFile.write("Precision = "+ str(precision)+"\n")
    recall = computeRecall(truePositives, total)        
    resultsFile.write("Recall = "+ str(recall)+"\n")
    resultsFile.write("F-measure = "+ str(computeFScore(precision, recall))+"\n")        
            
    truePositivesFile.close
    falseNegativesFile.close
    falsePositivesFile.close
    resultsFile.close        
            
            


# =============================================================================
# Load GoldStandard            
# =============================================================================
def loadGoldStandard(goldStandardPath):
    
    goldStandardFile = open(goldStandardPath)
    goldStandard = {}
    
    for line in goldStandardFile:
        fields = line.split("\t")
        entry1 = fields[0][1:]
        entry2 = removeJump(fields[2][1:])
        
        relation = fields[1]
        if(entry1 in goldStandard):
            goldStandard[entry1][entry2] = relation
        else:
            goldStandard[entry1] = {}
            goldStandard[entry1][entry2] = relation
            
    return goldStandard

# =============================================================================
# Load Matching            
# =============================================================================
def loadMatching(matchingPath):            
    
    matchingFile = open(matchingPath)
    matching = {}
    
    for line in matchingFile:
        fields = line.split("\t")
        entry1 = extractTerm(fields[0])
        entry2 = removeJump(extractTerm(fields[2]))
        relation = fields[1]
        if(entry1 != "Top" and entry2 !="Top"):
            if (entry1 in matching):
                matching[entry1][entry2] = relation
            else:
                matching[entry1] = {}
                matching[entry1][entry2] = relation

    return matching


# =============================================================================
# Generate table            
# =============================================================================

#    def generateTable(matcher, results):
def generateTable(data):
    
#        data[matcher] = series 
    
#        data = {'S-Match':pd.Series([81,35,30,46,0.5385,0.4321,0.4795], index=['total','true positives','false positives','False negatives','precision','recall','f-measure']),
#                'MeSH':pd.Series([81,35,30,46,0.5692,0.4568,0.5068], index=['total','true positives','false positives','False negatives','precision','recall','f-measure']),
#                'SPECIALIST':pd.Series([81,35,30,46,0.8889,0.4938,0.6349], index=['total','true positives','false positives','False negatives','precision','recall','f-measure'])}
#        
#    data2 = {'Precision':pd.Series([0.5385,0.5692,0.8889], index=['S-Match','MeSH','SPECIALIST']),
#            'Recall':pd.Series([0.4321,0.4568,0.4938], index=['S-Match','MeSH','SPECIALIST']),
#            'f-measure':pd.Series([0.4795,0.5068,0.6349], index=['S-Match','MeSH','SPECIALIST'])}
    pd.set_option('precision', 2)
    df = pd.DataFrame(data)

    return df
# =============================================================================
# Generate graphics            
# =============================================================================

def generateGraphics(dataFrame,color,experiment):

#    dataFrame.plot.bar()
#        sbn.heatmap(dataFrame, cmap="Blues",annot=True, fmt=".3f")
    heatmap = sbn.heatmap(dataFrame, cmap=color,annot=True, fmt=".3f",vmin=0.3,vmax=0.85) #vmin=0, vmax=1
    plt.tight_layout()
    plt.savefig("/Users/francis/Desktop/fig"+experiment+".png")
    plt.close()

    return


def generatePieChart():
    
    
    
    
#    labels = ['Oxygen','Hydrogen','Carbon_Dioxide','Nitrogen']
#    values = [4500,2500,1053,500]
#
#    trace = go.Pie(labels=labels, values=values)
#    py.iplot([trace], filename='"/Users/francis/Desktop/basic_pie_chart')
#    plt.tight_layout()
#    plt.savefig("/Users/francis/Desktop/fig_Piechart.png")
#    plt.close()
    import matplotlib.ticker as ticker
    import matplotlib.cm as cm
    import matplotlib as mpl
    from matplotlib.gridspec import GridSpec

    import matplotlib.pyplot as plt
    #credit https://matplotlib.org/devdocs/gallery/pie_and_polar_charts/pie_demo2.html#sphx-glr-gallery-pie-and-polar-charts-pie-demo2-py
    
    mpl.rcParams['font.size'] = 15.0

    source_labels = ['Lack of Knoweldge','Complex grammar & \n Matcher limitations','String \n Parsing']
    source_counts = [28,6,4]

    
    # Make square figures and axes
    plt.figure(1, figsize=(10,10))
    the_grid = GridSpec(3, 3)
    

    cmap = plt.get_cmap('tab20c')
    colors = [cmap(i) for i in np.linspace(0, 1, 8)]


  
    source_pie = plt.pie(source_counts, labels=source_labels, autopct='%1.1f%%', shadow=False, colors=colors)


#    plt.show()
    plt.tight_layout()
    plt.savefig("/Users/francis/Desktop/fig_Piechart.png")
    plt.close()
    
    
    return

# =============================================================================
# Extract Results            
# =============================================================================

def extractResults():
    
    mypath = '/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/evaluationResults/'
#    resultsFile = open("/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/evaluationResults/Experiment1_3results.xml")

    experiments = extractExperimentFiles(extractResultsFiles(mypath))
    print (experiments) 
 #   matcherNames = ['S-Match Minimal', 'S-Match\n(Lexicon extension)','S-Match\n(Grammar extension)','S-Match\n(Both extensions)']
    matcherNames = ['LogMap', 'LogMap\n(Lexicon extension)','LogMap\n(Grammar extension)','LogMap\n(Both extensions)']

 #   matcherNames = ['S-Match', 'S-Match\n(Symbolic extension)','S-Match\n(Grammar extension)','LogMap', 'LogMap\n(Symbolic extension)','LogMap\n(Grammar extension)']
#    matcherNames = ['S-Match\n Gabor', 'S-Match\n(SPECIALIST) Gabor','S-Match', 'S-Match\n(SPECIALIST)', 'other']
#    matcherNames = ['S-Match-Top\n', 'S-Match-Top\n(MeSH)','S-Match-Top\n(SPECIALIST)','S-Match-Disorder\n', 'S-Match-Disorder\n(MeSH)','S-Match-Disorder\n(SPECIALIST)','S-Match-Condition\n', 'S-Match-Condition\n(MeSH)','S-Match-Condition\n(SPECIALIST)', 'LogMap', 'LogMap\n(MeSH)','LogMap\n(SPECIALIST)', 'S-Match\n(MeSH+SPECIALIST)']
    
    data = {}
    param = {}
    
#    colormaps = np.array(['Blues','Greens','Oranges', 'Reds', 'icefire'])
    colormaps = np.array(['Blues','Blues','Blues', 'Blues', 'Blues'])
    experimentNumber = 0
    for experiment in experiments:
        precisions = {}
        recalls = {}
        fmeasures = {}
        totals = {}
        fPositives = {}
        fNegatives = {}
        tPositives = {}
        for matcherList in experiments[experiment]:
            for matcher in matcherList:
                name= matcherNames[int(matcher)-1]
                experimentPath = "/Users/francis/Development/eclipse-workspace/diseasesClassification/Resources/evaluationResults/"+experiment+"_"+matcher+"results.xml"
                experimentFile = open(experimentPath)
                for line in experimentFile:
                    fields = line.split(" ")
                    param = fields[0]
                    value = fields[2]
                    if (param == "Precision"):
            #            precisions[name] = "{0:.3f}".format(float(removeJump(value)))
                        precisions[name] = round(float(removeJump(value)),3)
                    elif (param == "Recall"):
                        recalls[name] = round(float(value),3)
                    elif (param == "F-measure"):
                        fmeasures[name] = round(float(removeJump(value)),3)
                    elif (param == "Total"):
                        totals[name] = int(removeJump(value))
                    elif (param == "TruePositives"):
                        tPositives[name] = int(removeJump(value))
                    elif (param == "FalsePositives"):
                        fPositives[name] = int(removeJump(value))
                    elif (param == "FalseNegatives"):
                        fNegatives[name] = int(removeJump(value))   
                experimentFile.close
            data = {'Precision':precisions, 'Recall':recalls, 'F-measure':fmeasures}
            param = {'Total':totals, 'True Positives':tPositives, 'False Positives':fPositives, 'False negatives':fNegatives}
        print (data)
        print (param)
        color = colormaps[experimentNumber]            
        generateGraphics(generateTable(data), color,experiment) 
        experimentNumber = experimentNumber + 1
        tableFile = open("../evaluationResults/table"+experiment+".tex","w", encoding = "utf8")
        df = pd.DataFrame(param)
        tableFile.write(df.to_latex())
        tableFile.close
    #    dataFrame.plot.bar()
    #        sbn.heatmap(dataFrame, cmap="Blues",annot=True, fmt=".3f")
    
    return


# =============================================================================
# Extract Results Files             
# =============================================================================

def extractResultsFiles(mypath):
    onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
    resultfiles = []
    for file in onlyfiles:
        if(file[13:-4]== 'results'): # This remove the words 'Experiment' and '.xml'  from the file name
            resultfiles.append(file)
        
    return resultfiles
    

# =============================================================================
# Extract Experiment Files         
# =============================================================================

def extractExperimentFiles(resultFiles):
    experiments = {}
    
    for file in resultFiles:
        experimentName = file[:11]
        matcher = [file[12:13]]
        if (experimentName in experiments):
            experiments[experimentName].append(matcher)
        else:
            experiments[experimentName] = []
            experiments[experimentName].append(matcher)
        
    return experiments

 

# =============================================================================
# Main            
# =============================================================================


#def main(argv):
#   inputfile1 = ''
#   inputfile2 = ''
#   name=''
#
#   try:
#      opts, args = getopt.getopt(argv,"h:a:b:n:g:",["goldStandardFile=","mappingsFile=","experimentName=","graphicsName="])
#   except getopt.GetoptError:
#      print ('evaluate.py -a <goldStandardFile> -b <mappingsFile> -n <experimentName>')
#      sys.exit(3)
#   for opt, arg in opts:
#      if opt == '-h':
#         print ('test.py -a <inputfile1> -b <inputfile2> -n <experimentName>')
#         sys.exit()
#      elif opt in ("-a", "--ifile1"):
#         inputfile1 = arg
#         
#      elif opt in ("-b", "--ifile2"):
#         inputfile2 = arg
#         
#      elif opt in ("-n", "--name"):
#         name = arg
#         
#      elif opt == "-g":
#         extractResults()
#         
#   goldStandard = loadGoldStandard(inputfile1)   
#   matching = loadMatching(inputfile2)
#   computeSimilarity(goldStandard, matching, name)  
#   print (name, "done")
#
#if __name__ == "__main__":
#   main(sys.argv[1:])            
#          
extractResults()      

#generatePieChart()