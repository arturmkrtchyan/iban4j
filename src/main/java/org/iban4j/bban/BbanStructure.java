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
package org.iban4j.bban;

import org.iban4j.CountryCode;
import java.util.EnumMap;


/**
 * Class which represents bban structure
 */
public class BbanStructure {

    private CountryCode countryCode;
    private BbanStructureEntry[] entries;

    private BbanStructure(final CountryCode countryCode, final BbanStructureEntry... entries) {
        this.countryCode = countryCode;
        this.entries = entries;
    }


    private static EnumMap<CountryCode, BbanStructure> structures;
    static {
        structures = new EnumMap<CountryCode, BbanStructure>(CountryCode.class);

        structures.put(CountryCode.AL,
                new BbanStructure(CountryCode.AL,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.AD,
                new BbanStructure(CountryCode.AD,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.AT,
                new BbanStructure(CountryCode.AT,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n')));


        structures.put(CountryCode.AZ,
                new BbanStructure(CountryCode.AZ,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(20, 'c')));

        structures.put(CountryCode.BH,
                new BbanStructure(CountryCode.BH,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(14, 'n')));

        structures.put(CountryCode.BE,
                new BbanStructure(CountryCode.BE,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(7, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.BA,
                new BbanStructure(CountryCode.BA,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(3, 'n'),
                        BbanStructureEntry.accountNumber(8, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.BR,
                new BbanStructure(CountryCode.BR,
                        BbanStructureEntry.bankCode(8, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(10, 'n'),
                        BbanStructureEntry.accountType(1, 'a'),
                        BbanStructureEntry.ownerAccountNumber(1, 'c')));

        structures.put(CountryCode.BG,
                new BbanStructure(CountryCode.BG,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.accountType(2, 'n'),
                        BbanStructureEntry.accountNumber(8, 'c')));

        structures.put(CountryCode.CR,
                new BbanStructure(CountryCode.CR,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(14, 'n')));

        structures.put(CountryCode.DE,
                new BbanStructure(CountryCode.DE,
                        BbanStructureEntry.bankCode(8, 'n'),
                        BbanStructureEntry.accountNumber(10, 'c')));

        structures.put(CountryCode.HR,
                new BbanStructure(CountryCode.HR,
                        BbanStructureEntry.bankCode(7, 'n'),
                        BbanStructureEntry.accountNumber(10, 'n')));

        structures.put(CountryCode.CY,
                new BbanStructure(CountryCode.CY,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.CZ,
                new BbanStructure(CountryCode.CZ,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.accountNumber(16, 'n')));

        structures.put(CountryCode.DK,
                new BbanStructure(CountryCode.DK,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.accountNumber(10, 'n')));

        structures.put(CountryCode.DO,
                new BbanStructure(CountryCode.DO,
                        BbanStructureEntry.bankCode(4, 'c'),
                        BbanStructureEntry.accountNumber(20, 'n')));

        structures.put(CountryCode.EE,
                new BbanStructure(CountryCode.EE,
                        BbanStructureEntry.bankCode(2, 'n'),
                        BbanStructureEntry.branchCode(2, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n')));

        structures.put(CountryCode.FI,
                new BbanStructure(CountryCode.FI,
                        BbanStructureEntry.bankCode(6, 'n'),
                        BbanStructureEntry.accountNumber(7, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n')));

        structures.put(CountryCode.FR,
                new BbanStructure(CountryCode.FR,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'c'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.GE,
                new BbanStructure(CountryCode.GE,
                        BbanStructureEntry.bankCode(2, 'a'),
                        BbanStructureEntry.accountNumber(16, 'n')));

        structures.put(CountryCode.GI,
                new BbanStructure(CountryCode.GI,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(15, 'c')));

        structures.put(CountryCode.GR,
                new BbanStructure(CountryCode.GR,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.GT,
                new BbanStructure(CountryCode.GT,
                        BbanStructureEntry.bankCode(4, 'c'),
                        BbanStructureEntry.accountNumber(20, 'c')));

        structures.put(CountryCode.HU,
                new BbanStructure(CountryCode.HU,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.accountNumber(16, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n')));

        structures.put(CountryCode.IS,
                new BbanStructure(CountryCode.IS,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.branchCode(2, 'n'),
                        BbanStructureEntry.accountNumber(6, 'n'),
                        BbanStructureEntry.identificationNumber(10, 'n')));

        structures.put(CountryCode.IE,
                new BbanStructure(CountryCode.IE,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.branchCode(6, 'n'),
                        BbanStructureEntry.accountNumber(8, 'n')));

        structures.put(CountryCode.IL,
                new BbanStructure(CountryCode.IL,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(3, 'n'),
                        BbanStructureEntry.accountNumber(13, 'n')));

        structures.put(CountryCode.IT,
                new BbanStructure(CountryCode.IT,
                        BbanStructureEntry.nationalCheckDigit(1, 'a'),
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.IT,
                new BbanStructure(CountryCode.IT,
                        BbanStructureEntry.nationalCheckDigit(1, 'a'),
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.KZ,
                new BbanStructure(CountryCode.KZ,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(13, 'c')));

        structures.put(CountryCode.KW,
                new BbanStructure(CountryCode.KW,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(22, 'c')));

        structures.put(CountryCode.LV,
                new BbanStructure(CountryCode.LV,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(13, 'c')));

        structures.put(CountryCode.LB,
                new BbanStructure(CountryCode.LB,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.accountNumber(20, 'c')));

        structures.put(CountryCode.LI,
                new BbanStructure(CountryCode.LI,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.LT,
                new BbanStructure(CountryCode.LT,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n')));

        structures.put(CountryCode.LU,
                new BbanStructure(CountryCode.LU,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(13, 'c')));

        structures.put(CountryCode.MK,
                new BbanStructure(CountryCode.MK,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(10, 'c'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.MT,
                new BbanStructure(CountryCode.MT,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(18, 'c')));

        structures.put(CountryCode.MR,
                new BbanStructure(CountryCode.MR,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.MU,
                new BbanStructure(CountryCode.MU,
                        BbanStructureEntry.bankCode(6, 'c'),
                        BbanStructureEntry.branchCode(2, 'n'),
                        BbanStructureEntry.accountNumber(18, 'c')));

        structures.put(CountryCode.MD,
                new BbanStructure(CountryCode.MD,
                        BbanStructureEntry.bankCode(2, 'c'),
                        BbanStructureEntry.accountNumber(18, 'c')));

        structures.put(CountryCode.MC,
                new BbanStructure(CountryCode.MC,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'c'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.ME,
                new BbanStructure(CountryCode.ME,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(13, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.NL,
                new BbanStructure(CountryCode.NL,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(10, 'n')));

        structures.put(CountryCode.NO,
                new BbanStructure(CountryCode.NO,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.accountNumber(6, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n')));

        structures.put(CountryCode.PK,
                new BbanStructure(CountryCode.PK,
                        BbanStructureEntry.bankCode(4, 'c'),
                        BbanStructureEntry.accountNumber(16, 'n')));

        structures.put(CountryCode.PS,
                new BbanStructure(CountryCode.PS,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(21, 'c')));

        structures.put(CountryCode.PL,
                new BbanStructure(CountryCode.PL,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'n'),
                        BbanStructureEntry.accountNumber(16, 'n')));

        structures.put(CountryCode.PT,
                new BbanStructure(CountryCode.PT,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.RO,
                new BbanStructure(CountryCode.RO,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.QA,
                new BbanStructure(CountryCode.QA,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(21, 'c')));

        structures.put(CountryCode.SM,
                new BbanStructure(CountryCode.SM,
                        BbanStructureEntry.nationalCheckDigit(1, 'a'),
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.branchCode(5, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.SA,
                new BbanStructure(CountryCode.SA,
                        BbanStructureEntry.bankCode(2, 'n'),
                        BbanStructureEntry.accountNumber(18, 'c')));

        structures.put(CountryCode.RS,
                new BbanStructure(CountryCode.RS,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(13, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.SK,
                new BbanStructure(CountryCode.SK,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.accountNumber(16, 'n')));

        structures.put(CountryCode.SI,
                new BbanStructure(CountryCode.SI,
                        BbanStructureEntry.bankCode(2, 'n'),
                        BbanStructureEntry.branchCode(3, 'n'),
                        BbanStructureEntry.accountNumber(8, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n')));

        structures.put(CountryCode.ES,
                new BbanStructure(CountryCode.ES,
                        BbanStructureEntry.bankCode(4, 'n'),
                        BbanStructureEntry.branchCode(4, 'n'),
                        BbanStructureEntry.nationalCheckDigit(2, 'n'),
                        BbanStructureEntry.accountNumber(10, 'n')));

        structures.put(CountryCode.SE,
                new BbanStructure(CountryCode.SE,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(17, 'n')));

        structures.put(CountryCode.CH,
                new BbanStructure(CountryCode.CH,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.accountNumber(12, 'c')));

        structures.put(CountryCode.TN,
                new BbanStructure(CountryCode.TN,
                        BbanStructureEntry.bankCode(2, 'n'),
                        BbanStructureEntry.branchCode(3, 'n'),
                        BbanStructureEntry.accountNumber(15, 'c')));

        structures.put(CountryCode.TR,
                new BbanStructure(CountryCode.TR,
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.nationalCheckDigit(1, 'c'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.GB,
                new BbanStructure(CountryCode.GB,
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.branchCode(6, 'n'),
                        BbanStructureEntry.accountNumber(8, 'n')));

        structures.put(CountryCode.AE,
                new BbanStructure(CountryCode.AE,
                        BbanStructureEntry.bankCode(3, 'n'),
                        BbanStructureEntry.accountNumber(16, 'c')));

        structures.put(CountryCode.VG,
                new BbanStructure(CountryCode.VG,
                        BbanStructureEntry.bankCode(4, 'c'),
                        BbanStructureEntry.accountNumber(16, 'n')));
    }

    public static BbanStructure forCountry(CountryCode countryCode) {
        return structures.get(countryCode);
    }

    public BbanStructureEntry[] getEntries() {
        return entries;
    }

    /**
     * Returns the length of bban.
     *
     * @return int length
     */
    public int getBbanLength() {
        int length = 0;

        for(BbanStructureEntry entry : entries) {
            length += entry.getLength();
        }

        return length;
    }

    @Override
    public String toString() {
        return "BbanStructure{" +
                "rawStructure='" + "TODO" +
                '}';
    }
}
