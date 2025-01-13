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

        stage('Build & Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat '''
                        mvn clean install
                        mvn sonar:sonar -Dsonar.login=e8ffa2692c10fb241956a43d6bfb66c09172a282
                        timeout /t 10 /nobreak
                        waitForQualityGate abortPipeline: true
                    '''
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