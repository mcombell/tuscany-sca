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

dnl run autogen.sh to generate the configure script.

AC_PREREQ(2.59)
AC_INIT(tuscany_sdo_cpp, 1.0-incubator-M3)
AC_CANONICAL_SYSTEM
AM_CONFIG_HEADER(tuscany_sdo_config.h)
AM_INIT_AUTOMAKE(AC_PACKAGE_NAME, AC_PACKAGE_VERSION)
AC_PREFIX_DEFAULT(/usr/local/tuscany/sdo)

# Checks for programs.
AC_PROG_CXX
AC_PROG_CC
AC_PROG_CPP
AC_PROG_INSTALL
AC_PROG_LN_S
AC_PROG_MAKE_SET
AC_PROG_LIBTOOL

# Check for running on Darwin
AC_MSG_CHECKING([Checking if running on Darwin])
UNAME=`uname -s`
if test "x$UNAME" = "xDarwin"; then
  AC_DEFINE([IS_DARWIN], [1], [Set to 1 when running on Darwin - Mac OSX])
  AC_MSG_RESULT(yes)
  AC_SUBST([libsuffix],[".dylib"])
  is_darwin=true
else
  AC_MSG_RESULT(no)
  AC_SUBST([libsuffix],[".so"])
  is_darwin=false
fi
AM_CONDITIONAL([DARWIN], [test x$is_darmin = xtrue])

# Checks for libraries.

# Checks for header files.
AC_HEADER_STDC
AC_CHECK_HEADERS([inttypes.h stdlib.h string.h sys/time.h wchar.h])

# Checks for typedefs, structures, and compiler characteristics.
AC_HEADER_STDBOOL
AC_C_CONST
AC_STRUCT_TM

# Checks for library functions.
AC_FUNC_ERROR_AT_LINE
AC_FUNC_VPRINTF
AC_CHECK_FUNCS([strchr strrchr])

AC_MSG_CHECKING(whether to build SDO Axiom utility)
AC_ARG_WITH(axis2c, [AS_HELP_STRING([--with-axis2c],[build SDO Axiom utility [default=no]])],
[ case "${withval}" in
  yes)
    AC_MSG_RESULT(yes)
    with_axis2c=true
    ;;
  *)
    AC_MSG_RESULT(no)
    with_axis2c=false
    ;;
  esac ],
[ AC_MSG_RESULT(no)
    with_axis2c=false ]
)
AM_CONDITIONAL([WITH_AXIS2C], [test x$with_axis2c = xtrue])

AC_CONFIG_FILES([Makefile
                 runtime/Makefile
                 runtime/core/Makefile
                 runtime/core/src/Makefile
                 runtime/core/src/commonj/Makefile
                 runtime/core/src/commonj/sdo/Makefile
                 runtime/core/test/Makefile
                 runtime/core/sdo_axiom/Makefile
                 runtime/core/sdo_axiom_test/Makefile])
AC_OUTPUT

