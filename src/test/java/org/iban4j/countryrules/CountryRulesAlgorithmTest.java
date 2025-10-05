package org.iban4j.countryrules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.algorithms.BaNationalCheckDigit;
import org.iban4j.countryrules.algorithms.BeNationalCheckDigit;
import org.iban4j.countryrules.algorithms.EsNationalCheckDigit;
import org.iban4j.countryrules.algorithms.FiNationalCheckDigit;
import org.iban4j.countryrules.algorithms.FrNationalCheckDigit;
import org.iban4j.countryrules.algorithms.ItNationalCheckDigit;
import org.iban4j.countryrules.algorithms.MeNationalCheckDigit;
import org.iban4j.countryrules.algorithms.MkNationalCheckDigit;
import org.iban4j.countryrules.algorithms.NlNationalCheckDigit;
import org.iban4j.countryrules.algorithms.NoNationalCheckDigit;
import org.iban4j.countryrules.algorithms.PtNationalCheckDigit;
import org.iban4j.countryrules.algorithms.RsNationalCheckDigit;
import org.iban4j.countryrules.algorithms.SiNationalCheckDigit;
import org.iban4j.countryrules.algorithms.SkNationalCheckDigit;
import org.iban4j.countryrules.algorithms.TnNationalCheckDigit;
import org.junit.jupiter.api.Test;

public class CountryRulesAlgorithmTest {

    @Test
    public void belgiumAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new BeNationalCheckDigit();
        assertEquals(CountryCode.BE, algorithm.getCountry());
        
        // Valid Belgian IBAN
        Iban validIban = Iban.valueOf("BE68539007547034");
        assertTrue(algorithm.validate(validIban));
        
        // Invalid Belgian IBAN (wrong check digits)
        Iban invalidIban = Iban.valueOf("BE19539007557034");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void bosniaHerzegovinaAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new BaNationalCheckDigit();
        assertEquals(CountryCode.BA, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("BA391290079401028494");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("BA551290079401028594");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void spainAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new EsNationalCheckDigit();
        assertEquals(CountryCode.ES, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("ES9121000418450200051332");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("ES4221000418450200061332");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void finlandAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new FiNationalCheckDigit();
        assertEquals(CountryCode.FI, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("FI2112345600000785");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("FI9112345600000786");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void franceAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new FrNationalCheckDigit();
        assertEquals(CountryCode.FR, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("FR1420041010050500013M02606");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("FR6020041010050500013M52606");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void italyAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new ItNationalCheckDigit();
        assertEquals(CountryCode.IT, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("IT60X0542811101000000123456");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("IT33X0542811101000000123457");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void macedoniaAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new MkNationalCheckDigit();
        assertEquals(CountryCode.MK, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("MK07250120000058984");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("MK40250120000058584");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void montenegroAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new MeNationalCheckDigit();
        assertEquals(CountryCode.ME, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("ME25505000012345678951");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("ME58505000012345678551");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void netherlandsAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new NlNationalCheckDigit();
        assertEquals(CountryCode.NL, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("NL91ABNA0417164300");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("NL26ABNA0417164500");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void norwayAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new NoNationalCheckDigit();
        assertEquals(CountryCode.NO, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("NO9386011117947");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("NO5086011117945");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void portugalAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new PtNationalCheckDigit();
        assertEquals(CountryCode.PT, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("PT50000201231234567890154");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("PT52000201231234567850154");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void serbiaAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new RsNationalCheckDigit();
        assertEquals(CountryCode.RS, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("RS35260005601001611379");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("RS67260005601001611579");
        assertFalse(algorithm.validate(invalidIban));
    }


    @Test
    public void slovakRepublicAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new SkNationalCheckDigit();
        assertEquals(CountryCode.SK, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("SK3112000000198742637541");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("SK4712000000198742637641");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void sloveniaAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new SiNationalCheckDigit();
        assertEquals(CountryCode.SI, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("SI56191000000123438");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("SI72191000000123538");
        assertFalse(algorithm.validate(invalidIban));
    }

    @Test
    public void tunisiaAlgorithmTest() {
        CountryRulesAlgorithm algorithm = new TnNationalCheckDigit();
        assertEquals(CountryCode.TN, algorithm.getCountry());
        
        Iban validIban = Iban.valueOf("TN5910006035183598478831");
        assertTrue(algorithm.validate(validIban));
        
        Iban invalidIban = Iban.valueOf("TN1110006035183598478531");
        assertFalse(algorithm.validate(invalidIban));
    }
}
