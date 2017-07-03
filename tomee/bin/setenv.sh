#!/bin/sh

# PASS THESE TO DOCKER
export JAVA_MIN_MEM=128m
export JAVA_MAX_MEM=256m
# export GRED_ENV=dev
# export GRED_DC=chd
export HTTP_PORT=8080
export SHUTDOWN_PORT=8009
#export JMX_PORT=8081

#export JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.port=${JMX_PORT}"
export JAVA_OPTS="-Xms${JAVA_MIN_MEM} -Xmx${JAVA_MAX_MEM} -server -Daddress=0.0.0.0 -Dport.http=${HTTP_PORT} -Dport.shutdown=${SHUTDOWN_PORT} -Dhost=localhost -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts"

#-Dlog4j.configurationFile=${CATALINA_BASE}/conf/log4j2.xml
