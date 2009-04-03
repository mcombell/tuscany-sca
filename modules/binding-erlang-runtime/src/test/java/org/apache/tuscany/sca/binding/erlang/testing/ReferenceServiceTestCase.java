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

package org.apache.tuscany.sca.binding.erlang.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.tuscany.sca.binding.erlang.impl.TypeMismatchException;
import org.apache.tuscany.sca.binding.erlang.impl.exceptions.ErlangException;
import org.apache.tuscany.sca.binding.erlang.testing.dynaignore.IgnorableRunner;
import org.apache.tuscany.sca.binding.erlang.testing.dynaignore.IgnoreTest;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangBinary;
import com.ericsson.otp.erlang.OtpErlangBoolean;
import com.ericsson.otp.erlang.OtpErlangDouble;
import com.ericsson.otp.erlang.OtpErlangInt;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

/**
 * Test is annotated with test runner, which will ignore tests if epmd is not
 * available
 * 
 * @version $Rev$ $Date$
 */
@RunWith(IgnorableRunner.class)
public class ReferenceServiceTestCase {

	private static final String EPMD_COMMAND = "epmd";

	private static MboxInterface mboxReference;
	private static MboxInterface timeoutMboxReference;
	private static ServiceInterface moduleReference;
	private static ServiceInterface cookieModuleReference;
	private static ServiceInterface invalidCookieModuleReference;
	private static ServiceInterface timeoutModuleReference;
	private static OtpNode serNode;
	private static OtpMbox serMbox;
	private static OtpNode refNode;
	private static OtpMbox refMbox;
	private static Process epmdProcess;

	@BeforeClass
	public static void init() throws IOException {
		try {
			epmdProcess = Runtime.getRuntime().exec(EPMD_COMMAND);
			SCADomain domain = SCADomain
					.newInstance("ErlangReference.composite");
			SCADomain.newInstance("ErlangService.composite");
			ReferenceTestComponentImpl component = domain.getService(
					ReferenceTestComponentImpl.class, "ReferenceTest");

			mboxReference = component.getMboxReference();
			timeoutMboxReference = component.getTimeoutMboxReference();
			moduleReference = component.getModuleReference();
			cookieModuleReference = component.getCookieModuleReference();
			invalidCookieModuleReference = component
					.getInvalidCookieModuleReference();
			timeoutModuleReference = component.getTimeoutModuleReference();

			serNode = new OtpNode("MboxServer");
			serMbox = serNode.createMbox("sendArgs");
			refNode = new OtpNode("MboxClient");
			refMbox = refNode.createMbox("connector_to_SCA_mbox");
		} catch (IOException e) {
			System.out.println("Problem executing " + EPMD_COMMAND + ": "
					+ e.getLocalizedMessage() + ". Tests will be IGNORED.");
		}
	}

	@AfterClass
	public static void clean() {
		if (epmdProcess != null) {
			epmdProcess.destroy();
		}
	}

	@Before
	public void before() {
		if (epmdProcess == null) {
			throw new IgnoreTest();
		}
	}

