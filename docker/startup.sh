#!/bin/bash

service mysql start
mysql -u root < initialization.sql
cd pm-core
./run.sh  &
cd ../imp
./build.sh
npm install -g angular-cli
ng --version
ng serve --host 0.0.0.0 --environment=docker