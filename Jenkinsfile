pipeline {
    agent any
    stages {
        stage('dir') {
            steps {
                echo 'Hello World!'
                sh 'ls -l'
                checkout scm
                dir ('foo') {
                    writeFile file:'bar', text:'dummy'
                }
                sh 'ls -l'
            }
        }
    }
}
