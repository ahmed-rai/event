pipeline {
    agent any
    
    stages {
        stage('GIT') {
            steps {
            
                echo 'Pulling... '
                git branch: 'ahmed',
                url :'https://github.com/ahmed-rai/event.git'
            }
        }
  
        stage('Maven Clean') {
            steps {
               echo "Clean avec maven"
               
               sh "mvn clean"

            }
 stage('Upload to Nexus') {
            steps {
                script {
                    nexusArtifactUploader(
                        nexusVersion: NEXUS_VERSION,
                        protocol: 'http',
                        nexusUrl: "${NEXUS_URL}",
                        groupId: 'tn.esprit',
                        artifactId: 'eventsProject',
                        version: '1.0.0-SNAPSHOT',
                        repository: 'maven-snapshots',
                        credentialsId: NEXUS_CREDENTIALS,
                        artifacts: [[artifactId: 'eventsProject', file: 'target/eventsProject-1.0.0-SNAPSHOT.jar', type: 'jar']]
                    )
                }
            }
        }
          
        }
        stage('Maven Compile') {
            steps {
                echo "compilation avec maven"
                sh "mvn compile"
            }
        }
  stage('JUnit/Mock') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sets') {
                    sh 'mvn sonar:sonar -X'
                }
            }
        }


        
    }
}
