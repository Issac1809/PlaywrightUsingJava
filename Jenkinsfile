pipeline {
    agent any

    tools {
        maven 'maven'
        msbuild 'MSBuild'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git url: 'https://github.com/CormSquare/GePS', branch: 'master'
            }
        }

        stage('Build C# Project') {
            steps {
                script {
                    bat 'msbuild /p:Configuration=Release'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/bin/Release/*.exe', allowEmptyArchive: true
                }
            }
        }

        stage('Deploy to Test Environment') {
            steps {
                echo "Deploy to QA"
                bat './deploy_to_qa.sh'
            }
        }

        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git url: 'https://github.com/Issac1809/PlayWright', branch: 'master'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_functional.xml"
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'build',
                             reportFiles: 'buildTestExecutionReport.html',
                             reportName: 'HTML Extent Report',
                             reportTitles: ''])
            }
        }
    }
}