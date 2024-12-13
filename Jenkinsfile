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

*/
         stage('Setup Trivy') {
            steps {
                script {
                    // Check if Trivy is installed and install if not
                    def trivyInstalled = sh(script: "trivy --version || echo 'not installed'", returnStdout: true).trim()
                    if (trivyInstalled == 'not installed') {
                        echo 'Installing Trivy...'
                        // Download and install Trivy
                        sh "wget https://github.com/aquasecurity/trivy/releases/download/v0.19.2/trivy_0.19.2_Linux-64bit.deb"
                        sh "sudo dpkg -i trivy_0.19.2_Linux-64bit.deb"
                    } else {
                        echo "Trivy is already installed"
                    }
                }
            }
        }

        // Second Stage: Run Trivy to Check Git Repository for Secrets
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

stage('Docker push action9559') {
           steps {
               echo "pushing vers docker"
               
        }
        }
        

        
    }
}
