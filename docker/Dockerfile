FROM ubuntu:16.04
MAINTAINER "Antoine Robert" antoine.robert03@outlook.fr
RUN apt-get update -y
RUN apt-get install curl -y
RUN apt-get install git -y
RUN apt-get install openjdk-8-jdk -y
RUN DEBIAN_FRONTEND=noninteractive apt-get install mysql-server  -y -q
RUN apt-get install maven -y
RUN apt-get install python -y
RUN apt-get install make g++ -y
RUN curl --silent --location https://deb.nodesource.com/setup_7.x | bash -
RUN apt-get install nodejs
RUN node -v
COPY initialization.sql /
RUN git clone https://github.com/profile-match/pm-core
RUN git clone https://github.com/profile-match/imp
WORKDIR pm-core
RUN git checkout dev
RUN mvn package
RUN mvn wildfly-swarm:package
WORKDIR ../imp
RUN git checkout dev
WORKDIR /
COPY ./startup.sh /
RUN ["chmod", "+x", "/startup.sh"]
ENTRYPOINT ["/startup.sh"]
