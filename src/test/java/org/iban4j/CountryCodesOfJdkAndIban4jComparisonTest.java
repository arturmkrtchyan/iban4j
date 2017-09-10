package org.iban4j;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Here we are comparing the Country Codes known to the JRE to the ContryCode enum that comes with this package
 * <p>
 * See also <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html">java.util.Locale</a>
 */

public class CountryCodesOfJdkAndIban4jComparisonTest {

	private static String join(Set<String> s) {
		// well, we can't use String.join() yet (since 1.8)
		boolean first = true;
		StringBuilder buf = new StringBuilder();
		for (String it : s) {
			if (!first) {
				buf.append(",");
			}
			buf.append(it);
			first = false;
		}
		return buf.toString();
	}

	/**
	 * It is actually ok if Iban4J knows more country codes than the JDK
	 * (currently: XK = (XK,UNK,Kosovo))
	 */

	@Ignore
	@Test
	public void verifyJdkHasAllCountryCodesOfIban4j() {
		Set<String> iban4jSet = CountryCodeSets.iban4jTwoLetters;
		Set<String> jdkSet = CountryCodeSets.jdkTwoLetters;
		SortedSet<String> iban4jOnly = new TreeSet<String>(iban4jSet);
		iban4jOnly.removeAll(jdkSet);
		if (!iban4jOnly.isEmpty()) {
			System.out.println("Iban4j knows about " + iban4jOnly.size() + " country codes more than the JDK: " + join(iban4jOnly));
			for (String it : iban4jOnly) {
				CountryCode cc = CountryCode.valueOf(it);
				System.out.println(it + " = " + cc.toStringExtended());
			}
		}
		assertTrue("Iban4j knows more country codes than the JDK", iban4jOnly.isEmpty());
	}


	@Test
	public void verifyIban4jHasAllCountryCodesOfJdk() {
		Set<String> iban4jSet = CountryCodeSets.iban4jTwoLetters;
		Set<String> jdkSet = CountryCodeSets.jdkTwoLetters;
		SortedSet<String> jdkOnly = new TreeSet<String>(jdkSet);
		jdkOnly.removeAll(iban4jSet);
		if (!jdkOnly.isEmpty()) {
			System.out.println("JDK " + System.getProperty("java.version") + " knows about " + jdkOnly.size() + " country codes more than Iban4j: " + join(jdkOnly));
			for (String it : jdkOnly) {
				Locale loc = new Locale("en", it);
				System.out.println(it + " = '" + loc.getDisplayCountry() + "'");
			}
		}
		assertTrue("JDK knows more country codes than Iban4j", jdkOnly.isEmpty());
	}
}