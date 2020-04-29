<#ftl encoding='UTF-8'>
<?xml version="1.0" encoding="UTF-8"?>

<LexicalResource
    name="${lexicalResource_Name}"       
    prefix="${lexicalResource_Prefix}" 
    xmlns:splL="https://github.com/francisjqr/ER-Wordnet-Extension/blob/master/specialistLexicon-extension.xml"
    xmlns:wn31="https://github.com/diversicon-kb/diversicon-wordnet-3.1"
         
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:noNamespaceSchemaLocation="${lexicalResource_Name_noNamespaceSchemaLocation}">

    <GlobalInformation label="${lexicalResource_GlobalInformation}"></GlobalInformation>

    <MetaData id="${lexicalResource_MetaData_id}"
              creationDate="${lexicalResource_MetaData_creationDate}"
              creationTool="${lexicalResource_MetaData_creationTool}"
              version="${lexicalResource_MetaData_version}"   
              creationProcess="${lexicalResource_MetaData_creationProcess}"
              automatic="${lexicalResource_MetaData_automatic}"
              />    
    
    <Lexicon id="${lexicon_Id}"
             languageIdentifier="${lexicon_LanguageIdentifier}" 
             name="${lexicon_name}">
         

         <!-- Lexical entries -->   
          <#list lexicalEntries as lexicalEntry>
        <LexicalEntry id="${lexicalEntry.id}" 
            partOfSpeech="${lexicalEntry.partOfSpeech}">
            <Lemma>
                <FormRepresentation languageIdentifier="${lexicalEntry.language}" 
                    writtenForm="${lexicalEntry.writtenForm}"/>
            </Lemma>
              <#--  <#list senses as sense> -->
            <Sense id="${lexicalEntry.sense}" 
                synset="${lexicalEntry.synsetName}">
               <#--   <MonolingualExternalRef externalSystem="${lexicalEntry.externalSystem}" 
                    externalReference="${lexicalEntry.externalReference}"/> -->
            </Sense> 
          <#--  </#list> -->
        </LexicalEntry>
        </#list>
     
        
        <!-- Synsets -->
        <#list synsets as synset>
        <Synset id="${synset.id}">
            <Definition>
                <TextRepresentation languageIdentifier="${synset.languageIdentifier}" 
                    writtenText="${synset.writtenText}"/>
            </Definition>
          <#--  <MonolingualExternalRef externalSystem="${synset.externalSystem}" 
                externalReference="${synset.externalReference}"/> -->
            <#--  <#list synset.senses as sense> -->
            <SynsetRelation target="${synset.synsetRelation.target}"
                relType="${synset.synsetRelation.relType}" 
                relName="${synset.synsetRelation.relName}"/>
            <#--  </#list> -->
        </Synset>
        </#list>
                   
    </Lexicon>  
                  
</LexicalResource>