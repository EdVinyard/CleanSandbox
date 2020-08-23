#!/bin/bash
mvn package && java -cp entrypoint/target/CleanSandbox.jar com.example.entrypoint.Main
