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
            steps {
                echo 'Testing...'
                if(currentBuild.previousBuild) {
                    try{
                        copyArtifacts(projectName: currentBuild.projectName,
                                          selector: specific("${currentBuild.previousBuild.number}"))
                        def previousModule = readFile(file: "module.txt")
                        def previousReadme = readFile(file: "README.md")
                        def previousCover = readFile(file: "Cover.md")
                    } catch(err) {
                        echo err
                    }
                } else {
                    archiveArtifacts artifacts: 'output/*.*'
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
