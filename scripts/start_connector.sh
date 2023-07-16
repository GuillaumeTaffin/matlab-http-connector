#!/bin/bash

#export MATLAB_SHARED_SESSION_NAME=titi
#export DYLD_LIBRARY_PATH="/Applications/MATLAB_R2023a.app/bin/maci64:$DYLD_LIBRARY_PATH"
export PATH="$PATH:${JAVA_HOME}/bin"

/Applications/MATLAB_R2023a.app/sys/java/jre/maci64/jre/bin/java \
 -Djava.library.path=/Applications/MATLAB_R2023a.app/bin/maci64 \
 -cp .:/Applications/MATLAB_R2023a.app/extern/engines/java/jar/engine.jar:build/libs/matlab-http-connector-all.jar \
  com.gt.ApplicationKt
