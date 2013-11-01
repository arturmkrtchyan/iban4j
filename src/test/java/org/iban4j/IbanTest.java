package org.iban4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IbanTest {

    private CountryCode countryCode;
    private Bban bban;
    private String expectedIbanString;

    public IbanTest(CountryCode countryCode, Bban bban, String expectedIbanString) {
        this.countryCode = countryCode;
        this.bban = bban;
        this.expectedIbanString = expectedIbanString;
    }

    @Test
    public void ibanConstructionWithSupportedCountriesShouldReturnIban() {
        Iban iban = new Iban(countryCode, bban);
        assertThat(iban.toString(), is(equalTo(expectedIbanString)));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> ibanParameters() {
        return Arrays.asList(new Object[][]{
                {CountryCode.AL, new Bban.Builder()
                        .bankCode("212")
                        .branchCode("1100")
                        .accountNumber("0000000235698741")
                        .nationalCheckDigit("9")
                        .build(), "AL47212110090000000235698741"},
                {CountryCode.AD, new Bban.Builder()
                        .bankCode("0001")
                        .branchCode("2030")
                        .accountNumber("200359100100")
                        .build(), "AD1200012030200359100100"},
                {CountryCode.AT, new Bban.Builder()
                        .bankCode("19043")
                        .accountNumber("00234573201")
                        .build(), "AT611904300234573201"},
                {CountryCode.AZ, new Bban.Builder()
                        .bankCode("NABZ")
                        .accountNumber("00000000137010001944")
                        .build(), "AZ21NABZ00000000137010001944"},
                {CountryCode.BH, new Bban.Builder()
                        .bankCode("BMAG")
                        .accountNumber("00001299123456")
                        .build(), "BH67BMAG00001299123456"},
                {CountryCode.BE, new Bban.Builder()
                        .bankCode("539")
                        .accountNumber("0075470")
                        .nationalCheckDigit("34")
                        .build(), "BE68539007547034"},
                {CountryCode.BA, new Bban.Builder()
                        .bankCode("129")
                        .branchCode("007")
                        .accountNumber("94010284")
                        .nationalCheckDigit("94")
                        .build(), "BA391290079401028494"},
                {CountryCode.BR, new Bban.Builder()
                        .bankCode("00360305")
                        .branchCode("00001")
                        .accountNumber("0009795493")
                        .accountType("P")
                        .ownerAccountType("1")
                        .build(), "BR9700360305000010009795493P1"},
                {CountryCode.BG, new Bban.Builder()
                        .bankCode("BNBG")
                        .branchCode("9661")
                        .accountNumber("20345678")
                        .accountType("10")
                        .build(), "BG80BNBG96611020345678"},
                {CountryCode.CR, new Bban.Builder()
                        .bankCode("152")
                        .accountNumber("02001026284066")
                        .build(), "CR0515202001026284066"},
                {CountryCode.HR, new Bban.Builder()
                        .bankCode("1001005")
                        .accountNumber("1863000160")
                        .build(), "HR1210010051863000160"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
        });
    }
}
