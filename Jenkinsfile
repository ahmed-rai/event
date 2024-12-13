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
            } }

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
            stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                sh "mvn deploy -DskipTests"
            }
        }
   stage('Test') {
    steps {
        echo 'Testing...'
        snykSecurity(
            snykInstallation: 'snyk', // Corrected the typo here
            snykTokenId: 'd3880968-16ec-48b3-96f3-6d7809b96642',
            // Example with specific depth, adjust or remove according to your needs
            additionalArguments: '--all-projects --detection-depth=5'
        )
    }
}

        /*stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sets') {
                    sh 'mvn sonar:sonar -X'
                }
            }
        }

*/

stage('Docker push action9559') {
           steps {
               echo "pushing vers docker"
               
        }
        }
        

        
    }
}
