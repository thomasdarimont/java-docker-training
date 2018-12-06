#!/usr/bin/env bash

if [ "$REMOTE_DEBUG" -eq "1" ]; then
export DEBUG_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787
else
export DEBUG_OPTS=
fi

echo
echo "Using Java Version"
java -version

echo "
App:       $APP_NAME
Version:   $APP_VERSION
Launching: $(ls /app/*.jar)
JAVA_OPTS: $(echo $JAVA_OPTS | tr -s " ")
"

exec java $JAVA_OPTS $DEBUG_OPTS -jar /app/*.jar $@