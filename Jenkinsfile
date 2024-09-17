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
        
        stage('Build Microservices') {
            steps {
                script {
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

    }

    post { 
        success {
            echo 'Build and Deployment Successful!'
        }

        failure {
            echo 'Build or Deployment Failed!'
        }
    }
}
