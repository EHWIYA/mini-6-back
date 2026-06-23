#!/bin/bash
APP_NAME="app.jar"

if pgrep -f "$APP_NAME" > /dev/null 2>&1; then
    pkill -f "$APP_NAME"
    sleep 2
fi
