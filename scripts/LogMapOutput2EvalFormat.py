

def removeDash(str):
    str=str.replace("_"," ")    
	
    return str


def extractDescription(str):
    substrings = str.split("#")                       
    desc = substrings[1]
    descLimpio = desc[:-4]    
    return descLimpio

lineas = [] # this is an empty list called lines

        
#archivo = open("//Users/francis/MarieCurieESR/PhD/Domain-aware matching/Medical Domain Experiments/Results/LogMap/mappings-2.rdf")

#archivo = open("LogMap/logmap2_mappingsLargeMeSH.rdf")
#f = open("LogMap/DSM5VsICD10logMapWithMesh(noCommas)Large.xml","w", encoding = "utf8")
#
#for linea in archivo:
#   if "<entity1" in linea:
#       
#       descripcion = extractDescription(linea)
#       descripcionSinGuion = removeDash(descripcion) 
#       
#       f.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
#       
#   if "<entity2" in linea:
#       
#       descripcion2 = extractDescription(linea)
#       descripcionSinGuion2 = removeDash(descripcion2) 
#       f.write("\\Top\\"+descripcionSinGuion2+"\n")
#        
#f.close

#archivo = open("LogMap/logmap2_mappingsLargeSinDiversicon.rdf")
#f2 = open("LogMap/DSM5VsICD10logMap(noCommas)Large.xml","w", encoding = "utf8")
#
#for linea in archivo:
#   if "<entity1" in linea:
#       print("mierda")
#       descripcion = extractDescription(linea)
#       descripcionSinGuion = removeDash(descripcion) 
#       f2.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
#       
#   if "<entity2" in linea:
#       descripcion2 = extractDescription(linea)
#       descripcionSinGuion2 = removeDash(descripcion2) 
#       f2.write("\\Top\\"+descripcionSinGuion2+"\n")
#
#f2.close
#
#archivo = open("LogMap/logmap2_mappingsLargeSPECIALIST.rdf")
#f3 = open("LogMap/DSM5VsICD10logMapSpecialistLexicon(noCommas)Large-v2.xml","w", encoding = "utf8")
#
#for linea in archivo:
#   if "<entity1" in linea:
#       descripcion = extractDescription(linea)
#       descripcionSinGuion = removeDash(descripcion) 
#       
#       f3.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
#       
#   if "<entity2" in linea:
#       descripcion2 = extractDescription(linea)
#       descripcionSinGuion2 = removeDash(descripcion2) 
#       f3.write("\\Top\\"+descripcionSinGuion2+"\n")
#        
#f3.close
#
#archivo = open("LogMap/logmap2_mappingsSmallMeSH.rdf")
#f4 = open("LogMap/DSM5VsICD10logMapWithMeSH(noCommas).xml","w", encoding = "utf8")
#
#for linea in archivo:
#   if "<entity1" in linea:
#       descripcion = extractDescription(linea)
#       descripcionSinGuion = removeDash(descripcion) 
#       f4.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
#       
#   if "<entity2" in linea:
#       descripcion2 = extractDescription(linea)
#       descripcionSinGuion2 = removeDash(descripcion2) 
#       f4.write("\\Top\\"+descripcionSinGuion2+"\n")
#
#f4.close


#archivo = open("LogMap/logmap2_mappingsSmallSinDiversicon.rdf")
#f5 = open("LogMap/DSM5VsICD10logMap(noCommas)v1.xml","w", encoding = "utf8")
#
#for linea in archivo:
#   if "<entity1" in linea:
#       descripcion = extractDescription(linea)
#       descripcionSinGuion = removeDash(descripcion) 
#       f5.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
#       
#   if "<entity2" in linea:
#       descripcion2 = extractDescription(linea)
#       descripcionSinGuion2 = removeDash(descripcion2) 
#       f5.write("\\Top\\"+descripcionSinGuion2+"\n")
#        
#f5.close
#
archivo = open("LogMap/FINAL/logmap2_mappingsDSM5vsICD10SymbolicGrammar.rdf")
f6 = open("LogMap/FINAL/logmap2_mappingsDSM5vsICD10SymbolicGrammar.xml","w", encoding = "utf8")

for linea in archivo:
   if "<entity1" in linea:
       descripcion = extractDescription(linea)
       print('ent1:', descripcion)
       descripcionSinGuion = removeDash(descripcion)
       print('ent1.1:', descripcionSinGuion)
       f6.write("\\Top\\"+descripcionSinGuion+"\t=\t")       
       
   if "<entity2" in linea:
       descripcion2 = extractDescription(linea)
       print('ent2:', descripcion2)
       descripcionSinGuion2 = removeDash(descripcion2) 
       print('ent2.1:', descripcionSinGuion2)
       f6.write("\\Top\\"+descripcionSinGuion2+"\n")

f6.close