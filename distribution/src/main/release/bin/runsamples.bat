REM Licensed to the Apache Software Foundation (ASF) under one
REM or more contributor license agreements.  See the NOTICE file
REM distributed with this work for additional information
REM regarding copyright ownership.  The ASF licenses this file
REM to you under the Apache License, Version 2.0 (the
REM "License"); you may not use this file except in compliance
REM with the License.  You may obtain a copy of the License at
REM
REM   http://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing,
REM software distributed under the License is distributed on an
REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
REM KIND, either express or implied.  See the License for the
REM specific language governing permissions and limitations
REM under the License.
set BINARY_BASE=edit this variable to point to the root folder of your binary distribution
set RELEASE=${sdo.version}
set LIB=%BINARY_BASE%\lib
set CLASSPATH=%LIB%\sample-sdo-%RELEASE%.jar;%LIB%\tuscany-sdo-api-r2.1-%RELEASE%.jar;%LIB%\tuscany-sdo-lib-%RELEASE%.jar;%LIB%\tuscany-sdo-impl-%RELEASE%.jar;%LIB%\tuscany-sdo-tools-%RELEASE%.jar;%LIB%\codegen-ecore-2.2.3.jar;%LIB%\codegen-2.2.3.jar;%LIB%\ecore-2.2.3.jar;%LIB%\ecore-change-2.2.3.jar;%LIB%\ecore-xmi-2.2.3.jar;%LIB%\common-2.2.3.jar;%LIB%\xsd-2.2.3.jar;%LIB%\stax-api-1.0.1.jar
java org.apache.tuscany.samples.sdo.ExecuteSamples