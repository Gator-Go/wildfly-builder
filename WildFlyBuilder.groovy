#!/usr/bin/env groovy
/**
 * WildFlyBuilder.groovy
 * Main entry point for the WildFly software factory.
 */
println "🚀 Starting WildFlyBuilder..."
import groovy.xml.XmlParser
// ====================== CONFIG LOADING ======================
def loadConfigs(String buildDir = './build/', String optionsDir = './options/') {
    def parser = new XmlParser()
    def configs = [:]
    // Builder core definitions
    configs.appProcs = parser.parseText(new File("${buildDir}APP_PROCS.xml").text)
    configs.appFuncs = parser.parseText(new File("${buildDir}APP_FUNCS.xml").text)
    configs.appCodes = parser.parseText(new File("${buildDir}APP_CODES.xml").text)
    configs.appEventCodes = parser.parseText(new File("${buildDir}APP_EVENT_CODES.xml").text)
    configs.appApps = parser.parseText(new File("${buildDir}APP_APPS.xml").text)
    // App-specific metadata
    configs.appNames = parser.parseText(new File("${optionsDir}APP_NAMES.xml").text)
    configs.appTables = parser.parseText(new File("${optionsDir}APP_TABLES.xml").text)
    configs.appEnums = parser.parseText(new File("${optionsDir}APP_ENUMS.xml").text)
    configs.appEvents = parser.parseText(new File("${optionsDir}APP_EVENTS.xml").text)
    configs.appCodeTypes = parser.parseText(new File("${optionsDir}APP_CODE_TYPES.xml").text)
    println "✅ Loaded configs: ${configs.keySet()}"
    return configs
}
// ====================== HELPER FUNCTIONS ======================
def insertAppPackages(String theText, def configs) {
    def result = theText
    configs.appNames.appPackage.each { p ->
        result = result.replace(p.'@oldPackage' as String, p.'@newPackage' as String)
    }
    result
}
def insertAppNames(String theText, def configs) {
    def result = theText
    configs.appNames.appName.each { n ->
        result = result.replace(n.'@oldName' as String, n.'@newName' as String)
    }
    result
}
def insertAppCopyrights(String theText, def configs) {
    def result = theText
    configs.appNames.appCopyright.each { n ->
        def newCopyright = (n.'@newCopyright' as String)
            .replace('\\n', System.lineSeparator())

        result = result.replace(n.'@oldCopyright' as String, newCopyright)
    }
    result
}
def insertAppAuthors(String theText, def configs) {
    def result = theText
    configs.appNames.appAuthor.each { n ->
        result = result.replace(n.'@oldAuthor' as String, n.'@newAuthor' as String)
    }
    result
}
def applyTablePlaceholders(String text, def table) {
    text.replace("yyyyys", table.'@objPlural' ?: "")
       .replace("Yyyyys", table.'@classPlural' ?: "")
       .replace("yyyyy", table.'@objName' ?: "")
       .replace("Yyyyy", table.'@className' ?: "")
       .replace("YYYYY", table.'@dbName' ?: "")
       .replace("DDDDD", table.'@displayName' ?: "")
       .replace("yfyfy", table.'@fileName' ?: "")
}
def cleanSkipAndDivs(String text) {
    def result = new StringBuilder()
    def titTat = "tit"
    text.eachLine { line ->
        def t = line.trim()
        if (t == "___skip___") return
        if (t == "___DIV_CLASS___") {
            result << (titTat == "tit" ? "<div class='pojo-section-alternate'>" : "<div class='pojo-section-body'>") << '\r\n'
            titTat = (titTat == "tit") ? "tat" : "tit"
        } else {
            result << line << '\r\n'
        }
    }
    result.toString()
}
def insertTableNames(String text, def configs) {
    def result = text
    for (n in configs.appTables.appTable) {
        result = result
            .replace("yyyyys", n.'@objPlural' ?: "")
            .replace("Yyyyys", n.'@classPlural' ?: "")
            .replace("yyyyy", n.'@objName' ?: "")
            .replace("Yyyyy", n.'@className' ?: "")
            .replace("YYYYY", n.'@dbName' ?: "")
            .replace("yfyfy", n.'@fileName' ?: "")
            .replace("DDDDD", n.'@displayName' ?: "")
    }
    result
}
/** Apply field-specific placeholders */
def applyFieldPlaceholders(String text, def field) {
    text.replace("FFFFF", field.'@dbName' ?: "")
       .replace("Fffff", field.'@classFieldName' ?: "")
       .replace("fffff", field.'@objFieldName' ?: "")
       .replace("ttttt", field.'@fieldType' ?: "")
       .replace("sssss", field.'@fieldSize' ?: "")
       .replace("ddddd", field.'@scale' ?: "")
}
//
// ====================== OTHER PROCESSORS ======================
//
/**
 * Builds the JSP list heading code by collecting list fields,
 * sorting them by order, and inserting the appropriate code templates.
 */
