pipeline {
    agent any

    environment {
        GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
        ANDROID_HOME = "/opt/android-sdk"
        PATH = "${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${PATH}"
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        ansiColor('xterm')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'chmod +x ./gradlew'
            }
            post {
                success {
                    echo "Checkout successful"
                }
                failure {
                    echo "Checkout failed"
                }
            }
        }

        stage('Detekt') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh './gradlew detekt --stacktrace'
                }
            }
            post {
                success {
                    echo "Detekt analysis passed"
                }
                failure {
                    echo "Detekt analysis found issues"
                }
            }
        }

        stage('Build') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh './gradlew clean assembleDebug --stacktrace'
                }
            }
            post {
                success {
                    echo "Build succeeded"
                }
                failure {
                    echo "Build failed"
                }
            }
        }

        stage('Tests') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh './gradlew test --stacktrace'
                }
            }
            post {
                always {
                    junit '**/build/test-results/**/*.xml'
                }
                success {
                    echo "Tests passed"
                }
                failure {
                    echo "Tests failed"
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/build/reports/**/*', allowEmptyArchive: true
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}