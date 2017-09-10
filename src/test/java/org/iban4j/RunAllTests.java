package org.iban4j;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite to run all tests using a <a href="http://junit.org/junit4/javadoc/latest/org/junit/runners/Suite.html">Suite</a> runner.
 *
 * See also <a href="https://stackoverflow.com/questions/457276/junit4-test-suites">How do I create test suites with Junit4?</a>
 *
 * This class is empty because it is just a "hook" or a node in a graph created by annotations.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BicTest.class,
		BicValidationTest.class,
		CountryCodesOfJdkAndIban4jComparisonTest.class,
		CountryCodeTest.class,
		CreationWithKnownBicsMustSucceedTest.class,
		IbanTest.class,
		IbanUtilTest.class,
		NextIntTest.class,
		BicGeneratorTest.class
})
public class RunAllTests {
	// nothing
}
