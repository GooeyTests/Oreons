#!/usr/bin/env groovy

void isEqual() {
    steps {
        script{
            def equals
            equals = sh "diff module.txt module.txt"
            echo equals
            if(fileExists("module.txt")) {
                if(equals == null) {
                    echo 'Same'
                } else {
                    echo 'Not Same'
                }
            } else {
                echo 'File does not exist'
            }
        }
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
                script{
                    try{
                        copyArtifacts(projectName: currentBuild.projectName,
                                      selector: specific("${currentBuild.previousBuild.number}"))
                        def previousModule = readFile(file: "module.txt")
                        def previousReadme = readFile(file: "README.md")
                        def previousCover = readFile(file: "Cover.png")
                        def previouslogo = readFile(file: "logo.png")

                        isEqual()
                    } catch(err) {
                        echo err.toString()
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
