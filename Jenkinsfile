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

          
        }
        stage('Maven Compile') {
            steps {
                echo "compilation avec maven"
                sh "mvn compile"
            }
        }
  stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube1') {
                    sh 'mvn sonar:sonar -X'
                }
            }
        }


        
    }
}
