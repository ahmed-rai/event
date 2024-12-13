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
          /*  stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                sh "mvn deploy -DskipTests"
            }
        } */
stage('Test') {
    steps {
        echo 'Testing...'
        snykSecurity(
            snykInstallation: 'snyk',
            snykTokenId: '305070a9-7c98-4731-b93f-af61bc8496ff', // updated ID
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
