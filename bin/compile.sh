#!/bin/sh

cd ../

CLASSES=classes
LIB=lib/*
AWS=$HOME/aws-java-sdk-1.7.9/lib/*
THIRD=$HOME/aws-java-sdk-1.7.9/third-party/*/*
OPENCV=$HOME/opencv/build/bin/*

CLASSPATH=$LIB:$OPENCV:$AWS:$THIRD
PROG=src/com/eamores/cloud/aws/recognition/RunRecognition.java
SRC=src


javac -d $CLASSES -sourcepath $SRC -classpath $CLASSPATH $PROG


