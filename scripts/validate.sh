#!/bin/bash
APP_DIR="/home/ec2-user/app"
HEALTH_URL="http://localhost:8080/health"
LOG_FILE="$APP_DIR/application.log"

for i in {1..12}; do
    if curl -fsS "$HEALTH_URL" > /dev/null; then
        exit 0
    fi
    sleep 5
done

echo "Health check failed: $HEALTH_URL"
if [ -f "$LOG_FILE" ]; then
    tail -n 100 "$LOG_FILE"
fi
exit 1
