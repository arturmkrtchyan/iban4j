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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Here we are performing various tests of the {@code CountryCode} enum.
 *
 * Note the following specificity due to the fact that there are {@code CountryCode} which are "live" and
 * {@code CountryCode} which are "transitional" (i.e. not live / being retired)
 *
 * Either you iterate over {@code CountryCode.values()} which includes all codes.
 * Then you must use the two-parameter {@code CountryCode.getByCode()} must use the two-parameter form to allow transitional codes to be retrieved.
 *
 * Or you iterate over {@code CountryCodeSets.iban4jCodesLiveOnly} (also obtainable via
 * {@code CountryCodeSets.getIban4jCodes(true)}), which includes only the live codes.
 * Then {@code CountryCode.getByCode()} can be used in the the one-parameter form as it will ever only retrieve live codes.
 */

public class CountryCodeTest {

	@Test
	public void getByCodeWithAlpha2CodeShouldReturnCountryIteratingOverValues() {
		for (CountryCode code : CountryCode.values()) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha2(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithAlpha2CodeShouldReturnCountryIteratingOverLiveSet() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(true)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha2(), true);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithAlpha2CodeShouldReturnCountryIteratingOverFullSet() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha2(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithLowerCaseAlpha2CodeShouldReturnCountry() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toLowerCase(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithUpperCaseAlpha2CodeShouldReturnCountry() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toUpperCase(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithAlpha3CodeShouldReturnCountryCode() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha3(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithLowerCaseAlpha3CodeShouldReturnCountry() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			String reCode = code.getAlpha3().toLowerCase();
			CountryCode newCode = CountryCode.getByCode(reCode, false);
			assertThat("3-letter code '" + reCode + "' yielded '" + newCode + "'", newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithUpperCaseAlpha3CodeShouldReturnCountry() {
		for (CountryCode code : CountryCodeSets.getIban4jCodes(false)) {
			CountryCode newCode = CountryCode.getByCode(code.getAlpha3().toUpperCase(), false);
			assertThat(newCode, is(equalTo(code)));
		}
	}

	@Test
	public void getByCodeWithNullCodeShouldReturnNull() {
		CountryCode code = CountryCode.getByCode(null);
		// the IDEA inspector finds this is always true! Well done!
		assertThat(code, is(nullValue()));
	}

	@Test
	public void getByCodeWith4DigitCodeShouldReturnNull() {
		CountryCode code = CountryCode.getByCode("XXXX");
		assertThat(code, is(nullValue()));
	}

	@Test
	public void getByCodeWithWrongAlpha2CodeShouldReturnNull() {
		CountryCode code = CountryCode.getByCode("XX");
		assertThat(code, is(nullValue()));
	}

	@Test
	public void getByCodeWithWrongAlpha3CodeShouldReturnNull() {
		CountryCode code = CountryCode.getByCode("XXX");
		assertThat(code, is(nullValue()));
	}

	@Test
	public void getNameWithDECodeShouldReturnGermany() {
		assertThat(CountryCode.DE.getDisplayName(), is(equalTo("Germany")));
	}

	@Test
	public void getAlpha2WithDECodeShouldReturnGermany() {
		assertThat(CountryCode.DE.getAlpha2(), is(equalTo("DE")));
	}

	@Test
	public void getAlpha3WithDECodeShouldReturnGermany() {
		assertThat(CountryCode.DE.getAlpha3(), is(equalTo("DEU")));
	}

	@Test
	public void getByCodeLenientWithEmptyCode() {
		assertNull(CountryCode.getByCode(" "));
		assertNull(CountryCode.getByCode(""));
		assertNull(CountryCode.getByCode(null));
	}

	@Test
	public void getByCodeLenientWithInexactTwoLetterCode() {
		assertEquals(CountryCode.LU, CountryCode.getByCode("LU"));
		assertEquals(CountryCode.LU, CountryCode.getByCode("Lu"));
		assertEquals(CountryCode.LU, CountryCode.getByCode(" LU "));
		assertEquals(CountryCode.LU, CountryCode.getByCode("    lu"));
	}

	@Test
	public void getByCodeLenientWithInexactThreeLetterCode() {
		assertEquals(CountryCode.LU, CountryCode.getByCode("LUX"));
		assertEquals(CountryCode.LU, CountryCode.getByCode("Lux"));
		assertEquals(CountryCode.LU, CountryCode.getByCode(" LUX "));
		assertEquals(CountryCode.LU, CountryCode.getByCode("     lux"));
	}

	@Test
	public void comparePrintedNames() {
		for (CountryCode cc : CountryCodeSets.getIban4jCodes(false)) {
			assertThat(cc.name().length(), is(2));
			assertThat(cc.name().trim().toUpperCase(), is(cc.name()));
			// toString() and getAlpha2() are exactly the name()
			assertThat(cc.toString(), equalTo(cc.name()));
			assertThat(cc.getAlpha2(), equalTo(cc.name()));
			// getName() is not the name() but a longer description
			assertThat(cc.getDisplayName(), not(equalTo(cc.name())));
			assertThat(cc.getDisplayName().length() > cc.name().length(), is(true));
			// getAlpha3() is not the name
			assertThat(cc.getAlpha3(), not(equalTo(cc.name())));
			// toStringExtended() is not the name
			assertThat(cc.toStringExtended(), not(equalTo(cc.name())));
		}
	}

	@Test
	public void unitedStatesCountryCodeMustExistWithLiveOnlyTrueOrFalse() {
		assertThat(CountryCode.getByCode("US", true), is(not(nullValue())));
		assertThat(CountryCode.getByCode("US", false), is(not(nullValue())));
	}

	@Test
	public void neutralTerritoriesCountryCodeMustExistOnlyWithLiveOnlyFalse() {
		assertThat(CountryCode.getByCode("NT", true), is(nullValue()));
		assertThat(CountryCode.getByCode("NT", false), is(not(nullValue())));
	}

	@Test
	public void thereMustBeNonEmptySetsOfCountryCodes() {
		assertThat(CountryCodeSets.getIban4jCodes(true).isEmpty(), is(false));
		assertThat(CountryCodeSets.getIban4jCodes(false).isEmpty(), is(false));
	}

	@Test
	public void thereMustBeNonEmptySetsOfCountry2LetterStrings() {
		assertThat(CountryCodeSets.getIban4jTwoLetters(true).isEmpty(), is(false));
		assertThat(CountryCodeSets.getIban4jTwoLetters(false).isEmpty(), is(false));
	}

	@Test
	public void unitedStatesCountryCodeMustBeMemberOfEverySetOfCountryCodes() {
		CountryCode ccLive = CountryCode.getByCode("US", false);
		assertThat(ccLive.name(), equalTo("US"));
		assertThat(ccLive.isTransitional(), is(false));
		assertThat(CountryCodeSets.getIban4jCodes(true), hasItem(ccLive));
		assertThat(CountryCodeSets.getIban4jCodes(false), hasItem(ccLive));
	}

	@Test
	public void unitedStatesCountryCodeMustBeMemberOfEverySetOf2LetterStrings() {
		CountryCode ccLive = CountryCode.getByCode("US", false);
		assertThat(ccLive.name(), equalTo("US"));
		assertThat(ccLive.isTransitional(), is(false));
		assertThat(CountryCodeSets.getIban4jTwoLetters(true), hasItem(ccLive.name()));
		assertThat(CountryCodeSets.getIban4jTwoLetters(false), hasItem(ccLive.name()));
	}

	@Test
	public void neutralTerritoriesCountryCodeMustNotBeMemberOfEverySetOfCountryCodes() {
		CountryCode ccTrans = CountryCode.getByCode("NT", false);
		assertThat(ccTrans.name(), equalTo("NT"));
		assertThat(ccTrans.isTransitional(), is(true));
		assertThat(CountryCodeSets.getIban4jCodes(true), not(hasItem(ccTrans)));
		assertThat(CountryCodeSets.getIban4jCodes(false), hasItem(ccTrans));
	}

	@Test
	public void neutralTerritoriesCountryCodeMustNotBeMemberOfEverySetOf2LetterStrings() {
		CountryCode ccTrans = CountryCode.getByCode("NT", false);
		assertThat(ccTrans.name(), equalTo("NT"));
		assertThat(ccTrans.isTransitional(), is(true));
		assertThat(CountryCodeSets.getIban4jTwoLetters(true), not(hasItem(ccTrans.name())));
		assertThat(CountryCodeSets.getIban4jTwoLetters(false), hasItem(ccTrans.name()));
	}

	@Test
	public void startOfTransitionalPeriodSetIffTransitional() {
		for (CountryCode cc : CountryCode.values()) {
			assertTrue(cc.isTransitional() == (cc.getStartOfTransitionalPeriod()!=null));
		}
	}

	@Test
	public void createYMtwoWays() {
		for (int i=1;i<=12;i++) {
			CountryCode.YM ym1 = new CountryCode.YM("1999-" + i);
			CountryCode.YM ym2 = new CountryCode.YM(1999, i);
			assertTrue(ym1.toString().equals(ym2.toString()));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void createBadYm1() {
		new CountryCode.YM(1999,0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createBadYm2() {
		new CountryCode.YM(1999,13);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createBadYm3() {
		new CountryCode.YM(0,11);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createBadYm4() {
		new CountryCode.YM("jojo");
	}

}