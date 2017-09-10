package org.iban4j;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Exercising the code computing a "next integer" included in the BicRandomGenerator class.
 * <p>
 * Maybe one should simple import
 * <a href="https://commons.apache.org/proper/commons-lang/javadocs/api-3.6/org/apache/commons/lang3/RandomUtils.html">org.apache.commons.lang3.RandomUtils</a>
 * and be done with it.
 */

public class NextIntTest {

	private Random rand = new Random(10000);
	private final int limit = 100;

	@Test
	public void runNextInt() {
		int lowInclusive = 0;
		int highInclusive = 100;
		for (int i = 0; i < limit; i++) {
			int x = BicRandomGenerator.randomInInterval(rand, lowInclusive, highInclusive);
			assertTrue(lowInclusive <= x && x <= highInclusive);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArg1() {
		BicRandomGenerator.randomInInterval(rand, -1, 100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArg2() {
		BicRandomGenerator.randomInInterval(rand, 0, Integer.MAX_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArg3() {
		BicRandomGenerator.randomInInterval(rand, 1, 0);
	}

	@Test
	public void runNextIntOneInterval() {
		for (int i = 0; i < limit; i++) {
			int x = BicRandomGenerator.randomInInterval(rand, 100, 100);
			assertTrue(x == 100);
		}
	}

	@Test
	public void runNextIntTwoInterval() {
		for (int i = 0; i < limit; i++) {
			int x = BicRandomGenerator.randomInInterval(rand, 100, 101);
			assertTrue(x == 100 || x == 101);
		}
	}

	@Test
	public void runNextIntThreeInterval() {
		boolean got100 = false;
		boolean got101 = false;
		boolean got102 = false;
		for (int i = 0; i < limit; i++) {
			int x = BicRandomGenerator.randomInInterval(rand, 100, 102);
			assertTrue(x == 100 || x == 101 || x == 102);
			switch (x) {
				case 100:
					got100 = true;
					break;
				case 101:
					got101 = true;
					break;
				case 102:
					got102 = true;
					break;
				default:
					assert false;
			}
		}
		assertTrue(got100 && got101 && got102);
	}

}
