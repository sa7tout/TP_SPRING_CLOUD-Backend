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

        stage('Build & Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat '''
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=spring-cloud \
                        -Dsonar.organization=emsig5ky \
                        -Dsonar.host.url=https://sonarcloud.io \
                        -Dsonar.login=e8ffa2692c10fb241956a43d6bfb66c09172a282
                    '''
                }
                timeout(time: 2, unit: 'MINUTES') {
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