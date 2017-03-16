# pm-core
The core project for Profile Match

## Requirements

* Mysql server running on localhost on the port 3306 (default port). The user used is imp with imp as password.
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

## Docker

You'll find in the docker folder a Dockerfile which is parameterized to run the whole projet, including both pm-core and imp.
It setups all the dependencies, configure mysql, clone, build and run both projects so you always get the latest version each time you creat a new image without modifying the Dockerfile.

To build the image, just run:

``` bash
cd docker
docker build -t imp .
```

This command should return a container ID.
To run the builded image, run:

``` bash
docker run CONTAINER_ID
```

Your app should be accessible at 172.17.0.2:4200