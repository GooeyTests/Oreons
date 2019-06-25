#!/usr/bin/env groovy

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Rest Build Logic
            }
        }
        stage('Test') {
            when {
                expression { return currentBuild.previousBuild }
            }
            steps {
                script{
                    try{
                        copyArtifacts(projectName: currentBuild.projectName,
                                      selector: specific("${currentBuild.previousBuild.number}"))
                        def previousModule = readFile(file: "module.txt")
                        def previousReadme = readFile(file: "README.md")
                        def previousCover = readFile(file: "Cover.md")
                    } catch(err) {
                        echo err
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
