typeset -x JAVA_HOME=~/bin/jdk1.5.0_06
typeset -x PATH=$JAVA_HOME/bin:$PATH
#
typeset -x ANT_HOME=~/bin/apache-ant-1.6.5
typeset -x PATH=$ANT_HOME/bin:$PATH
#
typeset -x MVN_HOME=~/bin/maven-2.0.4
typeset -x PATH=$MVN_HOME/bin:$PATH/bin
#
typeset -x SVN_HOME=~/bin/maven-2.0.4
typeset -x PATH=$SVN_HOME/bin:$PATH/bin
#
java -version
mvn --version
ant -version
svn --version
