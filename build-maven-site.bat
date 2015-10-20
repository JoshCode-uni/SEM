::echo Cleaning Maven project
start /wait mvn clean ^& exit

::echo Generating Maven Site
start /wait mvn site ^& exit

::echo Generating Cobertura reports
start /wait mvn cobertura:cobertura ^& exit

::echo Generating Checkstyle reports
start /wait mvn checkstyle:checkstyle ^& exit

start target/site/index.html
start target/site/checkstyle.html
start target/site/cobertura/index.html