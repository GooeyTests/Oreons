#!/usr/bin/env groovy

void archive() {
    list = ["module.txt", "README.md", "logo.png", "cover.png"]
    changed = false
    
    list.each { file ->
        equals = ""
        if(fileExists("output/$file")) {
            equals = sh "diff output/$file artifacts/output/$file"
            if(equals == null) {
                println("$file Same")
            } else {
                println("$file Not Same")
                changed = true
            }
        } else {
            println("$file Doesn't Exist")
            changed = true
        }
    }

    if(changed == true) {
        archiveArtifacts artifacts: 'output/*.*', fingerprint: true
    }

}

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                checkout scm
                // Rest Build Logic
            }
        }
        stage('Gather Data') {
            steps {
                sh "mkdir -p output"
                sh "./groovyw scrape.groovy"
            }
        }
        stage('Check Data') {
            when {
                expression { currentBuild.previousBuild }
            }
            steps {
                sh "mkdir -p artifacts"
                script{
                    try{
                        copyArtifacts(projectName: currentBuild.projectName,
                            target: "artifacts",
                            selector: lastSuccessful())

                        archive()
                    } catch(err) {
                        println("$err")
                        archiveArtifacts artifacts: 'output/*.*', fingerprint: true
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Deploy Logic
            }
        }
    }
}
