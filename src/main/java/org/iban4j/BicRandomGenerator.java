package org.iban4j;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generate random BICs to obtain test data.
 */

public class BicRandomGenerator {

	private final static Random rand = new Random();

	private final static String locationPos1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ__23456789".replace("_", "");
	private final static String locationPos2 = "ABCDEFGHIJKLMN_PQRSTUVWXYZ0123456789".replace("_", "");
	private final static String branchCodeTemplatePatternAsString = "^([A-Z0-9]{3}|_|)$";
	private final static String institutionCodeTemplatePatternAsString = "^([A-Z]{4}|_)$";

	private final static Pattern branchCodeTemplatePattern = Pattern.compile(branchCodeTemplatePatternAsString);
	private final static Pattern institutionCodeTemplatePattern = Pattern.compile(institutionCodeTemplatePatternAsString);

	/**
	 * A helper to generate random values in the interval [lowInclusive, highInclusive], where lowInclusive is {@literal >= 0},
	 * using a provided {@code java.util.Random instance.} This method also exists in <a href="https://commons.apache.org/proper/commons-lang/javadocs/api-3.6/org/apache/commons/lang3/RandomUtils.html">org.apache.commons.lang3.RandomUtils</a>
	 *
	 * @param rand          an already-existing random-number generator
	 * @param lowInclusive  lower inclusive bound, must be {@literal >= 0}.
	 * @param highInclusive upper inclusive bound, must be {@literal >= lowInclusive} and {@literal < Integer.MAX_VALUE}.
	 * @return a random integer in interval [lowInclusive, highInclusive]
	 */
	public static int randomInInterval(Random rand, int lowInclusive, int highInclusive) {
		checkRand(rand);
		if (lowInclusive < 0) {
			throw new IllegalArgumentException("The passed 'lowInclusive' is negative: " + lowInclusive);
		}
		if (lowInclusive > highInclusive) {
			throw new IllegalArgumentException("The passed 'lowInclusive' is larger than 'highInclusive': " + lowInclusive + " > " + highInclusive);
		}
		if (lowInclusive == highInclusive) {
			return lowInclusive;
		} else {
			int newHighInclusive = highInclusive - lowInclusive;
			if (newHighInclusive == Integer.MAX_VALUE) {
				throw new IllegalArgumentException("The passed 'highInclusive' is 'largest integer', which is too large by 1");
			}
			return rand.nextInt(newHighInclusive + 1) + lowInclusive;
		}
	}

	/**
	 * This is a trick to confuse the compiler enough so that warnings "always returns true" go away
	 */

	private static boolean giveMeTrue() {
		int a = rand.nextInt();
		return (a & ~a) == 0;
	}

	/**
	 * Helper to check passed argument. Throws on bad argument. Always returns true, so that it can be
	 * put into the body of an assert and thus "compiled out"
	 */

	private static boolean checkRand(Random rand) {
		if (rand == null) {
			throw new IllegalArgumentException("The passed java.util.Random instance is null");
		}
		return giveMeTrue();
	}

	/**
	 * Helper to check passed argument. Throws on bad argument. Always returns true, so that it can be
	 * put into the body of an assert and thus "compiled out"
	 */

	private static boolean checkTemplate(String[] template) {
		if (template == null) {
			throw new IllegalArgumentException("The passed template array is null");
		}
		if (template.length == 0) {
			throw new IllegalArgumentException("The passed template array is empty");
		}
		return true;
	}

	/**
	 * Get an uppercase alphanumeric character
	 */

	private static char nextUcAlphaNum(Random rand) {
		assert checkRand(rand);
		String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return template.charAt(rand.nextInt(template.length()));
	}

	/**
	 * Get an uppercase character
	 */

	private static char nextUcAlpha(Random rand) {
		assert checkRand(rand);
		String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return template.charAt(rand.nextInt(template.length()));
	}

	/**
	 * Generate a random 3-letter BIC "branch" string. The string is selected uniformly at random from the "template array" of strings
	 * passed. All the strings in the passed array must be uppercase 3-letter-alphanumeric or else be the empty string
	 * (which means the "primary office") or "_" (1 underscore). If the string "_" is selected from the template string array,
	 * a completely random branch string will be generated and returned. If a string is present several times in the
	 * template array, it will be returned more often in proportion.
	 *
	 * @param rand a non-null random number generator
	 * @param template An array of possible office strings to select from.
	 * @return A random branch string selected from the template array.
	 */

	public static String genRandomBranchCode(Random rand, String[] template) {
		assert checkRand(rand);
		assert checkTemplate(template);
		String selected = template[randomInInterval(rand, 0, template.length - 1)];
		Matcher m = branchCodeTemplatePattern.matcher(selected);
		if (!m.matches()) {
			throw new IllegalArgumentException("The template string '" + selected + "' does not match '" + branchCodeTemplatePatternAsString + "'");
		}
		if (selected.equals("_")) {
			return "" + nextUcAlphaNum(rand) + nextUcAlphaNum(rand) + nextUcAlphaNum(rand);
		} else {
			return selected;
		}
	}

