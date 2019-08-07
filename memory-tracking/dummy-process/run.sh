#!/bin/bash

DIR="${BASH_SOURCE%/*}"
if [[ ! -d "$DIR" ]]; then DIR="$PWD"; fi

cmd=$1

echo "Kill all Java Process"
kill -9 $(ps aux | grep "java" | grep -v 'grep' | awk '{print $2}')

echo "Remove logs folder"
rm -rf ./logs/*

echo "Starting multiple programs"

for ((i = 0; i < $cmd; i++)); do
  logDir=./logs/$i nohup java -jar build/libs/dummy-process-fat.jar >/dev/null 2>&1 &
done

echo "Stopping multiple programs"