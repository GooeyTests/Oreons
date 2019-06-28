import groovy.json.JsonSlurper
import static groovy.io.FileType.FILES

moduleFile = new File("./module.txt")
indexDir = "./output" // Needs to be changed according to workspace setting

if(moduleFile.exists()) {
	moduleJson = new JsonSlurper().parseText(moduleFile.text)
	moduleName = moduleJson.get("id")
	println "Scraping data from " + moduleName

	moduleSrc = moduleFile
	moduleDst = new File(indexDir.toString() + "/module.txt")
	moduleDst << moduleSrc.text
	println "Fetched module data"

	println "Searching for README file..."
	new File('.').eachFileRecurse(FILES) {
    	if(it.name.endsWith('.md') || it.name.endsWith('.markdown') || it.name.endsWith('.MD') || it.name.endsWith('.MARKDOWN')) {
			readmeSrc = new File(it)
			readmeDst = new File(indexDir.toString() + "/README.md")
			readmeDst << readmeSrc.text		
			println "Fetched README data."
    	} else {
    		println "README file not found, skipping the current step."
    	}
	}

	println "Searching for logo image..."
	logoSrc = new File("./logo.png")
	if(logoSrc.exists()) {
		logoDst = new File(indexDir.toString() + "/logo.png")
		logoDst << logoSrc.bytes
		println "Fetched logo image"
	} else {
		println "Logo image not found, skipping the current step."	
	}

	println "Searching for cover image..."
	logoSrc = new File("./cover.png")
	if(logoSrc.exists()) {
		logoDst = new File(indexDir.toString() + "/cover.png")
		logoDst << logoSrc.bytes
		println "Fetched cover image"
	} else {
		println "Cover image not found, skipping the current step."
	}

	println "Finished scrapping " + moduleName
} else {
	println "The following repository is not a module."
}
