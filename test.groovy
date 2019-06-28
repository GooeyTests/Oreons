// 
// import static groovy.io.FileType.FILES
// 
// 	new File('./','.').eachFileRecurse(FILES) {
//     	if(it.name.startsWith('logo')) {
//     		println "Logo image found."
// 			logoSrc = new File(it.toString())
// 			logoDst = new File(indexDir.toString() + "/logo.png")
// 			logoDst << logoSrc.bytes
// 			println "Fetched logo image."
//     	} else {
//     		println "Logo image not found, skipping the current step."
//     	}
// 	}
dir = new File('./')

dir.eachFile { file ->                      
    println file.name
}