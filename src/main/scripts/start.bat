@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

java ${script.server.java_mem_opts} -classpath ..\conf;%LIB_JARS% ${script.server.mainClass}
goto end

:debug
java ${script.server.java_mem_opts} ${script.server.java_debug_opts} -classpath ..\conf;%LIB_JARS% ${script.server.mainClass}
goto end

:jmx
java ${script.server.java_mem_opts} ${script.server.java_jmx_opts} -classpath ..\conf;%LIB_JARS% ${script.server.mainClass}

:end
pause