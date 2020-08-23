#!/bin/bash

find . -name "*.java" | entr -d mvn test
