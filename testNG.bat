set projectLocation=C:\Users\<UserName>\iPresence
cd %projectLocation%
set classpath=%projectLocation%\target\test-classes;%projectLocation%\target\classes;%projectLocation%\bin;%projectLocation%\lib\*
mvn clean install test -DsuiteXmlFile=testng.xml
pause