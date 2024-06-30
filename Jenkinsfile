pipeline {
    agent any

    tools {
        maven 'maven-3.9.8'
    }

    stages {
        stage('Build') {
            steps {
                git url: 'https://github.com/CormSquare/GePS.git', branch: 'master'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Deploy to Test Environment') {
            steps {
                echo "Deploy to QA"
            }
        }

        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git url: 'https://github.com/Issac1809/PlayWright.git', branch: 'master'
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