def loadJspListHeading(String theTable, def configs) {
    def fields = collectListFields(theTable, configs)
    if (fields.isEmpty()) return ""
    def theCode = new StringBuilder()
    for (fieldData in fields) {
        def codeKey = getJspListHeadCodeKey(fieldData.theType)
        if (codeKey) {
            def insertCode = createCode(theTable, codeKey, fieldData.theName, configs)
            theCode << insertCode
        }
    }
    theCode.toString()
}
/**
 * Builds the JSP list body code by collecting list fields,
 * sorting them by order, and inserting the appropriate code templates.
 */
def loadJspListBody(String theTable, def configs) {
    def fields = collectListFields(theTable, configs)
    if (fields.isEmpty()) return ""
    def theCode = new StringBuilder()
    for (fieldData in fields) {
        def codeKey = getJspListBodyCodeKey(fieldData.theType)
        if (codeKey) {
            def insertCode = createCode(theTable, codeKey, fieldData.theName, configs)
            theCode << insertCode
        }
    }
    theCode.toString()
}
/** Collect and sort fields marked with listFlag == "yes" */
def collectListFields(String theTable, def configs) {
    def sortClass = []
    for (tableNode in configs.appTables.appTable) {
        if (tableNode.'@className' != theTable) continue
        for (f in tableNode.appField) {
            if (f.'@listFlag' == "yes") {
                sortClass << [
                    theOrder: f.'@listOrder' as int,
                    theType: f.'@codeType',
                    theName: f.'@classFieldName'
                ]
            }
        }
    }
    sortClass.sort { it.theOrder }
    sortClass
}
/** Maps field type to the corresponding JSP list head code key */
def getJspListHeadCodeKey(String fieldType) {
    switch (fieldType) {
        case "String": return "___JSP_LIST_HEAD_STRING___"
        case "Enum": return "___JSP_LIST_HEAD_ENUM___"
        case "Clob": return "___JSP_LIST_HEAD_CLOB___"
        case "Integer": return "___JSP_LIST_HEAD_INTEGER___"
        case "Long": return "___JSP_LIST_HEAD_LONG___"
        case "Double": return "___JSP_LIST_HEAD_DOUBLE___"
        case "BigDecimal": return "___JSP_LIST_HEAD_BIG_DECIMAL___"
        case "Money": return "___JSP_LIST_HEAD_MONEY___"
        case "Loc": return "___JSP_LIST_HEAD_LOC___"
        case "CurrentLoc": return "___JSP_LIST_HEAD_CURRENT_LOC___"
        case "Date": return "___JSP_LIST_HEAD_DATE___"
        case "DateTime": return "___JSP_LIST_HEAD_DATE_TIME___"
        case "Camera": return "___JSP_LIST_HEAD_CAMERA___"
        case "Video": return "___JSP_LIST_HEAD_VIDEO___"
        case "Thumbnail": return "___JSP_LIST_HEAD_THUMBNAIL___"
        case "Post": return "___JSP_LIST_HEAD_POST___"
        case "One2ManyParent": return "___JSP_LIST_HEAD_ONE2MANY_PARENT___"
        case "One2ManyParentAlert": return "___JSP_LIST_HEAD_ONE2MANY_PARENT_ALERT___"
        case "One2ManyChild": return "___JSP_LIST_HEAD_ONE2MANY_CHILD___"
        case "One2ManyChildAlert": return "___JSP_LIST_HEAD_ONE2MANY_CHILD_ALERT___"
        default: return null
    }
}
/** Maps field type to the corresponding JSP list BODY code key */
def getJspListBodyCodeKey(String fieldType) {
    switch (fieldType) {
        case "String": return "___JSP_LIST_BODY_STRING___"
        case "Enum": return "___JSP_LIST_BODY_ENUM___"
        case "Clob": return "___JSP_LIST_BODY_CLOB___"
        case "Integer": return "___JSP_LIST_BODY_INTEGER___"
        case "Long": return "___JSP_LIST_BODY_LONG___"
        case "Double": return "___JSP_LIST_BODY_DOUBLE___"
        case "BigDecimal": return "___JSP_LIST_BODY_BIG_DECIMAL___"
        case "Money": return "___JSP_LIST_BODY_MONEY___"
        case "Loc": return "___JSP_LIST_BODY_LOC___"
        case "CurrentLoc": return "___JSP_LIST_BODY_CURRENT_LOC___"
        case "Date": return "___JSP_LIST_BODY_DATE___"
        case "DateTime": return "___JSP_LIST_BODY_DATE_TIME___"
        case "Camera": return "___JSP_LIST_BODY_CAMERA___"
        case "Video": return "___JSP_LIST_BODY_VIDEO___"
        case "Thumbnail": return "___JSP_LIST_BODY_THUMBNAIL___"
        case "Post": return "___JSP_LIST_BODY_POST___"
        case "One2ManyParent": return "___JSP_LIST_BODY_ONE2MANY_PARENT___"
        case "One2ManyParentAlert": return "___JSP_LIST_BODY_ONE2MANY_PARENT_ALERT___"
        case "One2ManyChild": return "___JSP_LIST_BODY_ONE2MANY_CHILD___"
        case "One2ManyChildAlert": return "___JSP_LIST_BODY_ONE2MANY_CHILD_ALERT___"
        default: return null
    }
}
/**
 * Builds a simple text-based list heading (e.g. " ID | Name | Date | ...")
 * Excludes media-heavy types like Camera, Video, etc.
 */
