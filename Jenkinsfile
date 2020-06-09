pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M3"
   }

   stages {
      stage('Build') {
         steps {
            sh "mvn clean package"
         }
      }
      stage('Deploy') {
           steps {
              sh "heroku deploy:jar target/cvgenerator-1.jar --app cvbuilderapp"
           }
      }
   }
}
