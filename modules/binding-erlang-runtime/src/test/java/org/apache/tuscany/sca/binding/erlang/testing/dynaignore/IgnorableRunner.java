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

package org.apache.tuscany.sca.binding.erlang.testing.dynaignore;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @version $Rev$ $Date$
 */
public class IgnorableRunner extends Runner {

	private static final class Notifier extends RunNotifier {
		private final RunNotifier notifier;

		public Notifier(final RunNotifier notifier) {
			this.notifier = notifier;
		}

		public void addFirstListener(final RunListener listener) {
			notifier.addFirstListener(listener);
		}

		public void addListener(final RunListener listener) {
			notifier.addListener(listener);
		}

		@Override
		public boolean equals(final Object obj) {
			return notifier.equals(obj);
		}

		@Override
		public void fireTestFailure(final Failure failure) {
			if (failure.getException().getClass() == IgnoreTest.class) {
				notifier.fireTestIgnored(failure.getDescription());
			} else {
				notifier.fireTestFailure(failure);
			}
		}

		@Override
		public void fireTestFinished(final Description description) {
			notifier.fireTestFinished(description);
		}

		@Override
		public void fireTestIgnored(final Description description) {
			notifier.fireTestIgnored(description);
		}

		@Override
		public void fireTestRunFinished(final Result result) {
			notifier.fireTestRunFinished(result);
		}

		@Override
		public void fireTestRunStarted(final Description description) {
			notifier.fireTestRunStarted(description);
		}

		@Override
		public void fireTestStarted(final Description description)
				throws StoppedByUserException {
			notifier.fireTestStarted(description);
		}

		@Override
		public int hashCode() {
			return notifier.hashCode();
		}

		@Override
		public void pleaseStop() {
			notifier.pleaseStop();
		}

		@Override
		public void removeListener(final RunListener listener) {
			notifier.removeListener(listener);
		}

		public void testAborted(final Description description,
				final Throwable cause) {
			((Notifier) notifier).testAborted(description, cause);
		}

		@Override
		public String toString() {
			return notifier.toString();
		}

	}

	Runner runner = null;

	public IgnorableRunner(Class<?> testClass) {
		try {
			runner = new BlockJUnit4ClassRunner(testClass);
		} catch (InitializationError e) {
			e.printStackTrace();
		}
	}

	@Override
	public Description getDescription() {
		return runner.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		runner.run(new Notifier(notifier));
	}

}
