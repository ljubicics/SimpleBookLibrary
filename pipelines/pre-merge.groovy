JENKINS_MACHINE_ID = "android-pre-merge-agent"
SUCCESS = "SUCCESS"
FAILURE = "FAILURE"

QUEUED = "QUEUED"
COMPLETED = "COMPLETED"

RESULT_SUCESS = 1
RESULT_NOT_RUN = 0
RESULT_ERROR = -1

pipeline {
    agent {
        label 'master'
    }

    environment {
        GRADLE_USER_HOME = "${HOME}/.gradle"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Grant gradlew execute') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Detekt') {
            steps {
                sh './gradlew detekt'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}