#!/bin/bash
mvn package -T 1C \
    && java \
        --enable-preview \
        -cp entrypoint/target/CleanSandbox.jar \
        com.example.entrypoint.Main