	/**
	 * Generate a random "location", which is a 2-character uppercase string with the following characteristics:
	 * <ul>
	 * <li>First characater is any of A-Z and 2-9 </li>
	 * <li>Second character is any of A-Z except O and and of 0-9</li>
	 * </ul>
	 * The second character may have a special significance.<br>
	 * From <a href="https://en.wikipedia.org/wiki/ISO_9362">ISO 9362</a> at Wikipedia:
	 * <ul>
	 * <li>if the second character is "0", then it is typically a test BIC as opposed to a BIC used on the live network.</li>
	 * <li>if the second character is "1", then it denotes a passive participant in the SWIFT network</li>
	 * <li>if the second character is "2", then it typically indicates a reverse billing BIC where the recipient pays for the message as opposed to the more usual mode whereby the sender pays for the message.</li>
	 * </ul>
	 * So it can be forced by passing a non-null Character.
	 *
	 * @param rand a non-null random number generator
	 * @param forcedSecondCharacter if non-null, the second character is set to this
	 * @return a String of length 2 representing a BIC "location"
	 */

	public static String genRandomLocationCode(Random rand, Character forcedSecondCharacter) {
		if (forcedSecondCharacter != null && locationPos2.indexOf(forcedSecondCharacter) < 0) {
			throw new IllegalArgumentException("The 'forced second character' is '" + forcedSecondCharacter + "', which is not allowed!");
		}
		char ch1 = locationPos1.charAt(rand.nextInt(locationPos1.length()));
		char ch2;
		if (forcedSecondCharacter != null) {
			ch2 = forcedSecondCharacter;
		} else {
			ch2 = locationPos2.charAt(rand.nextInt(locationPos2.length()));
		}
		return "" + ch1 + ch2;
	}

	/**
	 * Generate a random 4-letter BIC "bank code" string. The string is selected uniformly from the template array of strings
	 * passed. All the strings in the passed array must be uppercase 4-letter-alpha or else be "_" (1 underscore).
	 * The result is selected uniformly at random form the template array. If the string "_" is selected,
	 * a completely random "bank code" string will be generated and returned. If a string is present several times in the
	 * array, it will be returned more often in proportion.
	 *
	 * @param rand a non-null random number generator
	 * @param template An array of possible "bank code" strings to select from.
	 * @return A random bank string selected from the template array.
	 */

	public static String genRandomInstitutionCode(Random rand, String[] template) {
		assert checkRand(rand);
		assert checkTemplate(template);
		String selected = template[randomInInterval(rand, 0, template.length - 1)];
		Matcher m = institutionCodeTemplatePattern.matcher(selected);
		if (!m.matches()) {
			throw new IllegalArgumentException("The template string '" + selected + "' does not match '" + institutionCodeTemplatePatternAsString + "'");
		}
		if (selected.equals("_")) {
			return "" + nextUcAlpha(rand) + nextUcAlpha(rand) + nextUcAlpha(rand) + nextUcAlpha(rand);
		} else {
			return selected;
		}
	}

	/**
	 * Generate a random CountryCode.
	 *
	 * @param rand a non-null random number generator
	 * @return a random {@code CountryCode} instance
	 */

	public static CountryCode genRandomCountryCode(Random rand, boolean liveOnly) {
		assert checkRand(rand);
		CountryCode[] all = CountryCode.values();
		CountryCode cc;
		do {
			cc = all[rand.nextInt(all.length)];
		} while (liveOnly && cc.isTransitional());
		return cc;
	}

	/**
	 * Generate a random BIC. The values passed may be set. If any value is null, random values are picked instead.
	 *
	 * @param rand a non-null random number generator
	 * @param institution a pre-formed institution string to use, or null
	 * @param cc a pre-formed {@code CountryCode} instance to use, or null
	 * @param location a pre-formed location string to use, or null
	 * @param branch a pre-formed branch string to use, or null
	 * @return a random {@code Bic} instance using the values passed in
	 */

	public static Bic genRandomBic(Random rand, String institution, CountryCode cc, String location, String branch) {
		assert checkRand(rand);
		// Do not trim/uppercase passed values. should we?
		String institutionHere = (institution != null ? institution : genRandomInstitutionCode(rand, new String[]{"_"}));
		String ccHere = (cc != null ? cc : genRandomCountryCode(rand, true)).name();
		String locationHere = (location != null ? location : genRandomLocationCode(rand, null));
		String branchHere = (branch != null ? branch : genRandomBranchCode(rand, new String[]{"_"}));
		if (institutionHere.length() != 4) {
			throw new IllegalArgumentException("Institution code has disallowed length: '" + institutionHere + "'");
		}
		if (locationHere.length() != 2) {
			throw new IllegalArgumentException("Location code has disallowed length: '" + locationHere + "'");
		}
		if (!(branchHere.length() == 3 || branchHere.isEmpty())) {
			throw new IllegalArgumentException("Branch code has disallowed length: '" + branchHere + "'");
		}
		return new Bic(institutionHere + ccHere + locationHere + branchHere);
	}

	/**
	 * Generate a random BIC, all values chosen internally
	 *
	 * @return a completely random {@code Bic} instance
	 */

	public static Bic genRandomBic(Random rand) {
		return genRandomBic(rand, null, null, null, null);
	}

}