def loadListHeading(String theTable, def configs) {
    def fields = collectListHeadingFields(theTable, configs)
    if (fields.isEmpty()) return " ID"
    def theCode = new StringBuilder(" ID")
    for (fieldData in fields) {
        theCode << " | " << (fieldData.theData ?: "")
    }
    theCode.toString()
}
/** Collect fields for list heading (excludes media types) */
def collectListHeadingFields(String theTable, def configs) {
    def sortClass = []
    for (tableNode in configs.appTables.appTable) {
        if (tableNode.'@className' != theTable) continue
        for (f in tableNode.appField) {
            if (f.'@listFlag' == "yes" &&
                !["Camera", "Post", "Video", "Thumbnail"].contains(f.'@codeType')) {

                sortClass << [
                    theOrder: (f.'@listOrder' as int) ?: 0,
                    theData: f.'@displayName'
                ]
            }
        }
    }
    sortClass.sort { it.theOrder }
    sortClass
}
/**
 * Builds format string and argument list for list view formatting
 * (e.g. ""%d | %s | %.2f", obj.getId(), obj.getName(), ...")
 */
def loadListFields(String theTable, def configs) {
    def fields = collectListFieldsForDisplay(theTable, configs)
    if (fields.isEmpty()) {
        return "\"%d',\r\n obj.getId()"
    }
    def theTypeS = new StringBuilder("\"%d")
    def theNameS = new StringBuilder(" obj.getId()")
    for (fieldData in fields) {
        theTypeS << " | "
        theNameS << ", "
        switch (fieldData.theType) {
            case "Date":
                theTypeS << "%s"
                theNameS << "formatDate.format(obj.get" << fieldData.theName << "())"
                break
            case "DateTime":
                theTypeS << "%s"
                theNameS << "formatDateTime.format(obj.get" << fieldData.theName << "())"
                break
            case "Money":
                theTypeS << "%s"
                theNameS << "DecimalFormat.getCurrencyInstance().format(obj.get" << fieldData.theName << "())"
                break
            default:
                theTypeS << (fieldData.theFormat ?: "%s")
                theNameS << "obj.get" << fieldData.theName << "()"
        }
    }
    theTypeS << "\","
    theTypeS << '\r\n' << theNameS
    theTypeS.toString()
}
/**
 * Builds display format string and arguments for detail/view pages.
 * Example: ""%s %s %s", name, date, amount"
 */
def loadDisplayFields(String theTable, def configs) {
    def fields = collectListFieldsForDisplay(theTable, configs)
    if (fields.isEmpty()) {
        return "\"\",\r\n "
    }
    def theTypeS = new StringBuilder("\"")
    def theNameS = new StringBuilder(" ")
    boolean first = true
    for (fieldData in fields) {
        if (!first) {
            theTypeS << " "
            theNameS << ", "
        }
        first = false
        switch (fieldData.theType) {
            case "Date":
                theTypeS << "%s"
                theNameS << "formatDate.format(" << fieldData.theName << ")"
                break
            case "DateTime":
                theTypeS << "%s"
                theNameS << "formatDateTime.format(" << fieldData.theName << ")"
                break
            case "Money":
                theTypeS << "%s"
                theNameS << "DecimalFormat.getCurrencyInstance().format(" << fieldData.theName << ")"
                break
            default:
                theTypeS << (fieldData.theFormat ?: "%s")
                theNameS << fieldData.theName
        }
    }
    theTypeS << "\","
    theTypeS << '\r\n' << theNameS
    theTypeS.toString()
}
/** collector for list fields (excludes media types) */
def collectListFieldsForDisplay(String theTable, def configs) {
    def sortClass = []
    for (tableNode in configs.appTables.appTable) {
        if (tableNode.'@className' != theTable) continue
        for (f in tableNode.appField) {
            if (f.'@listFlag' == "yes" &&
                !["Camera", "Post", "Video", "Thumbnail"].contains(f.'@codeType')) {

                sortClass << [
                    theOrder: (f.'@listOrder' as int) ?: 0,
                    theType: f.'@codeType',
                    theName: f.'@classFieldName',
                    theFormat: f.'@format'
                ]
            }
        }
    }
    sortClass.sort { it.theOrder }
    sortClass
}
// ====================== CODE BLOCK GENERATOR ======================
/**
 * Core code block generator - processes templates from APP_CODES.xml
 * and applies field/table specific replacements + sequence logic.
 */
