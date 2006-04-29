/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.samples.helloworldmc;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;

/**
 * This class implements the HelloWorld service component.
 */
@Service(HelloWorldService.class)
public class HelloWorldServiceComponentImpl implements HelloWorldService {

    public String greetingMiddle;
    public GreetingPortionProvider greetingPrefix;
    public GreetingPortionProvider greetingSuffix;

    @Property
    public void setGreetingMiddle(String greetingMiddle) {
        this.greetingMiddle = greetingMiddle;
    }

    @Reference
    public void setGreetingPrefix(GreetingPortionProvider greetingPrefix) {
        this.greetingPrefix = greetingPrefix;
    }

    @Reference
    public void setGreetingSuffix(GreetingPortionProvider greetingSuffix) {
        this.greetingSuffix = greetingSuffix;
    }

    /**
     * @return
     */
    public String getGreetings() {
        String middle;
        if (greetingMiddle == null)
            middle = "";
        else
            middle = greetingMiddle;

        return greetingPrefix.getGreetingPortion() + " " + middle + " " + greetingSuffix.getGreetingPortion();
    }

}
