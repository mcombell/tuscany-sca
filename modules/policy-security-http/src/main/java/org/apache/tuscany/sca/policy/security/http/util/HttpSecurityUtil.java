/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

package org.apache.tuscany.sca.policy.security.http.util;

import java.util.StringTokenizer;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.tuscany.sca.invocation.Message;
import org.apache.tuscany.sca.policy.authentication.basic.BasicAuthenticationPrincipal;

/**
 *
 * @version $Rev$ $Date$
 */
public class HttpSecurityUtil {
    
    /**
     * Check if Authorization header is available
     * @param request
     * @param response
     * @return
     */
    public static boolean hasAuthorizationHeader(HttpServletRequest request) {
        boolean result = false;
        if(request.getHeader("Authorization") != null) {
            result = true;
        }
        
        return result;
    }   
    
    /**
     * 
     * @param request
     * @param response
     * @return
     */
    public static String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }    

    public static Subject getSubject(Message msg){

        Subject subject = null;
        HttpServletRequest request = null;

        for (Object header : msg.getHeaders()){
            if (header instanceof Subject){
                subject  = (Subject)header;
                break;
            } else if( header instanceof HttpServletRequest) {
                request = (HttpServletRequest) header;
            }
        }

        //if there is no subject, but request is available
        //try to build a subject from authorization header
        if (subject == null & request != null) {
            if ( hasAuthorizationHeader(request)) {
                subject = getSubject(getAuthorizationHeader(request));
            }
            
        }
        if (subject == null){
            subject = new Subject(); 
            msg.getHeaders().add(subject); 
        }

        return subject;

    }
    
    public static Subject getSubject(String httpAuthorizationHeader){
        
        
        // get the security context
        Subject subject = new Subject();
        String user = null;
        String password = null;
        
        if (httpAuthorizationHeader != null) {
            StringTokenizer tokens = new StringTokenizer(httpAuthorizationHeader);
            if (tokens.hasMoreTokens()) {
                String basic = tokens.nextToken();
                if (basic.equalsIgnoreCase("Basic")) {
                    String credentials = tokens.nextToken();
                    String userAndPassword = new String(Base64.decodeBase64(credentials.getBytes()));
                    int colon = userAndPassword.indexOf(":");
                    if (colon != -1) {
                        user = userAndPassword.substring(0, colon);
                        password = userAndPassword.substring(colon + 1);
                    }
                }
            }
        }

        if(user != null && password != null) {
            BasicAuthenticationPrincipal principal = new BasicAuthenticationPrincipal(user, password);
            subject.getPrincipals().add(principal);
        }
        
        return subject;
    }
}
