pipeline {
    agent any
    tools {
        maven 'm3' 
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Quality Tests') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        stage('Build Install') {
            steps {
                sh 'mvn install'
            }
        }
    }
}