#!/usr/bin/env groovy

void isEqual(file) {
    def equals
    if(fileExists("output/$file")) {
        equals = sh "diff output/$file artifacts/output/$file"
        if(equals == null) {
            sh "echo $file is same"
        } else {
            sh "echo $file is not same."
        }
    } else {
        sh "echo $file doensn't exsit."
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
                        
                        isEqual("cover.png")
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
