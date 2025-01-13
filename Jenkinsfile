pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('SonarCloud Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat "${tool name: 'SonarScanner'}/bin/sonar-scanner -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
    }
}
