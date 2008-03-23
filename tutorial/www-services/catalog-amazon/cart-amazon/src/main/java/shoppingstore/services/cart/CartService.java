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
package shoppingstore.services.cart;

import org.osoa.sca.annotations.Remotable;

import com.amazon.webservices.awsecommerceservice._2007_05_14.CartAdd;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartAddResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartClear;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartClearResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartCreate;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartCreateResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartGet;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartGetResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartModify;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartModifyResponse;

@Remotable
public interface CartService {
	
	public CartCreateResponse CartCreate(CartCreate cartCreate);
	
	public CartAddResponse CartAdd(CartAdd cartAdd);
	
	public CartModifyResponse CartModify(CartModify cartModify);
	
	public CartClearResponse CartClear(CartClear cartClear);

	public CartGetResponse CartGet(CartGet cartGet);
	
	//@Init
	//public void start();
	
	//@Destroy
	//public void stop();

}
