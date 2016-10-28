set JAVAPATH=C:\Program Files\Java\jdk1.8.0_71
set JYTHONPATH=C:\Users\lukasal.NET1\AppData\LocalLow\Home\devtools\python\jython\jython2.7.0

set FOLDER=C:\Users\lukasal.NET1\AppData\LocalLow\Home\workspace.espd\espd\espd-web\src\main\resources\i18n\
set TRANSLATED=C:\Users\lukasal.NET1\AppData\LocalLow\Home\workspace.espd\props\translated
set RESULT=C:\Users\lukasal.NET1\AppData\LocalLow\Home\workspace.espd\props\result
set HEADER=C:\Users\lukasal.NET1\AppData\LocalLow\Home\workspace.espd\props\translated\header.txt

"%JAVAPATH%\bin\java" -jar "%JYTHONPATH%\jython.jar" props.py -f %FOLDER% -t %TRANSLATED% -r %RESULT% -h %HEADER%