def createCode(String theTable, String theCodeKey, String theField, def configs) {
    def myReturn = '\r'
    def newLine = '\n'
    def myCode = new StringBuilder()
    // Initialize sequence counters (reset per table is done in createMultiFilesProc)
    if (!binding.hasVariable('seq3nbr')) seq3nbr = 1
    if (!binding.hasVariable('daoSetSeq')) daoSetSeq = 1
    if (!binding.hasVariable('staticSetSeq')) staticSetSeq = 700
    for (c in configs.appCodes.appCode) {
        if (c.'@codeKey' != theCodeKey) continue
        def codeType = c.'@codeType'
        def baseTemplate = buildBaseTemplate(c, myReturn, newLine)
        for (tableNode in configs.appTables.appTable) {
            if (tableNode.'@className' != theTable) continue
            for (f in tableNode.appField) {
                if (!shouldApplyToField(codeType, theField, f)) continue
                def processed = processFieldTemplate(baseTemplate, f, configs)
                myCode << processed
            }
        }
    }
    myCode.toString()
}
def buildBaseTemplate(def codeNode, String myReturn, String newLine) {
    def theCode = new StringBuilder()
    for (cc in codeNode.code) {
        def indentLevel = (cc.'@indent' as int) ?: 0
        def indent = " " * indentLevel
        theCode << indent << cc.text() << myReturn << newLine
    }
    theCode.toString()
}
def shouldApplyToField(String codeType, String theField, def field) {
    (codeType == "ALL" || codeType == field.'@codeType') &&
    (theField == "ALL" || theField == field.'@classFieldName')
}
def processFieldTemplate(String template, def f, def configs) {
    def oldCode = template
    // Special sections
    if (oldCode.contains("___LOAD_ENUMS___")) {
        def insertEnums = createEnums(f.'@classFieldName', configs)
        oldCode = oldCode.replace("___LOAD_ENUMS___", insertEnums)
    }
    oldCode = processSequenceCounters(oldCode)
    // Field placeholders
    oldCode = oldCode
        .replace("FFFFF", f.'@dbName' ?: "")
        .replace("Fffff", f.'@classFieldName' ?: "")
        .replace("fffff", f.'@objFieldName' ?: "")
        .replace("ttttt", f.'@fieldType' ?: "")
        .replace("sssss", f.'@fieldSize' ?: "")
        .replace("ddddd", f.'@scale' ?: "")
        .replace("DFDFD", f.'@displayName' ?: "")
        .replace("BFBFB", f.'@blobNameFld' ?: "")
        .replace("FTFTF", f.'@format' ?: "")
        .replace("CPCPC", f.'@classPlural' ?: "")
        .replace("MMMMM", '$')
        .replace("AAAAA", "&")
        .replace("LLLLL", "<")
        .replace("GGGGG", ">")
    // Test data handling (if needed)
    def finalCode = new StringBuilder()
    oldCode.eachLine { line ->
        if (line.contains("___TEST_DATA___") || line.contains("___TEST_DATA2___")) {
            def newLineContent = loadTestData(line, f) // implement if you have this method
            finalCode << newLineContent << '\r\n'
        } else {
            finalCode << line << '\r\n'
        }
    }
    finalCode.toString()
}
def processSequenceCounters(String text) {
    def result = text

    // DAO sequence
    if (result.contains("___DAO_SET_SEQ___")) {
        result = result.replace("___DAO_SET_SEQ___", daoSetSeq.toString())
        daoSetSeq++
    }
    if (result.contains("___PP_DAO_SET_SEQ___")) {
        result = result.replace("___PP_DAO_SET_SEQ___", daoSetSeq.toString())
        daoSetSeq++
    }

    // Static sequences
    if (result.contains("___STATIC_SET_SEQ___")) {
        result = result.replace("___STATIC_SET_SEQ___", staticSetSeq.toString())
        staticSetSeq++
    }
    if (result.contains("___PP_STATIC_SET_SEQ___")) {
        result = result.replace("___PP_STATIC_SET_SEQ___", staticSetSeq.toString())
        staticSetSeq++
    }
    if (result.contains("___PP_PP_STATIC_SET_SEQ___")) {
        result = result.replace("___PP_PP_STATIC_SET_SEQ___", staticSetSeq.toString())
        staticSetSeq++
    }
    if (result.contains("___PP_PP_PP_STATIC_SET_SEQ___")) {
        result = result.replace("___PP_PP_PP_STATIC_SET_SEQ___", staticSetSeq.toString())
        staticSetSeq++
    }

    // *** JSP <tr> sequencing (the part that was missing) ***
    if (result.contains("___JSP_TR_START_SEQ_3___")) {
        if (seq3nbr == 1)
            result = result.replace("___JSP_TR_START_SEQ_3___", " <tr>")
        else
            result = result.replace("___JSP_TR_START_SEQ_3___", "___skip___")
    }
    if (result.contains("___JSP_TR_DETAIL_SEQ_3___")) {
        if (seq3nbr == 1)
            result = result.replace("___JSP_TR_DETAIL_SEQ_3___", " <tr class='detail' style='display:none;'>")
        else
            result = result.replace("___JSP_TR_DETAIL_SEQ_3___", "___skip___")
    }
    if (result.contains("___JSP_TR_END_SEQ_3___")) {
        if (seq3nbr == 3) {
            result = result.replace("___JSP_TR_END_SEQ_3___", " </tr>")
            seq3nbr = 1
        } else {
            result = result.replace("___JSP_TR_END_SEQ_3___", "___skip___")
            seq3nbr++
        }
    }
    if (result.contains("___JSP_TR_FINALLY_3___")) {
        if (seq3nbr == 1) {
            result = result.replace("___JSP_TR_FINALLY_3___", "")
        } else {
            result = result.replace("___JSP_TR_FINALLY_3___", " </tr>")
            seq3nbr = 1
        }
    }

    result
}
def createEnums(String theField, def configs) {
    def myReturn = '\r'
    def newLine = '\n'
    def theCode = new StringBuilder()
    for (e in configs.appEnums.appEnum) {
        if (e.'@appField' == theField) {
            def first = true
            for (ee in e.theEnum) {
                if (!first) theCode << "," << myReturn << newLine
                first = false
                theCode << " " << ee.'@classValue' << "(\"" << ee.'@codeValue' << "\")"
            }
            theCode << ";" << myReturn << newLine
            break
        }
    }
    theCode.toString()
}
// ====================== Home URL PROC (Per App) ======================
/**
 * Generates the HTML for the app home page links (multi-app launcher).
 */
