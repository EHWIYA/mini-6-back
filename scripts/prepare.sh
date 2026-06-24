#!/bin/bash
APP_DIR="/home/ec2-user/app"

rm -f "$APP_DIR/app.jar"
mkdir -p "$APP_DIR/data"
chown -R ec2-user:ec2-user "$APP_DIR"
chmod -R u+rwX,go+rX "$APP_DIR"
