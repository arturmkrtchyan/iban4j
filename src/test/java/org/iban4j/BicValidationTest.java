/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iban4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

/**
 * Here we check Exceptions thrown during validation using <a href="http://junit.org/junit4/javadoc/latest/org/junit/rules/ExpectedException.html">org.junit.rukes.ExpectedException</a>
 *
 * <i>Notes DATO 2017-09-09:
 * <ul>
 *     <li>Note DATO: This used to be an inner class of {@code BicUtilTest}, which was confusing. So I pulled it "up". No
 * need to run it with <a href="http://junit.org/junit4/javadoc/latest/org/junit/experimental/runners/Enclosed.html">org.junit.experimental.runners.Enclosed</a>
 * after that</li>
 * 	   <li>Note DATO: Renamed class to {@code BicValidationTest} to make clear what it does.</li>
 * </ul>
 * </i>
 */

@RunWith(Enclosed.class)
public class BicValidationTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();


	@Test
	public void bicValidationWithNullShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Null can't be a valid Bic"));
		BicUtil.validate(null);
	}

	@Test
	public void bicValidationWithEmptyStringShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Empty string can't be a valid Bic"));
		BicUtil.validate("");
	}

	@Test
	public void bicValidationWithLessCharactersShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Bic length must be 8 or 11"));
		BicUtil.validate("DEUTFF");
	}

	@Test
	public void bicValidationWithMoreCharactersShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Bic length must be 8 or 11"));
		BicUtil.validate("DEUTFFDEUTFF");
	}

	@Test
	public void bicValidationWithLowercaseShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Bic must contain only upper case letters"));
		BicUtil.validate("DEUTdeFF");
	}

	@Test
	public void bicValidationWithInvalidBankCodeShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Bank code must contain only letters"));
		BicUtil.validate("DEU1DEFF");
	}

	@Test
	public void bicValidationWithNonExistingCountryCodeShouldThrowException() {
		expectedException.expect(UnsupportedCountryException.class);
		expectedException.expectMessage(containsString("Country code is not supported"));
		BicUtil.validate("DEUTDDFF");
	}

	@Test
	public void bicValidationWithInvalidCountryCodeShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Bic country code must contain upper case letters"));
		BicUtil.validate("DEUT_1FF");
	}

	@Test
	public void bicValidationWithInvalidLocationCodeShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Location code must contain only letters or digits"));
		BicUtil.validate("DEUTDEF ");
	}

	@Test
	public void bicValidationWithInvalidBranchCodeShouldThrowException() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage(containsString("Branch code must contain only letters or digits"));
		BicUtil.validate("DEUTDEFF50_");
	}

	@Test(expected = BicFormatException.class)
	public void badLocationCodeShouldThrowBadLetter() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage("must contain only letters or digits");
		Bic bic = new Bic("DEUTDE!!500");
	}

	@Test(expected = BicFormatException.class)
	public void badLocationCodeShouldThrow2() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage("first letter must not");
		Bic bic = new Bic("DEUTDE0F500");
	}

	@Test(expected = BicFormatException.class)
	public void badLocationCodeShouldThrow3() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage("first letter must not");
		Bic bic = new Bic("DEUTDE1F500");
	}

	@Test(expected = BicFormatException.class)
	public void badLocationCodeShouldThrow4() {
		expectedException.expect(BicFormatException.class);
		expectedException.expectMessage("second letter must not");
		Bic bic = new Bic("DEUTDE2O500");
	}
}
