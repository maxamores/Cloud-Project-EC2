MAIN=RunRecognition
SID=`ps -ef | grep $MAIN | grep -v grep | awk '{print $2}'`

echo "Termination $MAIN (SID:$SID)"

echo $SID |  xargs kill -9
