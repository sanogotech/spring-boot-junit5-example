
pipeline { 
    agent any 
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') { 
            steps { 
                /* sh 'mvn clean' */
				sleep 10
				echo 'Build'
            }
        }
        stage('Test'){
            steps {
                /* sh 'make check' */
               /* junit 'reports/**/*.xml' */
			   
			   echo 'Test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy'
            }
        }
    }
}