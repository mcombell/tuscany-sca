#!/bin/sh

#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#  
#    http://www.apache.org/licenses/LICENSE-2.0
#    
#  Unless required by applicable law or agreed to in writing,
#  software distributed under the License is distributed on an
#  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
#  specific language governing permissions and limitations
#  under the License.

TUSCANY_SDOCPP_HOME=`pwd`

if [ x$AXIS2C_HOME = x ]; then
echo "AXIS2C_HOME not set. not building SDO Axiom utility"
else
echo "Using Axis2C installed at $AXIS2C_HOME"
WITH_AXIS2C=--with-axis2c
fi

cd ${TUSCANY_SDOCPP_HOME}
./configure --prefix=${TUSCANY_SDOCPP_HOME}/deploy --enable-static=no ${WITH_AXIS2C} 
make
make install

cd ${TUSCANY_SDOCPP_HOME}/samples
export TUSCANY_SDOCPP=${TUSCANY_SDOCPP_HOME}/deploy
./configure --prefix=${TUSCANY_SDOCPP_HOME}/deploy --enable-static=no
make
make install

cd ${TUSCANY_SDOCPP_HOME}/deploy
for i in `find . -name "*.la"`
do
	rm $i
done