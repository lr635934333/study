#!/bin/sh
git pull origin develop
#mvn clean package docker:build
docker build -t bright-kitchen:latest .