def createMultiUrlProc(String fileName, String templatePath, String outputDir, def configs) {
    def theCode = new StringBuilder()
    def NL = System.lineSeparator()
    def tab = '\t'
    int count = 0
    for (node in configs.appApps.appsHome) {
        theCode << "${tab}<td><a href='${node.@appsHomeURL}' class='app-link'>"
        theCode << "<div class='app-item'>"
        theCode << "<img border='0' src='resources/gfx/${node.@appsPng}' class='app-logo'/>"
        theCode << "<span class='app-label'>${node.@appsDesc}</span>"
        theCode << "</div></a></td>"
        count++
        if (count > 3) {
            theCode << "</tr><tr>${NL}${NL}"
            count = 0
        } else {
            theCode << "${NL}${NL}"
        }
    }
    // Read template and replace placeholder
    def templateFile = new File(templatePath, fileName)
    def outputFile = new File(outputDir, fileName)
    if (templateFile.exists()) {
        def content = templateFile.text
        if (content.contains("___JSP_HOME_URLS___")) {
            content = content.replace("___JSP_HOME_URLS___", theCode.toString())
        }
        outputFile.text = content
        println " ✅ Generated multi-URL home page: ${fileName}"
    } else {
        println " ⚠️ Template not found: ${templateFile}"
    }
}
// ====================== Event PROC (Per file) ======================
/**
 * Generates code snippets for events and their parameters by replacing placeholders.
 */
def createEventCode(def appEvent, def appEventCode) {
    def codeType = appEventCode.'@codeType'
    if (codeType != "Event" && codeType != "Parameter") {
        return ""
    }
    def myReturn = '\r'
    def newLine = '\n'
    def codeBuilder = new StringBuilder()
    def processCodeBlock = { def context ->
        def theCode = new StringBuilder()
        for (cc in appEventCode.code) {
            def indentLevel = (cc.'@indent' as int) ?: 0
            def indent = " " * indentLevel
            theCode << indent << cc.text() << myReturn << newLine
        }
        def codeStr = theCode.toString()
        // Common replacements
        codeStr = codeStr
            .replace("Eeeee", appEvent.'@eventName' ?: "")
            .replace("Edddd", appEvent.'@eventDescription' ?: "")
            .replace("eeeee", appEvent.'@objName' ?: "")
            .replace("LLLLL", "<")
            .replace("GGGGG", ">")
        // Parameter-specific replacements
        if (codeType == "Parameter" && context?.parameter) {
            def p = context.parameter
            codeStr = codeStr
                .replace("Ppppp", p.'@parameterName' ?: "")
                .replace("Pdddd", p.'@parameterDescription' ?: "")
                .replace("Prrrr", p.'@parameterRequired' ?: "")
        }
        codeBuilder << codeStr
    }
    if (codeType == "Event") {
        processCodeBlock(null)
    } else if (codeType == "Parameter") {
        for (p in appEvent.eventParameter) {
            processCodeBlock([parameter: p])
        }
    }
    return codeBuilder.toString()
}
/**
 * Processes a template for a SINGLE event and writes the result to a file.
 * FIXED: Now properly accepts the event node.
 */
def createFileEventProc(String fileName, String templateContent, String outputDir, def appEvent, def configs) {
    String content = templateContent
    // Insert common metadata
    content = insertAppCopyrights(content, configs) ?: content
    content = insertAppAuthors(content, configs) ?: content
    content = insertAppNames(content, configs) ?: content
    content = insertAppPackages(content, configs) ?: content
    // Common event placeholders
    content = content
        .replace("Eeeee", appEvent.'@eventName' ?: "")
        .replace("Edddd", appEvent.'@eventDescription' ?: "")
        .replace("eeeee", appEvent.'@objName' ?: "")
    // Insert dynamic code blocks
    for (c in configs.appEventCodes.appCode) {
        def codeKey = c.'@codeKey'
        if (content.contains(codeKey)) {
            def insertCode = createEventCode(appEvent, c)
            content = content.replace(codeKey, insertCode ?: "")
        }
    }
    def newFilePath = "${outputDir}/${fileName}"
    new File(newFilePath).write(content)
    println " ✅ Generated event file: ${fileName}"
}
/**
 * Processes a single template file that contains MULTIPLE events.
 * Correctly joins blocks without re-inserting the placeholder.
 */
