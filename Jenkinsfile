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
               script {
                   def props = readProperties file: 'sonar-project.properties'
                   def sonarToken = props['sonar.token']

                   withSonarQubeEnv('SonarCloud') {
                       bat """
                           mvn clean install
                           mvn sonar:sonar -Dsonar.login=${sonarToken}
                       """
                   }
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