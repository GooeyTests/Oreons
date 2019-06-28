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

	dir = new File('./')

	println "Searching for README file..."
	readmeFound = 0
	dir.eachFile { file ->
    	if(file.name.endsWith('.md') || file.name.endsWith('.markdown') || file.name.endsWith('.MD') || file.name.endsWith('.MARKDOWN')) {
    		println "README Found."
			readmeSrc = new File("./" + file.toString())
			readmeDst = new File(indexDir.toString() + "/README.md")
			readmeDst << readmeSrc.text		
			println "Fetched README data."
			readmeFound += 1
    	}
	}

 	if(readmeFound == 0) {
    		println "README file not found, skipping the current step."
   	}

	logoSrc = new File("./logo.png")
	coverSrc = new File("./cover.png")

	if(logoSrc.exists()) {
		logoDst = new File(indexDir.toString() + "/logo.png")
		logoDst << logoSrc.bytes
		println "Fetched logo image."
	} else {
		println "Logo image not found, skipping the current step."
	}

	if(coverSrc.exists()) {
		coverDst = new File(indexDir.toString() + "/cover.png")
		coverDst << coverSrc.bytes
		println "Fetched cover image."
	} else {
		println "Cover image not found, skipping the current step."
	}

	println "Finished scrapping " + moduleName
} else {
	println "The following repository is not a module."
}
