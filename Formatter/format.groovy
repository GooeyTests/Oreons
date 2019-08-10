import groovy.json.JsonSlurper
import static groovy.io.FileType.FILES

dir = new File('.')
dst = new File('./gatsby/terasology.github.io/modules')
dst.mkdir()

dir.eachFile {
	println it
	if(it.isDirectory()) {
		moduleFile = new File(it.toString() + "/module.txt")
		if(moduleFile.exists()) {
				moduleJson = new JsonSlurper().parseText(moduleFile.text)
				moduleName = moduleJson.get("id")
				moduleAuthor = moduleJson.get("author")
				moduleDescription = moduleJson.get("description")
				moduleDisplayName = moduleJson.get("displayName")
				moduleLogo = new File(it.toString() + "/logo.png")
				moduleCover = new File(it.toString() + "/cover.png")
				moduleReadMe = new File(it.toString() + "/README.md")
				println "Formatting data for " + moduleName

				moduleDir = new File(dst.toString() + "/" + moduleName)
				moduleDir.mkdir()
				moduleFinal = new File(moduleDir.toString() + "/index.md")

				moduleFinal.write("---\n")
				moduleFinal.append("posttype: \"module\"\n")
				moduleFinal.append("title: " + moduleDisplayName + "\n" )
				if(moduleLogo.exists()) {
					moduleFinal.append("logo: \"./logo.png\"\n")
					logoDst = new File(moduleDir.toString() + "/logo.png")
					logoDst << moduleLogo.bytes
				} else {
					moduleFinal.append("logo: \"../../logos/logo.png\"\n")
				}
				if(moduleCover.exists()) {
					moduleFinal.append("cover: \"./cover.png\"\n")
					coverDst = new File(moduleDir.toString() + "/cover.png")
					coverDst << moduleCover.bytes
				} else {
					moduleFinal.append("cover: \"../../logos/logo.png\"\n")
				}
				moduleFinal.append("tags: \"Logic\"\n")
				moduleFinal.append("---\n")
				moduleFinal.append(moduleReadMe.text)
		}
	}
}