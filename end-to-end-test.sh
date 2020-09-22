#!/bin/bash -e

uri="localhost:8080/"
printf "\n===================================================================\n"
curl -X POST $uri --data '{"language":"en-US","text":"Howdy!"}'
printf "\n===================================================================\n"
curl -X POST $uri --data '{"language":"en-GB","text":"Hello!"}'
printf "\n===================================================================\n"
curl -X POST $uri --data '{"language":"es-ES","text":"¡Hola!"}'
printf "\n===================================================================\n"
curl -X GET $uri -H "Accept-Language: en-us,;"
printf "\n"