def createFileEventProcOne(String fileName, String templatePath, String outputDir, def configs) {
    def oldFilePath = "${templatePath}/${fileName}"
    String content = new File(oldFilePath).text
    // Initial metadata (top level)
    content = insertAppCopyrights(content, configs) ?: content
    content = insertAppAuthors(content, configs) ?: content
    content = insertAppNames(content, configs) ?: content
    content = insertAppPackages(content, configs) ?: content
    for (c in configs.appEventCodes.appCode) {
        def codeKey = c.'@codeKey'
        if (!content.contains(codeKey)) continue
        def events = configs.appEvents.appEvent
        def eventBlocks = []
        for (e in events) {
            def block = createEventCode(e, c)
            // Re-apply metadata to each event block
            block = insertAppCopyrights(block, configs) ?: block
            block = insertAppAuthors(block, configs) ?: block
            block = insertAppNames(block, configs) ?: block
            block = insertAppPackages(block, configs) ?: block
            // Handle nested placeholders inside the block
            block = processNestedEventCodes(block, e, configs)
            eventBlocks << block
        }
        // Join all blocks WITHOUT re-inserting the codeKey
        def finalReplacement = eventBlocks.join("\n")
        content = content.replace(codeKey, finalReplacement)
    }
    def newFilePath = "${outputDir}/${fileName}"
    newFilePath = insertAppNames(newFilePath, configs) ?: newFilePath
    new File(newFilePath).write(content)
    println " ✅ Generated multi-event file: ${fileName}"
}
/** Handle nested code keys inside an individual event block */
def processNestedEventCodes(String code, def event, def configs) {
    def result = code
    for (nestedCode in configs.appEventCodes.appCode) {
        def nestedKey = nestedCode.'@codeKey'
        if (result.contains(nestedKey)) {
            def nestedBlock = createEventCode(event, nestedCode)
            // Re-apply metadata to nested block
            nestedBlock = insertAppCopyrights(nestedBlock, configs) ?: nestedBlock
            nestedBlock = insertAppAuthors(nestedBlock, configs) ?: nestedBlock
            nestedBlock = insertAppNames(nestedBlock, configs) ?: nestedBlock
            nestedBlock = insertAppPackages(nestedBlock, configs) ?: nestedBlock
            result = result.replace(nestedKey, nestedBlock)
        }
    }
    result
}
/**
 * Main entry point for event code generation processing.
 */
def createMultiEventProc(String fileName, String templatePath, String outputDir, def configs) {
    if (fileName.contains("Eeeee")) {
        // One file per event
        for (e in configs.appEvents.appEvent) {
            def newFileName = fileName.replace("Eeeee", e.'@eventName' ?: "UnnamedEvent")
            def templateContent = new File("${templatePath}/${fileName}").text
            createFileEventProc(newFileName, templateContent, outputDir, e, configs)
        }
    } else {
        // Single file containing all events
        createFileEventProcOne(fileName, templatePath, outputDir, configs)
    }
}
// ====================== MULTI-FILES PROC (Per Table) ======================
/**
 * Creates one generated file per table (handles ___JSP_TR* sequence placeholders, etc.)
 */
def createMultiFilesProc(String fileName, String templatePath, String outputDir, def configs) {
    def templateContent = new File("${templatePath}/${fileName}").text
    for (table in configs.appTables.appTable) {
        // IMPORTANT: Reset sequence counters for each table
        seq3nbr = 1
        daoSetSeq = 1
        staticSetSeq = 700
        def className = table.'@className'
        def newFileName = fileName
            .replace("Yyyyy", className)
            .replace("yfyfy", table.'@fileName' ?: "")
        println " 📄 Generating table file: ${newFileName}"
        def content = templateContent
        // Process ALL code blocks first (this handles ___JSP_TR_START_SEQ_3___ etc.)
        for (c in configs.appCodes.appCode) {
            def codeKey = c.'@codeKey'
            if (content.contains(codeKey)) {
                def insertCode = createCode(className, codeKey, "ALL", configs)
                content = content.replace(codeKey, insertCode ?: "___skip___")
            }
        }
        // JSP List specific sections
        if (content.contains("___JSP_LIST_HEADING___")) {
            content = content.replace("___JSP_LIST_HEADING___", loadJspListHeading(className, configs))
        }
        if (content.contains("___JSP_LIST_BODY___")) {
            content = content.replace("___JSP_LIST_BODY___", loadJspListBody(className, configs))
        }
        if (content.contains("___LOAD_LIST_HEADING___")) {
            content = content.replace("___LOAD_LIST_HEADING___", loadListHeading(className, configs))
        }
        if (content.contains("___LOAD_LIST_FIELDS___")) {
            content = content.replace("___LOAD_LIST_FIELDS___", loadListFields(className, configs))
        }
        if (content.contains("___LOAD_DISPLAY_FIELDS___")) {
            content = content.replace("___LOAD_DISPLAY_FIELDS___", loadDisplayFields(className, configs))
        }
        // Final metadata and table placeholders
        content = insertAppCopyrights(content, configs) ?: content
        content = insertAppAuthors(content, configs) ?: content
        content = insertAppNames(content, configs) ?: content
        content = insertAppPackages(content, configs) ?: content
        content = applyTablePlaceholders(content, table)
        content = cleanSkipAndDivs(content)
        new File("${outputDir}/${newFileName}").write(content)
    }
}
// ====================== MULTI-FUNCTION PROC (Per Function) ======================
/**
 * Extracts and prepares a function template from APP_FUNCS.xml.
 */
