# pm-core
The core project for Profile Match

## Requirements

* Mysql server running on localhost on the port 3306 (default port). The root password should be root.
* A Database called IMP on this server

## build / deployment

To build and package the project, run the two following commands
``` bash
mvn package  
mvn wildfly-swarm:package  
```

You'll then find 2 files in the target folder: 


* pm-core.war
* pm-core-swarm.jar

The first one needs to be deployed in an application server  
The 2nd one can run on its own by running the following command :  
``` bash
java -Dswarm.ds.connection.url="jdbc:mysql://localhost:3306/IMP?useSSL=false" -jar pm-core-swarm.jar
```
