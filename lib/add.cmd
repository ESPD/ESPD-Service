set maven=C:\Users\lukasal.NET1\AppData\LocalLow\Home\devtools\apache-maven-3.0.5\bin\mvn.bat
set lib_path=C:\Users\lukasal.NET1\AppData\LocalLow\Home\workspace.ecertis\ESPD\lib\ecertisExchangeModel.jar

%maven% install:install-file -Dfile=%lib_path% -DgroupId=dgmarkt.ecertis -DartifactId=ecertisExchangeCoreModel -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -DgeneratePom=true

