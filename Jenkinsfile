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
        stage('Prepare Build Context') {
    steps {
        sh '''
        cp target/eventsProject-1.0.0.jar .
        '''
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
stage('SNYK SAST') {
    steps {
        echo 'Testing...'
        snykSecurity(
            snykInstallation: 'snyk',
            snykTokenId: '305070a9-7c98-4731-b93f-af61bc8496ff', // Ensure this ID matches the Snyk token in your Jenkins credentials
            failOnIssues: false,  // Set to false to not fail the build on issues
            additionalArguments: '--all-projects --detection-depth=5 --severity-threshold=medium'
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


       

        // Second Stage: Run Trivy to Check Git Repository for Secrets
   

stage('Docker push action9559') {
           steps {
               echo "pushing vers docker"
               
        }
        }
        
        */
        
      stage('Build Docker Image') {
            steps {
                echo "Building Docker image with multi-stage Dockerfile..."
                sh '''
                docker build -t events-project:1.0 .
                '''
            }
        }
      stage('Run Docker Container') {
    steps {
        script {
            def containerExists = sh(script: "docker ps -aqf 'name=events-container'", returnStdout: true).trim()
            if (containerExists) {
                echo "Container already exists. Restarting it..."
                sh "docker start events-container"
            } else {
                echo "Running new Docker container..."
                sh "docker run -d -p 8087:8087 --name events-container events-project:1.0"
            }
        }
    }
}

             stage('Trivy Check Git Secrets') {
            steps {
                echo 'Checking Git repository for secrets...'
              
                sh 'trivy repo --format "json" -o "trivy-git-repo-scan.json" https://github.com/ahmed-rai/event.git'
             
            }
            post {
                // Archive the scan report as an artifact for later review
                always {
                    archiveArtifacts artifacts: 'trivy-git-repo-scan.json', fingerprint: true
                }
            }
        }

stage('Snyk Container Scan') {
  steps {
       echo 'Running Snyk Container Scan...'

        )
    } 


    
    
    }
} 
