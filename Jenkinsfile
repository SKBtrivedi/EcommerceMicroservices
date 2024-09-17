pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'skbtrivedi/ecommerce-microservices'  // Update with your Docker image name
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')  // Ensure this matches the Jenkins credentials ID
    }

    tools {
        maven 'Maven' // This should match the name in the Global Tool Configuration
        jdk 'Java 21'  // Ensure this matches your Jenkins JDK configuration
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
                bat 'docker --version'  // Use 'bat' instead of 'sh' on Windows
            }
        }

        stage('Build Microservices') {
            steps {
                script {
                    // Build each microservice individually using Maven
                    bat 'mvn -f ApiGatewayService/pom.xml clean install'
                    bat 'mvn -f CartService/pom.xml clean install'
                    bat 'mvn -f CheckOutService/pom.xml clean install'
                    bat 'mvn -f EurekaServerService/pom.xml clean install'
                    bat 'mvn -f NotificationService/pom.xml clean install'
                    bat 'mvn -f PriceService/pom.xml clean install'
                    bat 'mvn -f ProductDetailService/pom.xml clean install'
                    bat 'mvn -f ProductService/pom.xml clean install'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Ensure the docker-compose.yml path is correct
                    bat 'docker-compose -f %WORKSPACE%\\docker-compose.yml build'  // Use Windows-compatible paths
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    // Log in to Docker and push the image to Docker Hub
                    bat 'echo %DOCKER_HUB_CREDENTIALS_PSW% | docker login -u %DOCKER_HUB_CREDENTIALS_USR% --password-stdin'
                    bat 'docker-compose -f %WORKSPACE%\\docker-compose.yml push'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Use kubectl to deploy the Docker image to your Kubernetes cluster
                    bat 'kubectl set image deployment/ecommerce-deployment ecommerce-microservices=%DOCKER_REGISTRY%/%DOCKER_IMAGE%:%BUILD_NUMBER%'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker resources after every build
            bat 'docker system prune -f'
        }

        success {
            echo 'Build and Deployment Successful!'
        }

        failure {
            echo 'Build or Deployment Failed!'
        }
    }
}