	/**
	 * Tests passing strings
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testStrings() throws Exception {
		String strArg = "Test message";
		String strResult = "OK";
		MboxListener mboxListener = new MboxListener(serMbox, strResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		String testResult = mboxReference.sendArgs(strArg);
		assertEquals(strArg, ((OtpErlangString) mboxListener.getMsg().getMsg())
				.stringValue());
		assertEquals(strResult, testResult);
	}

	/**
	 * Tests passing booleans
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testBooleans() throws Exception {
		boolean booleanArg = true;
		boolean booleanResult = false;
		MboxListener mboxListener = new MboxListener(serMbox, booleanResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		boolean testResult = mboxReference.sendArgs(booleanArg);
		assertEquals(booleanArg, ((OtpErlangAtom) mboxListener.getMsg()
				.getMsg()).booleanValue());
		assertEquals(booleanResult, testResult);
	}

	/**
	 * Tests passing floats
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testFloats() throws Exception {
		float floatArg = 1.0f;
		float floatResult = 2.0f;
		MboxListener mboxListener = new MboxListener(serMbox, floatResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		float testResult = mboxReference.sendArgs(floatArg);
		assertEquals(floatArg, ((OtpErlangDouble) mboxListener.getMsg()
				.getMsg()).doubleValue(), 0);
		assertEquals(floatResult, testResult, 0);
	}

	/**
	 * Tests passing doubles
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testDoubles() throws Exception {
		double doubleArg = 1.0f;
		double doubleResult = 2.0f;
		MboxListener mboxListener = new MboxListener(serMbox, doubleResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		double testResult = mboxReference.sendArgs(doubleArg);
		assertEquals(doubleArg, ((OtpErlangDouble) mboxListener.getMsg()
				.getMsg()).doubleValue(), 0);
		assertEquals(doubleResult, testResult, 0);
	}

	/**
	 * Tests passing long values
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testLongs() throws Exception {
		long longArg = 1;
		long longResult = 2;
		MboxListener mboxListener = new MboxListener(serMbox, longResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		long testResult = mboxReference.sendArgs(longArg);
		assertEquals(longArg, ((OtpErlangLong) mboxListener.getMsg().getMsg())
				.longValue(), 0);
		assertEquals(longResult, testResult, 0);
	}

	/**
	 * Tests passing integers
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testInts() throws Exception {
		int intArg = 1;
		int intResult = 2;
		MboxListener mboxListener = new MboxListener(serMbox, intResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		int testResult = mboxReference.sendArgs(intArg);
		assertEquals(intArg, ((OtpErlangLong) mboxListener.getMsg().getMsg())
				.intValue(), 0);
		assertEquals(intResult, testResult, 0);
	}

	/**
	 * Tests passing chars
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testChars() throws Exception {
		char charArg = 1;
		char charResult = 2;
		MboxListener mboxListener = new MboxListener(serMbox, charResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		char testResult = mboxReference.sendArgs(charArg);
		assertEquals(charArg, ((OtpErlangLong) mboxListener.getMsg().getMsg())
				.charValue(), 0);
		assertEquals(charResult, testResult, 0);
	}

	/**
	 * Tests passing shorts
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testShorts() throws Exception {
		short shortArg = 1;
		short shortResult = 2;
		MboxListener mboxListener = new MboxListener(serMbox, shortResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		short testResult = mboxReference.sendArgs(shortArg);
		assertEquals(shortArg, ((OtpErlangLong) mboxListener.getMsg().getMsg())
				.shortValue(), 0);
		assertEquals(shortResult, testResult, 0);
	}

	/**
	 * Tests passing bytes
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testBytes() throws Exception {
		byte byteArg = 1;
		byte byteResult = 2;
		MboxListener mboxListener = new MboxListener(serMbox, byteResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		byte testResult = mboxReference.sendArgs(byteArg);
		assertEquals(byteArg, ((OtpErlangLong) mboxListener.getMsg().getMsg())
				.byteValue(), 0);
		assertEquals(byteResult, testResult, 0);
	}

	/**
	 * Tests passing multiple arguments
	 * 
	 * @throws Exception
	 */
	// TODO: this test fails sometime
	@Test(timeout = 1000)
	public void testMultipleArguments() throws Exception {
		MboxListener mboxListener = new MboxListener(serMbox, true);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		String testString = "TupleString";
		int testInt = 10;
		mboxReference.sendArgs(testInt, testString);
		assertEquals(testInt, ((OtpErlangLong) ((OtpErlangTuple) mboxListener
				.getMsg().getMsg()).elementAt(0)).longValue());
		assertEquals(testString,
				((OtpErlangString) ((OtpErlangTuple) mboxListener.getMsg()
						.getMsg()).elementAt(1)).stringValue());
	}

