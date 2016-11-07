#
# Python script combining translated properties files
# for muilti language project.
#
# Usage as command line tool:
#
# python props.py [-options]
# or
# java -jar jython.jar props.py [-options]
#
# where options include:
#
# -f or -folder	path to a folder having source untranslated or partually rtanslated property files
# -t or -translated path to a folder containing newly translated property files
# -r or -result path to a folder where to generate results and translation check reports
# -h or -header path to a textual file header to be included in the beginning of every property file
#
# lukasal 03/11/2016
#

import os
import sys
import re
from os.path import basename

# simply prints line on screen
def trace(msg):
    sys.stdout.write(msg+"\n")
    sys.stdout.flush()
    return

# opens file with writing permission
# creates empty file including folder structure if file not exists 
def towrite(filename):
    if not os.path.exists(os.path.dirname(filename)):
        try:
            os.makedirs(os.path.dirname(filename))
        except OSError as exc:
            raise
    return open(filename, "w")

# load properties key=value ignoring comments from property file into a hash map
def loadProps(fileName, lang, globalProps):
    props = {}
    with open(fileName, "r") as f:
        for line in f:
            line = line.strip()
            if not line.startswith("#") and len(line) > 0:
                try:
                    key,val = line.split("=",2)
                except ValueError:
                    trace("Error on reading line: " + line)

                if key[0].isupper(): trace("Warning. Uppercase in label key found " + key)
                props[key] = val
                if globalProps != None:
                    if key not in globalProps:
                        globalProps[key] = {lang : True}
                    else: globalProps[key][lang] = True
    return props

# load properties from muiltipe property files presented in given folder and sort them by language
def loadTranslations(folderName, loadDefault = True, globalProps=None):
    globalProps = {} if globalProps == None else globalProps
    propsByLang = {}

    if loadDefault:
        propsByLang["MS"] = loadProps(os.path.join(folderName, "messages.properties"), "MS", globalProps)
        trace("[" + str(len(propsByLang["MS"])) + " lines]" + " messages.properties loaded")

    for propsFile in [f for f in os.listdir(folderName + "\\.")]:
        if "_" in propsFile:
            lang = re.search(r'\_(.*)\.', propsFile).group(1);
            propsByLang[lang] = loadProps(os.path.join(folderName, propsFile), lang, globalProps)
            trace("[" + str(len(propsByLang[lang])) + " lines]" + " language " + lang + " loaded")

    langs = propsByLang.keys() 	# it is nice to have sorted
    langs.sort()		# list of languages including MS as master property
    return propsByLang, langs, globalProps

# performs merging between untranslated or partually translated properties files and newly translated properties files
def mergeTranslations(headerFile, resultFolder, langs, translByLang, propsByLang, globalProps):

    with open(headerFile) as f: headerText = f.read()

    resultFiles = {}
    resultFiles["MS"] = towrite(os.path.abspath(os.path.join(resultFolder, "messages.properties")))
    resultFiles["MS"].write(headerText + "\n\n")

    for lang in langs: 
        if lang != "MS":
            resultFiles[lang] = towrite(os.path.abspath(os.path.join(resultFolder, "messages_" + lang  + ".properties")))
            resultFiles[lang].write(headerText + "\n\n")

    fullList = globalProps.keys()
    fullList.sort()

    for key in fullList:
        translated = ""
        misingTranslation = ""
        for lang in langs:
            if lang != "MS":                     
                if key in translByLang[lang]:
                    resultFiles[lang].write(key + "=" + translByLang[lang][key]+"\n")
                    translated += ("" if translated=="" else ",") + lang
                elif key in propsByLang[lang]:
                    resultFiles[lang].write(key + "=" + propsByLang[lang][key]+"\n")
                    translated += ("" if translated=="" else ",") + lang
                elif key in propsByLang["MS"]:
                    misingTranslation += ("" if misingTranslation=="" else ",")+ lang
                else:    
                     trace("Error. Translation for " + key + " not found")

        if len(misingTranslation) > 0 and len(translated) > 0:
            if misingTranslation == "en":
                resultFiles["en"].write(key + "=" + propsByLang["MS"][key]+"\n")
            else:
                trace("Error. Property " + key + " has missing translations for some languages " + misingTranslation )

        elif len(misingTranslation) > 0 and len(translated) == 0:
            resultFiles["MS"].write(key + "=" + propsByLang["MS"][key]+"\n")

    for lang in langs: 
        resultFiles[lang].flush()
        resultFiles[lang].close()

# generates report indicating partually translated properties
def checkReport(reportFileName, langs, propsByLang, globalProps):
    checkList = []
    for key in globalProps: 
        keyFlag = True
        for lang in propsByLang: 
            if lang not in globalProps[key]: 
                checkList.append(key)
                break;
    checkList.sort()
    
    report = towrite(os.path.abspath(reportFileName))
    report.write(" ".join(langs) + "\n" ) # write report file header with all languages

    for key in checkList: # write list of keys distributed by avaliable langugage translation
        keyLine = ""
        for lang in langs:
            if lang in globalProps[key]: keyLine += "   "
            else: keyLine += " x "
        report.write(keyLine + "    " + key + "\n")


# main method with all application logic inside
def MAIN(argv):    
                    
    headerFile = ""
    propsFolder = ""
    resultFolder = ""
    translatedFolder = ""

    for i in range(len(argv)):
        if argv[i] == "-header" or argv[i] == "-h": headerFile = os.path.abspath(argv[i+1])
        if argv[i] == "-folder" or argv[i] == "-f": propsFolder = os.path.abspath(argv[i+1])
        if argv[i] == "-result" or argv[i] == "-r": resultFolder = os.path.abspath(argv[i+1])
        if argv[i] == "-translated" or argv[i] == "-t": translatedFolder = os.path.abspath(argv[i+1])

    trace("Load properties from " + propsFolder)    
    propsByLang, langs, globalProps = loadTranslations(propsFolder)

    trace("Check properties...")
    checkReport(os.path.join(resultFolder, "sourceTest.txt"), langs, propsByLang, globalProps)

    trace("Load translations...")
    translByLang, _, _ = loadTranslations(translatedFolder, False)

    trace("Merge translations...")        
    mergeTranslations(headerFile, resultFolder, langs, translByLang, propsByLang, globalProps)

    trace("Load translated files from " + resultFolder)    
    propsByLang, langs, globalProps = loadTranslations(resultFolder)

    trace("Global property check...")
    checkReport(os.path.join(resultFolder, "translationTest.txt"), langs, propsByLang, globalProps)


MAIN(sys.argv)	# run main
sys.exit(0) 	# exit with ok
