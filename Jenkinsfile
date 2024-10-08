pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'skbtrivedi/ecommerce-microservices'  // Update with your Docker image name
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')  // Ensure this matches the Jenkins credentials ID
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
        stage('Build with Java 21') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-21'
                    args '-v /var/run/docker.sock:/var/run/docker.sock -u root'
                }
            }
            steps {
                script {
                    sh 'java -version'
                    // Build each microservice individually using Maven
                    sh 'mvn -f ApiGatewayService/pom.xml clean install'
                    sh 'mvn -f CartService/pom.xml clean install'
                    sh 'mvn -f CheckOutService/pom.xml clean install'
                    sh 'mvn -f EurekaServerService/pom.xml clean install'
                    sh 'mvn -f NotificationService/pom.xml clean install'
                    sh 'mvn -f PriceService/pom.xml clean install'
                    sh 'mvn -f ProductDetailService/pom.xml clean install'
                    sh 'mvn -f ProductService/pom.xml clean install'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Build Docker images for each microservice
                    sh 'docker-compose -f /var/jenkins_home/workspace/ecommerce-app-pipeline/docker-compose.yml build'
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    // Log in to Docker and push the image to Docker Hub
                     sh 'echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin'
                     sh 'docker-compose -f /var/jenkins_home/workspace/ecommerce-app-pipeline/docker-compose.yml build'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Use kubectl to deploy the Docker image to your Kubernetes cluster
                    sh 'kubectl set image deployment/ecommerce-deployment ecommerce-microservices=${DOCKER_REGISTRY}/${DOCKER_IMAGE}:$BUILD_NUMBER'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker resources after every build
            sh 'docker system prune -f'
        }

        success {
            echo 'Build and Deployment Successful!'
        }

        failure {
            echo 'Build or Deployment Failed!'
        }
    }
}