	/**
	 * Tests passing tuples
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testTuples() throws Exception {
		StructuredTuple tupleResult = new StructuredTuple();
		tupleResult.arg1.arg1 = 1;
		tupleResult.arg1.arg2 = "Tuple inside tuple";
		tupleResult.arg2 = "Tuple!";
		tupleResult.arg3 = true;
		MboxListener mboxListener = new MboxListener(serMbox, tupleResult);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		StructuredTuple testArg = new StructuredTuple();
		testArg.arg2 = "Arg2a";
		testArg.arg3 = true;
		testArg.arg1.arg1 = 10;
		testArg.arg1.arg2 = "Arg2b";
		StructuredTuple testResult = mboxReference.sendArgs(testArg);
		assertEquals(tupleResult, testResult);
		OtpErlangTuple received = (OtpErlangTuple) mboxListener.getMsg()
				.getMsg();
		assertEquals(testArg.arg1.arg1,
				((OtpErlangLong) ((OtpErlangTuple) received.elementAt(0))
						.elementAt(0)).longValue());
		assertEquals(testArg.arg1.arg2,
				((OtpErlangString) ((OtpErlangTuple) received.elementAt(0))
						.elementAt(1)).stringValue());
		assertEquals(testArg.arg2, ((OtpErlangString) received.elementAt(1))
				.stringValue());
		assertEquals(testArg.arg3, ((OtpErlangAtom) received.elementAt(2))
				.booleanValue());
	}

	/**
	 * Test passing Erlang binaries
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testBinaries() throws Exception {
		byte[] testArg = { 0, 1 };
		MboxListener mboxListener = new MboxListener(serMbox, testArg);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		byte[] testResult = mboxReference.sendArgs(testArg);
		assertEquals(testArg.length, testResult.length);
		for (int i = 0; i < testArg.length; i++) {
			assertEquals(testArg[i], testResult[i]);
		}
		OtpErlangBinary received = (OtpErlangBinary) mboxListener.getMsg()
				.getMsg();
		assertEquals(testArg.length, received.size());
		for (int i = 0; i < testArg.length; i++) {
			assertEquals(testArg[i], received.binaryValue()[i]);
		}
	}

	/**
	 * Tests passing lists
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testLists() throws Exception {
		String[] testArg = new String[] { "One", "Two", "Three" };
		MboxListener mboxListener = new MboxListener(serMbox, testArg);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		String[] testResult = mboxReference.sendArgs(testArg);
		assertEquals(testArg.length, testResult.length);
		for (int i = 0; i < testArg.length; i++) {
			assertEquals(testArg[i], testResult[i]);
		}
		OtpErlangList received = (OtpErlangList) mboxListener.getMsg().getMsg();
		assertEquals(testArg.length, received.arity());
		for (int i = 0; i < testArg.length; i++) {
			assertEquals(testArg[i], ((OtpErlangString) received.elementAt(i))
					.stringValue());
		}
	}

	/**
	 * Tests passing multidimensional lists
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testMultiDimLists() throws Exception {
		String[][] testArg = new String[][] { { "One", "Two" },
				{ "Three", "Four", "Five" }, { "Six" } };
		MboxListener mboxListener = new MboxListener(serMbox, testArg);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		String[][] testResult = mboxReference.sendArgs(testArg);
		assertEquals(testArg.length, testResult.length);
		for (int i = 0; i < testArg.length; i++) {
			for (int j = 0; j < testArg[i].length; j++) {
				assertEquals(testArg[i][j], testResult[i][j]);
			}
		}
		OtpErlangList received = (OtpErlangList) mboxListener.getMsg().getMsg();
		assertEquals(testArg.length, received.arity());
		for (int i = 0; i < testArg.length; i++) {
			for (int j = 0; j < testArg[i].length; j++) {
				assertEquals(testArg[i][j],
						(((OtpErlangString) ((OtpErlangList) received
								.elementAt(i)).elementAt(j)).stringValue()));
			}
		}
	}

	/**
	 * Tests mismatched interface
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void typeMismatch() throws Exception {
		try {
			StructuredTuple arg = new StructuredTuple();
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			String[] arg = new String[] { "test" };
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			long arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			int arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			short arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			char arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			byte arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			double arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			float arg = 1;
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			String arg = "1";
			MboxListener mboxListener = new MboxListener(serMbox, true);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}

		try {
			boolean arg = true;
			MboxListener mboxListener = new MboxListener(serMbox, 1);
			Thread mboxThread = new Thread(mboxListener);
			mboxThread.start();
			mboxReference.sendArgs(arg);
		} catch (Exception e) {
			assertEquals(TypeMismatchException.class, e.getClass());
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Basic RPC test, without arguments
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 10000000)
	public void testRPC() throws Exception {
		String[] result = moduleReference.sayHellos();
		assertEquals(2, result.length);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
	}

	/**
	 * Tests RPC with arguments
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testRPCWithArgs() throws Exception {
		String arg1 = "One";
		String arg2 = "Two";
		String testResult = moduleReference.sayHello(arg1, arg2);
		assertEquals("Hello " + arg1 + " " + arg2, testResult);
	}

	/**
	 * Tests RPC with structured arguments
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testRPCWithComplexArgs() throws Exception {
		StructuredTuple arg = new StructuredTuple();
		arg.arg1.arg2 = "Not empty";
		arg.arg2 = "Not empty";
		StructuredTuple testResult = moduleReference.passComplexArgs(arg,
				new String[] { "some", "array" });
		assertEquals(arg, testResult);
	}

	/**
	 * Tests RPC with no result
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testRPCWithVoidResult() throws Exception {
		moduleReference.doNothing();
	}

	/**
	 * Tests handling requests pointing to unknown functions
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testUnknownFunction() throws Exception {

		// following functions differs by parameters

		try {
			moduleReference.sayHello();
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
		}

		try {
			moduleReference.sayHello("1");
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
		}

		try {
			moduleReference.sayHello(1, 2);
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
		}

		// for following ones name not exists

		moduleReference.notExist();

		try {
			moduleReference.notExistWithException();
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
		}
	}
	
	/**
	 * Tests mbox with retrieving and answering with basic arguments
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testMbox() throws Exception {
		OtpErlangObject[] args = new OtpErlangObject[2];
		args[0] = new OtpErlangString("world");
		args[1] = new OtpErlangString("!");
		OtpErlangTuple tuple = new OtpErlangTuple(args);
		refMbox.send("sayHello", "RPCServerMbox", tuple);
		OtpErlangString result = (OtpErlangString) refMbox.receiveMsg()
				.getMsg();
		assertEquals("Hello world !", result.stringValue());
	}

	/**
	 * Tests service mbox receiving complex message
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 2000)
	public void testMboxWithComplexArgs() throws Exception {
		int arg1 = 1;
		String arg2 = "arg2";
		String arg3 = "arg3";
		boolean arg4 = true;

		OtpErlangObject[] smallTupleContent = new OtpErlangObject[2];
		smallTupleContent[0] = new OtpErlangInt(arg1);
		smallTupleContent[1] = new OtpErlangString(arg2);
		OtpErlangTuple smallTuple = new OtpErlangTuple(smallTupleContent);
		OtpErlangObject[] structuredTupleContent = new OtpErlangObject[3];
		structuredTupleContent[0] = smallTuple;
		structuredTupleContent[1] = new OtpErlangString(arg3);
		structuredTupleContent[2] = new OtpErlangBoolean(arg4);
		OtpErlangTuple structuredTuple = new OtpErlangTuple(
				structuredTupleContent);
		OtpErlangObject[] secondArg = new OtpErlangObject[2];
		secondArg[0] = new OtpErlangString("in");
		secondArg[1] = new OtpErlangString("array");
		OtpErlangList list = new OtpErlangList(secondArg);
		OtpErlangObject[] argsContent = new OtpErlangObject[2];
		argsContent[0] = structuredTuple;
		argsContent[1] = list;
		OtpErlangTuple args = new OtpErlangTuple(argsContent);
		refMbox.send("passComplexArgs", "RPCServerMbox", args);
		OtpErlangObject result = refMbox.receiveMsg().getMsg();
		assertEquals(arg1,
				((OtpErlangLong) ((OtpErlangTuple) ((OtpErlangTuple) result)
						.elementAt(0)).elementAt(0)).intValue());
		assertEquals(arg2,
				((OtpErlangString) ((OtpErlangTuple) ((OtpErlangTuple) result)
						.elementAt(0)).elementAt(1)).stringValue());
		assertEquals(arg3, ((OtpErlangString) ((OtpErlangTuple) result)
				.elementAt(1)).stringValue());
		assertEquals(arg4, ((OtpErlangAtom) ((OtpErlangTuple) result)
				.elementAt(2)).booleanValue());
	}

	/**
	 * Tests timeout feature for reference binding messaging
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 4000)
	public void testMboxReferenceTimeouts() throws Exception {
		long timeBiggerThanTimeout = 1000;
		String stringResult = "result";

		// doing test for response time bigger than declared timeout (500)
		MboxListener mboxListener = new MboxListener(serMbox, stringResult,
				timeBiggerThanTimeout);
		Thread mboxThread = new Thread(mboxListener);
		mboxThread.start();
		try {
			// timeout exception expected
			timeoutMboxReference.sendArgs("");
			fail("Exception expected");
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
			assertEquals(e.getCause().getClass(), InterruptedException.class);
		}

		// doing test for response time smaller than declared timeout (500)
		mboxListener = new MboxListener(serMbox, stringResult, 0);
		mboxThread = new Thread(mboxListener);
		mboxThread.start();
		// expecting no timeout exception
		String testResult = timeoutMboxReference.sendArgs("");
		assertEquals(stringResult, testResult);

		// doing test for response time which will cause timeout. This time
		// there is no declared exception in users operation so we expect no
		// exception and null result
		mboxListener = new MboxListener(serMbox, new byte[1],
				timeBiggerThanTimeout);
		mboxThread = new Thread(mboxListener);
		mboxThread.start();
		// expecting no timeout exception
		byte[] result = timeoutMboxReference.sendArgs(new byte[1]);
		assertEquals(null, result);
	}

	/**
	 * Tests timeout feature for reference binding RPC
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 4000)
	public void testRpcReferenceTimeouts() throws Exception {

		// doing test for response time which will cause timeout. Method does
		// not
		// declare exception so only null value will be returned
		String result1 = timeoutModuleReference.sayHello("hello", "world");
		assertEquals(null, result1);

		// doing test for response time which will cause timeout. Method declare
		// exception, so expecting one
		try {
			timeoutModuleReference.sayHellos();
			fail("Exception expected");
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
		}

		// doing test for response time shorter than timeout
		timeoutModuleReference.doNothing();
	}

	/**
	 * Tests timeout feature for service side bindings
	 * @throws Exception
	 */
	@Test(timeout = 4000)
	public void testServiceTimeouts() throws Exception {
		OtpSelf self = new OtpSelf("tmp_connector_"
				+ System.currentTimeMillis());
		OtpPeer peer = new OtpPeer("RPCServerTimeout");
		OtpConnection connection = self.connect(peer);
		// delay message sending after connecting
		Thread.sleep(1000);
		// service binding timeout set to 500 so after that time it will give up
		// and close connection
		try {
			connection.send("rex", new OtpErlangString("test"));
			fail("Exception expected");
		} catch (Exception e) {
			assertEquals(IOException.class, e.getClass());
		}

		connection = self.connect(peer);
		// sending message immediately and encountering no connection close
		connection.send("rex", new OtpErlangString("test"));

	}

	/**
	 * Tests cookie feature for both reference and service bindings RPC
	 * 
	 * @throws Exception
	 */
	@Test(timeout = 1000)
	public void testReferenceCookies() throws Exception {
		// testing wrong cookie
		try {
			invalidCookieModuleReference.sayHellos();
			fail("Exception expected");
		} catch (Exception e) {
			assertEquals(ErlangException.class, e.getClass());
			assertEquals(OtpAuthException.class, e.getCause().getClass());
		}

		// testing correct cookie
		cookieModuleReference.sayHellos();

	}
	
}
