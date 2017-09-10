package org.iban4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Run various tests on the Bic class.
 *
 * This class uses <a href="http://junit.org/junit4/javadoc/latest/org/junit/runners/Parameterized.html">org.junit.runners.Parametrized</a>
 * to run the test with predefined test data, in "input only" mode (as opposed to "input + expected data" mode)
 *
 * <i>Notes DATO 2017-09-09:</i>
 * <ul>
 *     <li>This used to be an inner class of {@code BicTest}, which was confusing. So I pulled it "up".</li>
 *     <li>This used to have test data in another class, which was confusing. Test data moved to here.</li>
 *     <li>This used to use "input + expected data" junit test mode, which was confusing. Changed to use "input only" mode.</li>
 * </ul>
 */

@RunWith(Parameterized.class)
public class CreationWithKnownBicsMustSucceedTest {

	private final String bicString;

	public CreationWithKnownBicsMustSucceedTest(String bicString) {
		this.bicString = bicString;
	}

	@Test
	public void bicConstructionWithValueOfShouldReturnBic() {
		assertThat(new Bic(bicString), is(notNullValue()));
	}

	/**
	 * Note that you need at least JUnit 4.12 for the "single parameter" data() method to work.
	 */

	@Parameters
	public static Object[] data() {
		return new String[]
				   {"DEUTDEFF",
					"DEUTDEFF500",
					"NEDSZAJJXXX",
					"DABADKKK",
					"UNCRIT2B912",
					"DSBACNBXSHA",
					"BNORPHMM"};
	}
}