def createFunc(String funcKey, def configs) {
    def theFunc = new StringBuilder()
    def myReturn = '\r'
    def newLine = '\n'
    for (f in configs.appFuncs.appFunc) {
        if (f.'@funcKey' == funcKey) {
            for (ff in f.func) {
                def indentLevel = (ff.'@indent' as int) ?: 0
                def indent = " " * indentLevel
                theFunc << indent << ff.text() << myReturn << newLine
            }
            break
        }
    }
    // Common replacements
    return theFunc.toString()
        .replace("LLLLL", "<")
        .replace("GGGGG", ">")
        .replace("MMMMM", '$')
}
/**
 * Main processor for function-based templates (e.g. controllers, services, etc.).
 */
def createMultiFunctionProc(String fileName, String templatePath, String outputDir, def configs) {
    def allCodeTypes = configs.appCodeTypes.appCodeType.'@codeType'*.toString()
    def templateContent = new File("${templatePath}/${fileName}").text
    def newFileName = insertAppNames(fileName, configs)
    newFileName = insertTableNames(newFileName, configs) // we'll define this helper
    int jspSetSeq = 2
    int jspSetAdminSeq = 4
    def content = templateContent
    for (funcNode in configs.appFuncs.appFunc) {
        if (!allCodeTypes.contains(funcNode.'@codeType')) continue
        def funcKey = funcNode.'@funcKey'
        def codeNbr = funcNode.'@codeNbr'
        def tblOrder = funcNode.'@tblOrder' ?: "normal"
        if (!content.contains(funcKey)) continue
        def insertFunc = createFunc(funcKey, configs)
        if (codeNbr == "Table") {
            def tableList = (tblOrder == "reverse") ?
                configs.appTables.appTable.reverse() : configs.appTables.appTable
            def allReplacements = new StringBuilder()
            for (table in tableList) {
                def tableFunc = insertFunc
                tableFunc = applyCodeBlocksToContent(tableFunc, table, configs)
                tableFunc = applyTablePlaceholders(tableFunc, table)
                // Sequence counters
                if (tableFunc.contains("___JSP_SET_SEQ___")) {
                    tableFunc = tableFunc.replace("___JSP_SET_SEQ___", jspSetSeq.toString())
                    jspSetSeq++
                }
                if (tableFunc.contains("___JSP_SET_ADMIN_SEQ___")) {
                    tableFunc = tableFunc.replace("___JSP_SET_ADMIN_SEQ___", jspSetAdminSeq.toString())
                    jspSetAdminSeq++
                }
                allReplacements << tableFunc
            }
            content = content.replace(funcKey, allReplacements.toString())
        }
        else if (codeNbr == "Once") {
            def processed = insertFunc
            if (processed.contains("___JSP_SEQ___")) {
                processed = processed.replace("___JSP_SEQ___", jspSetSeq.toString())
                jspSetSeq++
            }
            if (processed.contains("___JSP_SET_SEQ___")) {
                processed = processed.replace("___JSP_SET_SEQ___", jspSetSeq.toString())
                jspSetSeq++
            }
            if (processed.contains("___JSP_SET_ADMIN_SEQ___")) {
                processed = processed.replace("___JSP_SET_ADMIN_SEQ___", jspSetAdminSeq.toString())
                jspSetAdminSeq++
            }
            content = content.replace(funcKey, processed)
        }
    }
    // Final metadata and cleanup
    content = insertAppCopyrights(content, configs) ?: content
    content = insertAppAuthors(content, configs) ?: content
    content = insertAppNames(content, configs) ?: content
    content = insertAppPackages(content, configs) ?: content
    content = insertTableNames(content, configs) ?: content
    content = cleanSkipMarkers(content)
    def finalPath = "${outputDir}/${newFileName}"
    new File(finalPath).write(content)
    println " ✅ Generated function template: ${newFileName}"
}
/** Applies all code blocks (___KEY___) inside a function template */
def applyCodeBlocksToContent(String text, def table, def configs) {
    def result = text
    for (c in configs.appCodes.appCode) {
        def codeKey = c.'@codeKey'
        if (result.contains(codeKey)) {
            def insertCode = createCode(table.'@className', codeKey, "ALL", configs)
            result = result.replace(codeKey, insertCode ?: "")
        }
    }
    result
}
/** Removes lines starting with ___ and cleans up */
def cleanSkipMarkers(String text) {
    def result = new StringBuilder()
    def myReturn = '\r'
    def newLine = '\n'
    text.eachLine { line ->
        if (!line.trim().startsWith("___")) {
            result << line << myReturn << newLine
        }
    }
    result.toString()
}
// ====================== MULTI-FILES-CODE-TYPE PROC (Per Table) ======================
/**
 * Creates one generated file per table.
 * Includes debugging for sequence placeholders.
 */
