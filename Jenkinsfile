pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool(name: 'Maven 3.3.9', type: 'clean package')
      }
    }
  }
}