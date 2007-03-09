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

set buildtype=Release
if .%1 == .Debug (
   set buildtype=Debug
)

set TUSCANY_SDOCPP=%cd%\deploy

echo using TUSCANY_SDOCPP: %TUSCANY_SDOCPP%

set PATH=%TUSCANY_SDOCPP%\bin;%PATH%

set SCATESTPATH=%cd%\vsexpress\tuscany_sdo\sdo_test\%buildtype%
cd runtime\core\test
"%SCATESTPATH%\sdo_test"

:end
@endlocal
