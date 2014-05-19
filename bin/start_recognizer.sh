#!/bin/ksh

cd ../

LIB=lib
AWS=/Users/max/aws-java-sdk/1.7.9/lib/*
THIRDPARTY=/Users/max/aws-java-sdk/1.7.9/third-party/*/*
LIBRARY=/Users/max/Downloads/opencv-2.4.9/build/lib

# Jar libraries
lib_jar=`find $LIB -name *.jar | awk '{printf("%s:",$0)}'`
aws_jar=`find $AWS -name *.jar | awk '{printf("%s:",$0)}'`
lib_third=`find $THIRDPARTY -name *.jar | awk '{printf("%s:",$0)}'`

CLASSPATH=classes:$lib_jar:$aws_jar:$lib_third
MAIN=com.eamores.cloud.aws.recognition.RunRecognition

echo "Starting $MAIN" 

nohup java -Djava.library.path=$LIBRARY -classpath $CLASSPATH $MAIN &
