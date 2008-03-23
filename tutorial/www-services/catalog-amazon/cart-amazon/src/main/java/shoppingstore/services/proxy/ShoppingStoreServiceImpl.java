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
package shoppingstore.services.proxy;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;

import shoppingstore.services.cart.CartService;

import com.amazon.webservices.awsecommerceservice._2007_05_14.CartAddResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartClearResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartCreateResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartGetResponse;
import com.amazon.webservices.awsecommerceservice._2007_05_14.CartModifyResponse;

@Scope("COMPOSITE")
public class ShoppingStoreServiceImpl implements ShoppingStoreService {

	private CartService cartService;
    
    @Reference
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

	public CartAddResponse CartAdd(
			com.amazon.webservices.awsecommerceservice._2007_05_14.CartAdd cartAdd) {
		return cartService.CartAdd(cartAdd);
	}

	public CartClearResponse CartClear(
			com.amazon.webservices.awsecommerceservice._2007_05_14.CartClear cartClear) {
		return cartService.CartClear(cartClear);
	}

	public CartCreateResponse CartCreate(
			com.amazon.webservices.awsecommerceservice._2007_05_14.CartCreate cartCreate) {
		return cartService.CartCreate(cartCreate);
	}

	public CartGetResponse CartGet(
			com.amazon.webservices.awsecommerceservice._2007_05_14.CartGet cartGet) {
		return cartService.CartGet(cartGet);
	}

	public CartModifyResponse CartModify(
			com.amazon.webservices.awsecommerceservice._2007_05_14.CartModify cartModify) {
		return cartService.CartModify(cartModify);
	}

}
