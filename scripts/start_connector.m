


command = sprintf('!%s/sys/java/jre/maci64/jre/bin/java -Djava.library.path=%s/bin/maci64 -cp .:%s/extern/engines/java/jar/engine.jar:build/libs/matlab-http-connector-all.jar com.gt.ApplicationKt', matlabroot, matlabroot, matlabroot);

eval(command)

