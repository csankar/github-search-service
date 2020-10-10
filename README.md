# Take Home Test

REST Service in java which takes a language as input and search it in GitHub repositories
and provides JSON output with project id, name, url and the owner login 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for evaluation purposes.

### Prerequisites

The following tools / softwares needs to be installed and available on your PATH to run the application

1. JDK 1.8
2. Maven


### Installing

1. Clone the following repository from Git repository onto your local drive

    https://github.com/csankar/github-search-service.git
2. Go to the project home directory 'github-search-service'
3. Verify java and maven setup and executable from the home directory. 
   If not please set the JAVA_HOME and MAVEN_HOME path to execute the commands
4. Build the project using the following command from the project home directory 'github-search-service'
   mvn clean install
5. Verify github-search-service-0.0.1-SNAPSHOT.jar generated under '/github-search-service/target' folder
   

## Running the service / application

Run the following command to start the service / application from the project home directory 'github-search-service'

java -jar target/github-search-service-0.0.1-SNAPSHOT.jar



### Test the REST service

Access the following links to test the service

http://localhost:8080/github/search?language={some-value}

or 

http://localhost:8080/github/search/{some-value}

Note for example : {some-value} -> coffeescript