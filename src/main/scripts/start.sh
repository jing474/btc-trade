#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

SERVER_NAME="${script.server.name}"
LOGS_FILE="${script.server.logfile}"

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
    LOGS_DIR=`dirname $LOGS_FILE`
else
    LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.net.preferIPv4Stack=true -Dfile.encoding=utf-8"
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" ${script.server.java_debug_opts} "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" ${script.server.java_jmx_opts} "
fi
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
	JAVA_MEM_OPTS=" ${script.server.java_mem_opts} "
else
    JAVA_MEM_OPTS=" ${script.server.java_mem_opts_32} "
fi

echo -e "Starting the $SERVER_NAME ...\c"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS ${script.server.mainClass} > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
    COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

echo "OK!"
PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
