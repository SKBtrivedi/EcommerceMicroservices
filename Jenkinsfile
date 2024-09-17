pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'skbtrivedi/ecommerce-microservices'
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
    }

    tools {
        maven 'Maven'
        jdk 'Java 21'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/SKBtrivedi/EcommerceMicroservices.git'
            }
        }
        
        stage('Check Docker') {
            steps {
                bat 'wsl docker --version'  // Use WSL to check Docker version
            }
        }

        stage('Build Microservices') {
            steps {
                script {
                    bat 'wsl mvn -f ApiGatewayService/pom.xml clean install'
                    bat 'wsl mvn -f CartService/pom.xml clean install'
                    bat 'wsl mvn -f CheckOutService/pom.xml clean install'
                    bat 'wsl mvn -f EurekaServerService/pom.xml clean install'
                    bat 'wsl mvn -f NotificationService/pom.xml clean install'
                    bat 'wsl mvn -f PriceService/pom.xml clean install'
                    bat 'wsl mvn -f ProductDetailService/pom.xml clean install'
                    bat 'wsl mvn -f ProductService/pom.xml clean install'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat 'wsl docker-compose -f /mnt/c/ProgramData/Jenkins/.jenkins/workspace/ecommerce-app-pipeline/docker-compose.yml build'  // Adjust path for WSL
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    bat 'wsl echo $DOCKER_HUB_CREDENTIALS_PSW | wsl docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin'
                    bat 'wsl docker-compose -f /mnt/c/ProgramData/Jenkins/.jenkins/workspace/ecommerce-app-pipeline/docker-compose.yml push'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    bat 'wsl kubectl set image deployment/ecommerce-deployment ecommerce-microservices=${DOCKER_REGISTRY}/${DOCKER_IMAGE}:$BUILD_NUMBER'
                }
            }
        }
    }

    post {
        always {
            bat 'wsl docker system prune -f'  // Clean up Docker resources in WSL
        }

        success {
            echo 'Build and Deployment Successful!'
        }

        failure {
            echo 'Build or Deployment Failed!'
        }
    }
}
