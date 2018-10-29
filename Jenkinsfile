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
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
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
        stage('Deploy') {
            steps {
                echo 'fazendo deploy...'
            }
        }
    }
}