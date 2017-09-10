package org.iban4j;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class BicGeneratorTest {

	private Random rand = new Random(10000);

	@Test
	public void generateSome1() {
		int limit = 10000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < limit; i++) {
			String branch = BicRandomGenerator.genRandomBranchCode(rand, new String[]{"_", "_", "_", "_", ""});
			CountryCode cc = BicRandomGenerator.genRandomCountryCode(rand, true);
			String loc = BicRandomGenerator.genRandomLocationCode(rand, null);
			String inst = BicRandomGenerator.genRandomInstitutionCode(rand, new String[]{"_"});
			Bic res = BicRandomGenerator.genRandomBic(rand, inst, cc, loc, branch);
			// System.out.println(res);
		}
		long end = System.currentTimeMillis();
		System.out.println("Generated " + limit + " random BICs in " + (end-start) + " ms");
	}

	@Test
	public void generateSome2() {
		int limit = 10000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < limit; i++) {
			Bic res = BicRandomGenerator.genRandomBic(rand);
			// System.out.println(res);
			assertTrue(Bic.passesRegex(res.toString()));
		}
		long end = System.currentTimeMillis();
		System.out.println("Generated " + limit + " random BICs in " + (end-start) + " ms");
	}

	@Test
	public void generateSome3() {
		int limit = 10000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < limit; i++) {
			Bic res = BicRandomGenerator.genRandomBic(rand,"MUMU",CountryCode.LU,null,"XXX");
			// System.out.println(res);
			assertTrue(Bic.passesRegex(res.toString()));
		}
		long end = System.currentTimeMillis();
		System.out.println("Generated " + limit + " random BICs in " + (end-start) + " ms");
	}

	@Test
	public void generateSome4() {
		int limit = 10000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < limit; i++) {
			Bic res = BicRandomGenerator.genRandomBic(rand,"MUMU",CountryCode.LU,"MI","");
			// System.out.println(res);
			assertTrue(Bic.passesRegex(res.toString()));
		}
		long end = System.currentTimeMillis();
		System.out.println("Generated " + limit + " random BICs in " + (end-start) + " ms");
	}

	public void passesRegex1() {
		assertTrue(Bic.passesRegex("DEUTDEFF500"));
		assertTrue(Bic.passesRegex("DEUTDEFF"));
		assertTrue(Bic.passesRegex("DEUTDEFFXXX"));
		assertFalse(Bic.passesRegex(" DEUTDEFF500 "));
		assertFalse(Bic.passesRegex(""));
		assertFalse(Bic.passesRegex("XXX"));
	}
}
