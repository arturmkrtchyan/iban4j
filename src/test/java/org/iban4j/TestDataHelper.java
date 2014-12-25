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

import java.util.Arrays;
import java.util.Collection;

final class TestDataHelper {

    private TestDataHelper() {
    }

    public static Collection<Object[]> getIbanData() {
        return Arrays.asList(new Object[][]{
                {new Iban.Builder()
                        .countryCode(CountryCode.AL)
                        .bankCode("212")
                        .branchCode("1100")
                        .accountNumber("0000000235698741")
                        .nationalCheckDigit("9")
                        .build(), "AL47212110090000000235698741"},
                {new Iban.Builder()
                        .countryCode(CountryCode.AD)
                        .bankCode("0001")
                        .branchCode("2030")
                        .accountNumber("200359100100")
                        .build(), "AD1200012030200359100100"},
                {new Iban.Builder()
                        .countryCode(CountryCode.AT)
                        .bankCode("19043")
                        .accountNumber("00234573201")
                        .build(), "AT611904300234573201"},
                {new Iban.Builder()
                        .countryCode(CountryCode.AZ)
                        .bankCode("NABZ")
                        .accountNumber("00000000137010001944")
                        .build(), "AZ21NABZ00000000137010001944"},
                {new Iban.Builder()
                        .countryCode(CountryCode.BH)
                        .bankCode("BMAG")
                        .accountNumber("00001299123456")
                        .build(), "BH67BMAG00001299123456"},
                {new Iban.Builder()
                        .countryCode(CountryCode.BE)
                        .bankCode("539")
                        .accountNumber("0075470")
                        .nationalCheckDigit("34")
                        .build(), "BE68539007547034"},
                {new Iban.Builder()
                        .countryCode(CountryCode.BA)
                        .bankCode("129")
                        .branchCode("007")
                        .accountNumber("94010284")
                        .nationalCheckDigit("94")
                        .build(), "BA391290079401028494"},
                {new Iban.Builder()
                        .countryCode(CountryCode.BR)
                        .bankCode("00360305")
                        .branchCode("00001")
                        .accountNumber("0009795493")
                        .accountType("P")
                        .ownerAccountType("1")
                        .build(), "BR9700360305000010009795493P1"},
                {new Iban.Builder()
                        .countryCode(CountryCode.BG)
                        .bankCode("BNBG")
                        .branchCode("9661")
                        .accountNumber("20345678")
                        .accountType("10")
                        .build(), "BG80BNBG96611020345678"},
                {new Iban.Builder()
                        .countryCode(CountryCode.CR)
                        .bankCode("152")
                        .accountNumber("02001026284066")
                        .build(), "CR0515202001026284066"},
                {new Iban.Builder()
                        .countryCode(CountryCode.HR)
                        .bankCode("1001005")
                        .accountNumber("1863000160")
                        .build(), "HR1210010051863000160"},
                {new Iban.Builder()
                        .countryCode(CountryCode.CY)
                        .bankCode("002")
                        .branchCode("00128")
                        .accountNumber("0000001200527600")
                        .build(), "CY17002001280000001200527600"},
                {new Iban.Builder()
                        .countryCode(CountryCode.CZ)
                        .bankCode("0800")
                        .accountNumber("0000192000145399")
                        .build(), "CZ6508000000192000145399"},
                {new Iban.Builder()
                        .countryCode(CountryCode.DK)
                        .bankCode("0040")
                        .accountNumber("0440116243")
                        .build(), "DK5000400440116243"},
                {new Iban.Builder()
                        .countryCode(CountryCode.DO)
                        .bankCode("BAGR")
                        .accountNumber("00000001212453611324")
                        .build(), "DO28BAGR00000001212453611324"},
                {new Iban.Builder()
                        .countryCode(CountryCode.EE)
                        .bankCode("22")
                        .branchCode("00")
                        .accountNumber("22102014568")
                        .nationalCheckDigit("5")
                        .build(), "EE382200221020145685"},
                {new Iban.Builder()
                        .countryCode(CountryCode.FI)
                        .bankCode("123456")
                        .accountNumber("0000078")
                        .nationalCheckDigit("5")
                        .build(), "FI2112345600000785"},
                {new Iban.Builder()
                        .countryCode(CountryCode.FR)
                        .bankCode("20041")
                        .branchCode("01005")
                        .accountNumber("0500013M026")
                        .nationalCheckDigit("06")
                        .build(), "FR1420041010050500013M02606"},
                {new Iban.Builder()
                        .countryCode(CountryCode.GE)
                        .bankCode("NB")
                        .accountNumber("0000000101904917")
                        .build(), "GE29NB0000000101904917"},
                {new Iban.Builder()
                        .countryCode(CountryCode.DE)
                        .bankCode("37040044")
                        .accountNumber("0532013000")
                        .build(), "DE89370400440532013000"},
                {new Iban.Builder()
                        .countryCode(CountryCode.GI)
                        .bankCode("NWBK")
                        .accountNumber("000000007099453")
                        .build(), "GI75NWBK000000007099453"},
                {new Iban.Builder()
                        .countryCode(CountryCode.GR)
                        .bankCode("011")
                        .branchCode("0125")
                        .accountNumber("0000000012300695")
                        .build(), "GR1601101250000000012300695"},
                {new Iban.Builder()
                        .countryCode(CountryCode.GT)
                        .bankCode("TRAJ")
                        .accountNumber("01020000001210029690")
                        .build(), "GT82TRAJ01020000001210029690"},
                {new Iban.Builder()
                        .countryCode(CountryCode.HU)
                        .bankCode("117")
                        .branchCode("7301")
                        .accountNumber("6111110180000000")
                        .nationalCheckDigit("0")
                        .build(), "HU42117730161111101800000000"},
                {new Iban.Builder()
                        .countryCode(CountryCode.IS)
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {new Iban.Builder()
                        .countryCode(CountryCode.IE)
                        .bankCode("AIBK")
                        .branchCode("931152")
                        .accountNumber("12345678")
                        .build(), "IE29AIBK93115212345678"},
                {new Iban.Builder()
                        .countryCode(CountryCode.IL)
                        .bankCode("010")
                        .branchCode("800")
                        .accountNumber("0000099999999")
                        .build(), "IL620108000000099999999"},
                {new Iban.Builder()
                        .countryCode(CountryCode.IT)
                        .bankCode("05428")
                        .branchCode("11101")
                        .nationalCheckDigit("X")
                        .accountNumber("000000123456")
                        .build(), "IT60X0542811101000000123456"},
                {new Iban.Builder()
                        .countryCode(CountryCode.JO)
                        .bankCode("CBJO")
                        .branchCode("0010")
                        .accountNumber("000000000131000302")
                        .build(), "JO94CBJO0010000000000131000302"},
                {new Iban.Builder()
                        .countryCode(CountryCode.KZ)
                        .bankCode("125")
                        .accountNumber("KZT5004100100")
                        .build(), "KZ86125KZT5004100100"},
                {new Iban.Builder()
                        .countryCode(CountryCode.KW)
                        .bankCode("CBKU")
                        .accountNumber("0000000000001234560101")
                        .build(), "KW81CBKU0000000000001234560101"},
                {new Iban.Builder()
                        .countryCode(CountryCode.LV)
                        .bankCode("BANK")
                        .accountNumber("0000435195001")
                        .build(), "LV80BANK0000435195001"},
                {new Iban.Builder()
                        .countryCode(CountryCode.LB)
                        .bankCode("0999")
                        .accountNumber("00000001001901229114")
                        .build(), "LB62099900000001001901229114"},
                {new Iban.Builder()
                        .countryCode(CountryCode.LI)
                        .bankCode("08810")
                        .accountNumber("0002324013AA")
                        .build(), "LI21088100002324013AA"},
                {new Iban.Builder()
                        .countryCode(CountryCode.LT)
                        .bankCode("10000")
                        .accountNumber("11101001000")
                        .build(), "LT121000011101001000"},
                {new Iban.Builder()
                        .countryCode(CountryCode.LU)
                        .bankCode("001")
                        .accountNumber("9400644750000")
                        .build(), "LU280019400644750000"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MK)
                        .bankCode("250")
                        .accountNumber("1200000589")
                        .nationalCheckDigit("84")
                        .build(), "MK07250120000058984"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MT)
                        .bankCode("MALT")
                        .branchCode("01100")
                        .accountNumber("0012345MTLCAST001S")
                        .build(), "MT84MALT011000012345MTLCAST001S"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MR)
                        .bankCode("00020")
                        .branchCode("00101")
                        .accountNumber("00001234567")
                        .nationalCheckDigit("53")
                        .build(), "MR1300020001010000123456753"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MU)
                        .bankCode("BOMM01")
                        .branchCode("01")
                        .accountNumber("101030300200000MUR")
                        .build(), "MU17BOMM0101101030300200000MUR"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MD)
                        .bankCode("AG")
                        .accountNumber("000225100013104168")
                        .build(), "MD24AG000225100013104168"},
                {new Iban.Builder()
                        .countryCode(CountryCode.MC)
                        .bankCode("11222")
                        .branchCode("00001")
                        .accountNumber("01234567890")
                        .nationalCheckDigit("30")
                        .build(), "MC5811222000010123456789030"},
                {new Iban.Builder()
                        .countryCode(CountryCode.ME)
                        .bankCode("505")
                        .accountNumber("0000123456789")
                        .nationalCheckDigit("51")
                        .build(), "ME25505000012345678951"},
                {new Iban.Builder()
                        .countryCode(CountryCode.NL)
                        .bankCode("ABNA")
                        .accountNumber("0417164300")
                        .build(), "NL91ABNA0417164300"},
                {new Iban.Builder()
                        .countryCode(CountryCode.NO)
                        .bankCode("8601")
                        .accountNumber("111794")
                        .nationalCheckDigit("7")
                        .build(), "NO9386011117947"},
                {new Iban.Builder()
                        .countryCode(CountryCode.PK)
                        .bankCode("SCBL")
                        .accountNumber("0000001123456702")
                        .build(), "PK36SCBL0000001123456702"},
                {new Iban.Builder()
                        .countryCode(CountryCode.PS)
                        .bankCode("PALS")
                        .accountNumber("000000000400123456702")
                        .build(), "PS92PALS000000000400123456702"},
                {new Iban.Builder()
                        .countryCode(CountryCode.PL)
                        .bankCode("109")
                        .branchCode("0101")
                        .accountNumber("0000071219812874")
                        .nationalCheckDigit("4")
                        .build(), "PL61109010140000071219812874"},
                {new Iban.Builder()
                        .countryCode(CountryCode.PT)
                        .bankCode("0002")
                        .branchCode("0123")
                        .accountNumber("12345678901")
                        .nationalCheckDigit("54")
                        .build(), "PT50000201231234567890154"},
                {new Iban.Builder()
                        .countryCode(CountryCode.RO)
                        .bankCode("AAAA")
                        .accountNumber("1B31007593840000")
                        .build(), "RO49AAAA1B31007593840000"},
                {new Iban.Builder()
                        .countryCode(CountryCode.QA)
                        .bankCode("DOHB")
                        .accountNumber("00001234567890ABCDEFG")
                        .build(), "QA58DOHB00001234567890ABCDEFG"},
                {new Iban.Builder()
                        .countryCode(CountryCode.SM)
                        .bankCode("03225")
                        .branchCode("09800")
                        .accountNumber("000000270100")
                        .nationalCheckDigit("U")
                        .build(), "SM86U0322509800000000270100"},
                {new Iban.Builder()
                        .countryCode(CountryCode.SA)
                        .bankCode("80")
                        .accountNumber("000000608010167519")
                        .build(), "SA0380000000608010167519"},
                {new Iban.Builder()
                        .countryCode(CountryCode.RS)
                        .bankCode("260")
                        .branchCode("26")
                        .accountNumber("0056010016113")
                        .nationalCheckDigit("79")
                        .build(), "RS35260005601001611379"},
                {new Iban.Builder()
                        .countryCode(CountryCode.SK)
                        .bankCode("1200")
                        .accountNumber("0000198742637541")
                        .build(), "SK3112000000198742637541"},
                {new Iban.Builder()
                        .countryCode(CountryCode.SI)
                        .bankCode("26")
                        .branchCode("330")
                        .accountNumber("00120390")
                        .nationalCheckDigit("86")
                        .build(), "SI56263300012039086"},
                {new Iban.Builder()
                        .countryCode(CountryCode.ES)
                        .bankCode("2100")
                        .branchCode("0418")
                        .accountNumber("0200051332")
                        .nationalCheckDigit("45")
                        .build(), "ES9121000418450200051332"},
                {new Iban.Builder()
                        .countryCode(CountryCode.SE)
                        .bankCode("500")
                        .accountNumber("00000058398257466")
                        .build(), "SE4550000000058398257466"},
                {new Iban.Builder()
                        .countryCode(CountryCode.CH)
                        .bankCode("00762")
                        .accountNumber("011623852957")
                        .build(), "CH9300762011623852957"},
                {new Iban.Builder()
                        .countryCode(CountryCode.TN)
                        .bankCode("10")
                        .branchCode("006")
                        .accountNumber("035183598478831")
                        .build(), "TN5910006035183598478831"},
                {new Iban.Builder()
                        .countryCode(CountryCode.TR)
                        .bankCode("00061")
                        .accountNumber("0519786457841326")
                        .nationalCheckDigit("0")
                        .build(), "TR330006100519786457841326"},
                {new Iban.Builder()
                        .countryCode(CountryCode.AE)
                        .bankCode("033")
                        .accountNumber("1234567890123456")
                        .build(), "AE070331234567890123456"},
                {new Iban.Builder()
                        .countryCode(CountryCode.GB)
                        .bankCode("NWBK")
                        .branchCode("601613")
                        .accountNumber("31926819")
                        .build(), "GB29NWBK60161331926819"},
                {new Iban.Builder()
                        .countryCode(CountryCode.VG)
                        .bankCode("VPVG")
                        .accountNumber("0000012345678901")
                        .build(), "VG96VPVG0000012345678901"},
                {new Iban.Builder()
                        .countryCode(CountryCode.TL)
                        .bankCode("008")
                        .accountNumber("00123456789101")
                        .nationalCheckDigit("57")
                        .build(), "TL380080012345678910157"},
                {new Iban.Builder()
                        .countryCode(CountryCode.XK)
                        .bankCode("10")
                        .branchCode("00")
                        .accountNumber("0000000000")
                        .nationalCheckDigit("53")
                        .build(), "XK051000000000000053"}
        });
    }

    public static Collection<Object[]> getBicData() {
        return Arrays.asList(new Object[][]{
                {"DEUTDEFF"},
                {"DEUTDEFF500"},
                {"NEDSZAJJXXX"},
                {"DABADKKK"},
                {"UNCRIT2B912"},
                {"DSBACNBXSHA"},
                {"BNORPHMM"}
        });
    }

}
