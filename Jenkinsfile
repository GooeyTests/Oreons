pipeline {
    agent any
    stages {
        stage('dir') {
            steps {
                echo 'Hello World!'
                sh 'ls -l'
                dir ('foo') {
                    writeFile file:'bar', text:'dummy'
                }
                sh 'ls -l'
            }
        }
    }
}
