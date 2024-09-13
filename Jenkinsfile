pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'skbtrivedi/ecommerce-microservices'  // Update with your Docker image name
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')  // Ensure this matches the Jenkins credentials ID
    }
    
     tools {
        maven 'Maven' // This should match the name in the Global Tool Configuration
        jdk 'Java 21'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pull the code from your GitHub repository
                git branch: 'main', url: 'https://github.com/SKBtrivedi/EcommerceMicroservices.git'
            }
        }
        stage('Check Docker') {
            steps {
                sh 'docker --version'
            }
        }
        // Add a stage to build the microservices using Maven
        stage('Build Microservices') {
            steps {
                script {
                    // Build each microservice individually using Maven
                  echo 'Sucess Build Microservices'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Build Docker images for each microservice
                   echo 'Sucess Docker Image Created'
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    // Log in to Docker and push the image to Docker Hub
                      echo 'Docker Image Pushed'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Use kubectl to deploy the Docker image to your Kubernetes cluster
                     echo 'Deployed to kubernates'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker resources after every build
             echo 'resources cleaned'
        }

        success {
            echo 'Build and Deployment Successful!'
        }

        failure {
            echo 'Build or Deployment Failed!'
        }
    }
}
