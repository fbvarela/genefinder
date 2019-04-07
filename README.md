### Gene Finder app

#### GeneFinder
GeneFinder is a REST-api application developed in Java 8 using the Spring Boot 2 framework and Maven. The IDE was IntelliJ.

#### Run the application
To compile, test and run the application, you have to run the following maven commands in the console in the project's root folder:
```
> mvn clean
> mvn install
> mvn exec:java Dexec.mainClass='com.genefinder.demo.GeneFinderApplication'
```
You can also run the project using the embedded IDE console or clicking the play button.

#### Repository (https://github.com/anikies/genefinder)
I've created two branches in the Github repository. Developed, where I committed all the changes during the developed process, and master, with the first app final version.

#### Deployment
To deploy the application it can be used a pipeline using a Continuous Integration software. To scale to meet increased demand you can run multiple instances in Kubernetes.

#### Test
To test the app, I have created unit tests for every class in the project with the JUnit library. Also, I coded integration tests to test the entire app flow. To ensure the correct functioning of the application I use Postman to test the endpoint (file: GENE FINDER.postman_collection.json in the root app folder).
To automatize the process, you can create a stage in the pipeline to ensure that all the test passed before the deployment.

#### Improving app performance
There are some issues in using the operator LIKE in SQL sentences. In this app is not the case because the wildcard is at the end of the pattern so the database engine will be able to use the indexes as usual and not make a full scan of the table.
