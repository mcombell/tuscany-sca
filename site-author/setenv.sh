REM set JAVA_HOME to the full path of where Java JDK  was installed
set JAVA_HOME=E:\bin\jdk1.5.0_06

REM set MAVEN_HOME to the full path of where Maven was installed
set MAVEN_HOME=e:\bin\maven-2.0.4

REM set ANT_HOME to the full path of where Ant was installed
set ANT_HOME=E:\bin\apache-ant-1.6.5

REM set SVN_HOME to the full path of where Subversion was installed
set SVN_HOME=E:\bin\svn-win32-1.3.1

set PATH=%PATH%;%JAVA_HOME%\bin
set PATH=%PATH%;%MAVEN_HOME%\bin
set PATH=%PATH%;%ANT_HOME%\bin
set PATH=%PATH%;%SVN_HOME%\bin
echo.
echo Checkng Java 
java -version
echo checking Ant
call ant -version
echo.
echo checking SVN
svn --version
echo.
echo checking Maven 
mvn --version
