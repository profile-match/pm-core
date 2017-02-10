#!/bin/bash
mvn clean
mvn package
mvn wildfly-swarm:package
cd target/

echo "Lmfiroot" >> password.txt
java -Dswarm.ds.connection.url="jdbc:mysql://localhost:3306/IMP?useSSL=false" -jar pm-core-swarm.jar
