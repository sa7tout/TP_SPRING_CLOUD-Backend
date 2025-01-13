pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/sa7tout/TP_SPRING_CLOUD-Backend.git'
            }
        }

        stage('Build & SonarCloud') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat '''
                        mvn clean install
                        mvn sonar:sonar -Dsonar.login=e8ffa2692c10fb241956a43d6bfb66c09172a282
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always { cleanWs() }
        success { echo 'Pipeline completed successfully!' }
        failure { echo 'Pipeline failed!' }
    }
}