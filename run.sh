#!/bin/bash
mvn package \
    && java \
        --enable-preview \
        -cp entrypoint/target/CleanSandbox.jar \
        com.example.entrypoint.Main
