package org.iban4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Run various tests on the Bic class.
 *
 * <i>Note DATO 2017-09-09: This used to be an inner class of {@code BicTest}, which was confusing. So I pulled it "up".</i>
 */

public class BicTest {

	@Test(expected = UnsupportedCountryException.class)
	public void bicConstructionWithInvalidCountryCodeShouldThrowException() {
		new Bic("DEUTAAFF500");
	}

	@Test
	public void bicsWithSameDataShouldBeEqual() {
		Bic bic1 = new Bic("DEUTDEFF500");
		Bic bic2 = new Bic("DEUTDEFF500");

		assertThat(bic1, is(equalTo(bic2)));
	}

	@Test
	public void bicsWithDifferentDataShouldNotBeEqual() {
		Bic bic1 = new Bic("DEUTDEFF500");
		Bic bic2 = new Bic("DEUTDEFF501");

		assertThat(bic1, is(not(equalTo(bic2))));
	}

	@Test
	public void bicsWithStringValueAndBicShouldNotBeEqual() {
		Bic bic = new Bic("DEUTDEFF500");

		assertNotEquals(bic, "DEUTDEFF500");
	}

	@Test
	public void bicsWithSameDataShouldHaveSameHashCode() {
		Bic bic1 = new Bic("DEUTDEFF500");
		Bic bic2 = new Bic("DEUTDEFF500");

		assertThat(bic1.hashCode(), is(equalTo(bic2.hashCode())));
	}

	@Test
	public void bicsWithDifferentDataShouldNotHaveSameHashCode() {
		Bic bic1 = new Bic("DEUTDEFF500");
		Bic bic2 = new Bic("DEUTDEFF501");

		assertThat(bic1.hashCode(), is(not(equalTo(bic2.hashCode()))));
	}

	@Test
	public void bicShouldReturnBankCode() {
		Bic bic = new Bic("DEUTDEFF500");

		assertThat(bic.getInstitutionCode(), is(equalTo("DEUT")));
	}

	@Test
	public void bicShouldReturnCountryCode() {
		Bic bic = new Bic("DEUTDEFF500");

		assertThat(bic.getCountryCode(), is(equalTo(CountryCode.DE)));
	}

	@Test
	public void bicShouldReturnBranchCode() {
		Bic bic = new Bic("DEUTDEFF500");

		assertThat(bic.getBranchCode(), is(equalTo("500")));
	}

	@Test
	public void bicWithoutBranchCodeShouldReturnNull() {
		Bic bic = new Bic("DEUTDEFF");

		assertThat(bic.getBranchCode(), is(equalTo(null)));
	}

	@Test
	public void bicShouldReturnLocationCode() {
		Bic bic = new Bic("DEUTDEFF500");

		assertThat(bic.getLocationCode(), is(equalTo("FF")));
	}

	@Test
	public void bicToStringShouldReturnString() {
		Bic bic = new Bic("DEUTDEFF500");

		assertThat(bic.toString(), is(equalTo("DEUTDEFF500")));
	}

	@Test
	public void passesRegex1() {
		assertTrue(Bic.passesRegex("DEUTDEFF500"));
		assertTrue(Bic.passesRegex("DEUTDEFF"));
		assertTrue(Bic.passesRegex("DEUTDEFFXXX"));
		assertFalse(Bic.passesRegex(" DEUTDEFF500 "));
		assertFalse(Bic.passesRegex(""));
		assertFalse(Bic.passesRegex("XXX"));
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic1() {
		new Bic("deutDEFFXXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic2() {
		new Bic("DEUTdeFFXXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic3() {
		new Bic("DEUTDEffXXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic4() {
		new Bic("DEUTDEFFxxx");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic5() {
		new Bic("???DEFFXXX");
	}

	@Test(expected = UnsupportedCountryException.class)
	public void buildBadBic6() {
		new Bic("DEUT??FFXXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic7() {
		new Bic("DEUTDE??XXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic8() {
		new Bic("DEUTDEFF???");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic9Subtle() {
		new Bic("DEUTDE09XXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic10Subtle() {
		new Bic("DEUTDE19XXX");
	}

	@Test(expected = BicFormatException.class)
	public void buildBadBic11Subtle() {
		new Bic("DEUTDE2OXXX");
	}

}