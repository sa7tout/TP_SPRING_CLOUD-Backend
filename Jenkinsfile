pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Build & Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat '''
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=spring-cloud \
                        -Dsonar.organization=emsig5ky \
                        -Dsonar.host.url=https://sonarcloud.io \
                        -Dsonar.login=e8ffa2692c10fb241956a43d6bfb66c09172a282 \
                        -Dsonar.qualitygate.wait=true
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