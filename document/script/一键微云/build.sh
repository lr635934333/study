#!/bin/sh

#git pull origin develop

docker build -t bright-kitchen-web:latest .

#mvn clean package docker:build