def createMultiFilesCodeTypeProc(String fileName, String templatePath, String outputDir, String codeType, def configs) {
    def templateContent = new File("${templatePath}/${fileName}").text
    for (table in configs.appTables.appTable) {
        for (field in table.appField) {
            if (field.'@codeType' != codeType) continue
            def className = table.'@className'
            def fieldName = field.'@classFieldName'
            def newFileName = fileName
                .replace("Yyyyy", className)
                .replace("Fffff", fieldName)
            println " 📄 Generating ${codeType} file: ${newFileName}"
            def content = templateContent
            // Special M2M / list handling
            if (content.contains("***LOAD_LIST_HEADING_M2M***")) {
                content = content.replace("***LOAD_LIST_HEADING_M2M***", loadListHeading(fieldName, configs))
            }
            // Apply standard metadata and placeholders
            content = insertAppCopyrights(content, configs) ?: content
            content = insertAppAuthors(content, configs) ?: content
            content = insertAppNames(content, configs) ?: content
            content = insertAppPackages(content, configs) ?: content
            content = applyTablePlaceholders(content, table)
            content = applyFieldPlaceholders(content, field)
            content = cleanSkipAndDivs(content)
            def newFilePath = "${outputDir}/${newFileName}"
            new File(newFilePath).write(content)
        }
    }
}
// ====================== DEFAULT TEMPLATE PROCESSING ======================
def defaultCodeProc(String fileName, String templatePath, String outputDir, String codeType, def configs) {
   newFileName = insertAppNames(fileName, configs)
   def oldFilePath = thePath + "/" + fileName
   def newFilePath = theDir + "/" + newFileName
   def oldFile = new File(oldFilePath).text
   def newFile = ""
   newFile = insertAppCopyrights(oldFile, configs)
   newFile = insertAppAuthors(newFile, configs)
   newFile = insertAppNames(newFile, configs)
   newFile = insertAppPackages(newFile, configs)
   new File(newFilePath).write(newFile)
                    // Default: copy with placeholder replacement
                    def content = inFile.text
                    content = insertAppPackages(content, configs)
                    content = insertAppNames(content, configs)
                    new File(targetDir, i).text = content
   println " 📝 Default processed: ${i}"
}
// ====================== MAIN TEMPLATE PROCESSING ======================
void createApp(String templatePath, String appDir, def configs) {
    def fileList = new File(templatePath).list()?.toList() ?: []
    for (String i in fileList) {
        def inFile = new File(templatePath, i)
        def targetDir = new File(appDir)
        if (inFile.directory) {
            println "📁 Processing directory: ${i}"
            // Transform directory name (this is key for xxxxx-ejb, xxxxxws-war, etc.)
            String newDirName = i
            newDirName = insertAppPackages(newDirName, configs)
            newDirName = insertAppNames(newDirName, configs)
            def newAppDir = new File(targetDir, newDirName)
            newAppDir.mkdirs()
            // Recurse into the (possibly renamed) directory
            createApp(inFile.absolutePath, newAppDir.absolutePath, configs)
        }
        else {
            println "📄 Processing file: ${i}"
            if (i.endsWithAny('.gif', '.jar', '.png', '.jpg', '.zip')) {
                // Binary files - copy as-is
                new File(targetDir, i).bytes = inFile.bytes
                println " 📦 Copied binary: ${i}"
            }
            else {
                // Text files - check for special processing
                boolean processed = false
                for (p in configs.appProcs.appProc) {
                    if (p.'@fileName' == i) {
                        def procName = p.'@procName'
                        switch (procName) {
                            case "createMultiUrlProc":
                                createMultiUrlProc(i, templatePath, appDir, configs)
                                processed = true
                                break
                            case "createMultiEventProc":
                                createMultiEventProc(i, templatePath, appDir, configs)
                                processed = true
                                break
                            case "createMultiFilesProc":
                                createMultiFilesProc(i, templatePath, appDir, configs)
                                processed = true
                                break
                            case "createMultiFunctionProc":
                                createMultiFunctionProc(i, templatePath, appDir, configs)
                                processed = true
                                break
                            case "createMultiFilesCodeTypeProc":
                                createMultiFilesCodeTypeProc(i, templatePath, appDir, p.'@codeType', configs)
                                processed = true
                                break
                           case "defaultCodeProc":
                                break
                            default:
                                println " ⚠️ Unknown proc '${procName}' for ${i}"
                        }
                        break
                    }
                }
                if (!processed) {
                    // Default: copy with placeholder replacement
                    def content = inFile.text
                    content = insertAppCopyrights(content, configs) ?: content
                    content = insertAppAuthors(content, configs) ?: content
                    content = insertAppNames(content, configs) ?: content
                    content = insertAppPackages(content, configs) ?: content
                    // Transform the filename too (e.g. xxxxx.java → MyApp.java)
                    def outputFileName = i
                    outputFileName = insertAppNames(outputFileName, configs) ?: outputFileName
                    outputFileName = insertAppPackages(outputFileName, configs) ?: outputFileName

                    def outputFile = new File(targetDir, outputFileName)
                    outputFile.text = content
                    println " 📝 Default processed: ${outputFileName}"
                }
            }
        }
    }
}
// ====================== EXECUTION ======================
def configs = loadConfigs()
def appDir = "./" + configs.appNames.appName[1].'@newName'
new File(appDir).mkdirs()
def templatePath = "./template"
createApp(templatePath, appDir, configs)
println "✅ WildFlyBuilder completed successfully!"
