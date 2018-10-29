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
        stage('Deploy') {
            steps {
                pushToCloudFoundry(
                    target: 'api.ng.bluemix.net',
                    organization: 'RM331854@fiap.com.br',
                    cloudSpace: 'dev',
                    credentialsId: 'ibm-cloud-credentials',
                    manifestChoice: [
                        appName: 'cloud-restful',
                        appPath: 'target/Clound-0.0.1-SNAPSHOT.jar'
                    ]
                )
            }
        }
    }
}