@echo off
@REM  Licensed to the Apache Software Foundation (ASF) under one
@REM  or more contributor license agreements.  See the NOTICE file
@REM  distributed with this work for additional information
@REM  regarding copyright ownership.  The ASF licenses this file
@REM  to you under the Apache License, Version 2.0 (the
@REM  "License"); you may not use this file except in compliance
@REM  with the License.  You may obtain a copy of the License at
@REM  
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM    
@REM  Unless required by applicable law or agreed to in writing,
@REM  software distributed under the License is distributed on an
@REM  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM  KIND, either express or implied.  See the License for the
@REM  specific language governing permissions and limitations
@REM  under the License.


@setlocal

set config=Release
if .Debug == .%1 (
echo Building Debug version
set config=Debug
)

if "%LIBXML2_HOME%" == "" (
echo "LIBXML2_HOME not set"
goto end
)
echo using LIBXML2: %LIBXML2_HOME%

if "%ICONV_HOME%" == "" (
echo "ICONV_HOME not set"
goto end
)
echo using ICONV: %ICONV_HOME%"


set TUSCANY_SDOCPP=%cd%\deploy
call vcvars32
cd vsexpress\tuscany_sdo\sdo_runtime
call vcbuild sdo_runtime.vcproj "%config%|Win32"

cd ..\sdo_test
call vcbuild sdo_test.vcproj "%config%|Win32"

if "%AXIS2C_HOME%" == "" (
echo "AXIS2C_HOME not set: sdo_axiom will not be built"
goto noaxis
)
echo using AXIS2C: %AXIS2C_HOME%

cd ..\sdo_axiom
call vcbuild sdo_axiom.vcproj "%config%|Win32"

cd ..\sdo_axiom_test
call vcbuild sdo_axiom_test.vcproj "%config%|Win32"

:noaxis

@endlocal
