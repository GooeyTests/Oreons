#!/usr/bin/env groovy

void isEqual(file) {
    def equals
    equals = sh "diff output/$file artifacts/output/$file"
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

                        sh "ls"
                        sh "cd artifacts"
                        sh "ls"
                        isEqual("module.txt")
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
