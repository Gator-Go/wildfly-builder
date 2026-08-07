#!/usr/bin/env groovy
import groovy.xml.XmlParser
import groovy.xml.XmlUtil

def APP_APPS_FILE = "./build/APP_APPS.xml"
def APP_HOME_FILE = "./options/APP_HOME.xml"

try {
    // --- Read and parse XML ---
    def appApps = new XmlParser().parse(new File(APP_APPS_FILE))
    def appHome = new XmlParser().parse(new File(APP_HOME_FILE))

    // --- Extract values from APP_HOME.xml ---
    def target = appHome.home[0]  // <home ...>
    def targetName = target.@appName
    def targetDesc = target.@appDesc
    def targetPng  = target.@appPng
    def targetURL  = target.@appHomeURL

    def found = false
    def changed = false

    // --- Search in APP_APPS.xml ---
    for (node in appApps.appsHome) {
        if (node.@appsName == targetName) {
            found = true

            // Compare all attributes
            if (node.@appsDesc != targetDesc ||
                node.@appsPng  != targetPng ||
                node.@appsHomeURL != targetURL) {
                changed = true
                node.@appsDesc    = targetDesc
                node.@appsPng     = targetPng
                node.@appsHomeURL = targetURL
            }
            break
        }
    }

    // --- Add new if not found ---
    if (!found) {
        changed = true
        appApps.appendNode('appsHome', [
            appsName: targetName,
            appsDesc: targetDesc,
            appsPng:  targetPng,
            appsHomeURL: targetURL
        ])
    }

    // --- Write only if changed ---
    if (changed) {
        def writer = new StringWriter()
        new XmlUtil().serialize(appApps, writer)
        new File(APP_APPS_FILE).write(writer.toString())
        println "APP_APPS.xml updated"
    } else {
        println "APP_APPS.xml up-to-date"
    }

} catch (FileNotFoundException e) {
    println "ERROR: File not found: ${e.message}"
    System.exit(1)
} catch (Exception e) {
    println "ERROR: ${e.class.simpleName}: ${e.message}"
    System.exit(1)
}