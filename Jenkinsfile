pipeline {
    agent any
    tools {
        maven 'maven-3.8.1'
    }
    stages {
        stage('test') {
            steps {
                echo 'Running tests...'
                sh 'mvn verify'
            }
        }
    }
}
