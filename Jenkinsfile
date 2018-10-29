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
    }
    stages {
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }
    stages {
        stage('Quality Tests') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
    }
    stages {
        stage('Build Install') {
            steps {
                sh 'mvn install'
            }
        }
    }
    stages {
        stage('Deploy') {
            steps {
                echo 'fazendo deploy...'
            }
        }
    }
}