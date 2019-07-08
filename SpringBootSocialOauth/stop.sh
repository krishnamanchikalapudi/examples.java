#!/bin/bash

kill -9 `lsof -i :8080 | grep LISTEN | awk '{print $2}'` 

