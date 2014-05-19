#!/bin/ksh

cd ../

LIB=lib
AWS=$HOME/aws-java-sdk-1.7.9/lib/*
THIRDPARTY=$HOME/aws-java-sdk-1.7.9/third-party/*/*
LIBRARY=$HOME/opencv/build/lib
OPENCV=$HOME/opencv/build/bin/

# Jar libraries
lib_jar=`find $LIB -name *.jar | awk '{printf("%s:",$0)}'`
aws_jar=`find $AWS -name *.jar | awk '{printf("%s:",$0)}'`
lib_third=`find $THIRDPARTY -name *.jar | awk '{printf("%s:",$0)}'`
opencv_jar=`find $OPENCV -name *.jar | awk '{printf("%s:",$0)}'`

CLASSPATH=classes:$lib_jar:$aws_jar:$lib_third:$opencv_jar
MAIN=com.eamores.cloud.aws.recognition.RunRecognition

echo "Starting $MAIN" 

nohup java -Djava.library.path=$LIBRARY -classpath $CLASSPATH $MAIN &
