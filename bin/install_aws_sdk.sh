#!/bin/sh

wget http://sdk-for-java.amazonwebservices.com/latest/aws-java-sdk.zip -P $HOME

cd $HOME
unzip $HOME/aws-java-sdk.zip
rm $HOME/aws-java-sdk.zip

