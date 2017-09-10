package org.iban4j;

import java.util.*;

/**
 * A facility class which presents both the JDK-known 2-letter country codes and the Iban4J-known 2-letter country
 * codes as a immutable {@code Set<String>}.
 * <p>
 * Currently this is used by test code, but someone may find it useful for something else.
 */

public class CountryCodeSets {

	/**
	 * 2-letter strings harvested from JDK
	 */

	public static SortedSet<String> jdkTwoLetters;

	/**
	 * 2-letter strings harvested from iban4j (both live and transitional codes)
	 */

	public static SortedSet<String> iban4jTwoLetters;

	/**
	 * 2-letter strings harvested from iban4j (only live, not transitional codes)
	 */

	public static SortedSet<String> iban4jTwoLettersLiveOnly;

	/**
	 * codes harvested from iban4j (both live and transitional codes)
	 */

	public static SortedSet<CountryCode> iban4jCodes;

	/**
	 * codes harvested from iban4j (only live, not transitional codes)
	 */

	public static SortedSet<CountryCode> iban4jCodesLiveOnly;


	static {
		jdkTwoLetters = Collections.unmodifiableSortedSet(getJdkTwoLettersSet());
		iban4jTwoLetters = Collections.unmodifiableSortedSet(getIban4jTwoLettersSet(false));
		iban4jTwoLettersLiveOnly = Collections.unmodifiableSortedSet(getIban4jTwoLettersSet(true));
		iban4jCodes = Collections.unmodifiableSortedSet(getIban4jCodesSet(false));
		iban4jCodesLiveOnly = Collections.unmodifiableSortedSet(getIban4jCodesSet(true));
	}

	private static SortedSet<String> getJdkTwoLettersSet() {
		String[] jdkCcs = Locale.getISOCountries();
		SortedSet<String> jdkSet = new TreeSet<String>();
		for (String it : Arrays.asList(jdkCcs)) {
			assert it.equals(it.toUpperCase()) : "JDK 2-letter ISO 3166 Country Code is not uppercase: '" + it + "'";
			boolean added = jdkSet.add(it);
			assert added : "JDK 2-letter ISO 3166 Country Code encountered twice: '" + it;
		}
		return jdkSet;
	}

	private static SortedSet<String> getIban4jTwoLettersSet(boolean liveOnly) {
		CountryCode[] iban4jCcs = CountryCode.values();
		SortedSet<String> iban4jSet = new TreeSet<String>();
		for (CountryCode it : Arrays.asList(iban4jCcs)) {
			assert it.getAlpha2().equals(it.getAlpha2().toUpperCase()) : "Iban4j 2-letter ISO 3166 Country Code is not uppercase: '" + it.getAlpha2() + "'";
			if (liveOnly && it.isTransitional()) {
				// skip
			} else {
				boolean added = iban4jSet.add(it.getAlpha2());
				assert added : "Iban4j 2-letter ISO 3166 Country Code encountered twice: '" + it.getAlpha2();
			}
		}
		assert iban4jCcs.length >= iban4jSet.size();
		return iban4jSet;
	}

	private static SortedSet<CountryCode> getIban4jCodesSet(boolean liveOnly) {
		CountryCode[] iban4jCcs = CountryCode.values();
		SortedSet<CountryCode> iban4jSet = new TreeSet<CountryCode>();
		iban4jSet.addAll(Arrays.asList(iban4jCcs));
		assert iban4jCcs.length == iban4jSet.size();
		if (liveOnly) {
			// can't wait for Java 8
			Iterator<CountryCode> iter = iban4jSet.iterator();
			while (iter.hasNext()) {
				CountryCode cc = iter.next();
				if (cc.isTransitional()) {
					iter.remove();
				}
			}
		}
		assert iban4jCcs.length >= iban4jSet.size();
		return iban4jSet;
	}

	/**
	 * 2-letter strings harvested from iban4j (an accessor to get the sets more easily by "liveOnly" boolean)
	 */

	public static SortedSet<String> getIban4jTwoLetters(boolean liveOnly) {
		if (liveOnly) {
			return iban4jTwoLettersLiveOnly;
		} else {
			return iban4jTwoLetters;
		}
	}

	/**
	 * codes harvested from iban4j  (an accessor to get the sets more easily by "liveOnly" boolean)
	 */

	public static SortedSet<CountryCode> getIban4jCodes(boolean liveOnly) {
		if (liveOnly) {
			return iban4jCodesLiveOnly;
		} else {
			return iban4jCodes;
		}
	}

}
