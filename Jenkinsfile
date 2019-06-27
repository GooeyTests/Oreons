#!/usr/bin/env groovy

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
                script {
                    def code = load 'scrape.groovy'
                    code.scrape()
                }
            }
        }
        stage('Test') {
            when {
                expression { currentBuild.previousBuild }
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
                        echo err.toString()
                        archiveArtifacts artifacts: './output/*.*', fingerprint: true
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
