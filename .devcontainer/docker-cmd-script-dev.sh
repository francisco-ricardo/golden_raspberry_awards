#!/bin/sh

#while true; do sleep 1; done
echo "Development container started. Run 'mvn quarkus:dev' to start the application."
exec tail -f /dev/null

# EOF
