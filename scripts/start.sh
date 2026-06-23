#!/bin/bash
APP_DIR="/home/ec2-user/app"
APP_NAME="app.jar"
LOG_FILE="$APP_DIR/application.log"

cd "$APP_DIR"
nohup java -jar "$APP_NAME" > "$LOG_FILE" 2>&1 &
