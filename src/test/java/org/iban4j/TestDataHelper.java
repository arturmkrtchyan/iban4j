/* Licensed under the Apache License, Version 2.0 (the "License");
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

public final class TestDataHelper {

    private TestDataHelper() {
    }

    public static Collection<Object[]> getIbanData() {
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
                {CountryCode.CY, new Bban.Builder()
                        .bankCode("002")
                        .branchCode("00128")
                        .accountNumber("0000001200527600")
                        .build(), "CY17002001280000001200527600"},
                {CountryCode.CZ, new Bban.Builder()
                        .bankCode("0800")
                        .accountNumber("0000192000145399")
                        .build(), "CZ6508000000192000145399"},
                {CountryCode.DK, new Bban.Builder()
                        .bankCode("0040")
                        .accountNumber("0440116243")
                        .build(), "DK5000400440116243"},
                {CountryCode.DO, new Bban.Builder()
                        .bankCode("BAGR")
                        .accountNumber("00000001212453611324")
                        .build(), "DO28BAGR00000001212453611324"},
                {CountryCode.EE, new Bban.Builder()
                        .bankCode("22")
                        .branchCode("00")
                        .accountNumber("22102014568")
                        .nationalCheckDigit("5")
                        .build(), "EE382200221020145685"},
                {CountryCode.FI, new Bban.Builder()
                        .bankCode("123456")
                        .accountNumber("0000078")
                        .nationalCheckDigit("5")
                        .build(), "FI2112345600000785"},
                {CountryCode.FR, new Bban.Builder()
                        .bankCode("20041")
                        .branchCode("01005")
                        .accountNumber("0500013M026")
                        .nationalCheckDigit("06")
                        .build(), "FR1420041010050500013M02606"},
                {CountryCode.GE, new Bban.Builder()
                        .bankCode("NB")
                        .accountNumber("0000000101904917")
                        .build(), "GE29NB0000000101904917"},
                {CountryCode.DE, new Bban.Builder()
                        .bankCode("37040044")
                        .accountNumber("0532013000")
                        .build(), "DE89370400440532013000"},
                {CountryCode.GI, new Bban.Builder()
                        .bankCode("NWBK")
                        .accountNumber("000000007099453")
                        .build(), "GI75NWBK000000007099453"},
                {CountryCode.GR, new Bban.Builder()
                        .bankCode("011")
                        .branchCode("0125")
                        .accountNumber("0000000012300695")
                        .build(), "GR1601101250000000012300695"},
                {CountryCode.GT, new Bban.Builder()
                        .bankCode("TRAJ")
                        .accountNumber("01020000001210029690")
                        .build(), "GT82TRAJ01020000001210029690"},
                {CountryCode.HU, new Bban.Builder()
                        .bankCode("117")
                        .branchCode("7301")
                        .accountNumber("6111110180000000")
                        .nationalCheckDigit("0")
                        .build(), "HU42117730161111101800000000"},
                {CountryCode.IS, new Bban.Builder()
                        .bankCode("0159")
                        .branchCode("26")
                        .accountNumber("007654")
                        .identificationNumber("5510730339")
                        .build(), "IS140159260076545510730339"},
                {CountryCode.IE, new Bban.Builder()
                        .bankCode("AIBK")
                        .branchCode("931152")
                        .accountNumber("12345678")
                        .build(), "IE29AIBK93115212345678"},
                {CountryCode.IL, new Bban.Builder()
                        .bankCode("010")
                        .branchCode("800")
                        .accountNumber("0000099999999")
                        .build(), "IL620108000000099999999"},
                {CountryCode.IT, new Bban.Builder()
                        .bankCode("05428")
                        .branchCode("11101")
                        .nationalCheckDigit("X")
                        .accountNumber("000000123456")
                        .build(), "IT60X0542811101000000123456"},
                {CountryCode.KZ, new Bban.Builder()
                        .bankCode("125")
                        .accountNumber("KZT5004100100")
                        .build(), "KZ86125KZT5004100100"},
                {CountryCode.KW, new Bban.Builder()
                        .bankCode("CBKU")
                        .accountNumber("0000000000001234560101")
                        .build(), "KW81CBKU0000000000001234560101"},
                {CountryCode.LV, new Bban.Builder()
                        .bankCode("BANK")
                        .accountNumber("0000435195001")
                        .build(), "LV80BANK0000435195001"},
                {CountryCode.LB, new Bban.Builder()
                        .bankCode("0999")
                        .accountNumber("00000001001901229114")
                        .build(), "LB62099900000001001901229114"},
                {CountryCode.LI, new Bban.Builder()
                        .bankCode("08810")
                        .accountNumber("0002324013AA")
                        .build(), "LI21088100002324013AA"},
                {CountryCode.LT, new Bban.Builder()
                        .bankCode("10000")
                        .accountNumber("11101001000")
                        .build(), "LT121000011101001000"},
                {CountryCode.LU, new Bban.Builder()
                        .bankCode("001")
                        .accountNumber("9400644750000")
                        .build(), "LU280019400644750000"},
                {CountryCode.MK, new Bban.Builder()
                        .bankCode("250")
                        .accountNumber("1200000589")
                        .nationalCheckDigit("84")
                        .build(), "MK07250120000058984"},
                {CountryCode.MT, new Bban.Builder()
                        .bankCode("MALT")
                        .branchCode("01100")
                        .accountNumber("0012345MTLCAST001S")
                        .build(), "MT84MALT011000012345MTLCAST001S"},
                {CountryCode.MR, new Bban.Builder()
                        .bankCode("00020")
                        .branchCode("00101")
                        .accountNumber("00001234567")
                        .nationalCheckDigit("53")
                        .build(), "MR1300020001010000123456753"},
                {CountryCode.MU, new Bban.Builder()
                        .bankCode("BOMM01")
                        .branchCode("01")
                        .accountNumber("101030300200000MUR")
                        .build(), "MU17BOMM0101101030300200000MUR"},
                {CountryCode.MD, new Bban.Builder()
                        .bankCode("AG")
                        .accountNumber("000225100013104168")
                        .build(), "MD24AG000225100013104168"},
                {CountryCode.MC, new Bban.Builder()
                        .bankCode("11222")
                        .branchCode("00001")
                        .accountNumber("01234567890")
                        .nationalCheckDigit("30")
                        .build(), "MC5811222000010123456789030"},
                {CountryCode.ME, new Bban.Builder()
                        .bankCode("505")
                        .accountNumber("0000123456789")
                        .nationalCheckDigit("51")
                        .build(), "ME25505000012345678951"},
                {CountryCode.NL, new Bban.Builder()
                        .bankCode("ABNA")
                        .accountNumber("0417164300")
                        .build(), "NL91ABNA0417164300"},
                {CountryCode.NO, new Bban.Builder()
                        .bankCode("8601")
                        .accountNumber("111794")
                        .nationalCheckDigit("7")
                        .build(), "NO9386011117947"},
                {CountryCode.PK, new Bban.Builder()
                        .bankCode("SCBL")
                        .accountNumber("0000001123456702")
                        .build(), "PK36SCBL0000001123456702"},
                {CountryCode.PS, new Bban.Builder()
                        .bankCode("PALS")
                        .accountNumber("000000000400123456702")
                        .build(), "PS92PALS000000000400123456702"},
                {CountryCode.PL, new Bban.Builder()
                        .bankCode("109")
                        .branchCode("0101")
                        .accountNumber("0000071219812874")
                        .nationalCheckDigit("4")
                        .build(), "PL61109010140000071219812874"},
                {CountryCode.PT, new Bban.Builder()
                        .bankCode("0002")
                        .branchCode("0123")
                        .accountNumber("12345678901")
                        .nationalCheckDigit("54")
                        .build(), "PT50000201231234567890154"},
                {CountryCode.RO, new Bban.Builder()
                        .bankCode("AAAA")
                        .accountNumber("1B31007593840000")
                        .build(), "RO49AAAA1B31007593840000"},
                {CountryCode.QA, new Bban.Builder()
                        .bankCode("DOHB")
                        .accountNumber("00001234567890ABCDEFG")
                        .build(), "QA58DOHB00001234567890ABCDEFG"},
                {CountryCode.SM, new Bban.Builder()
                        .bankCode("03225")
                        .branchCode("09800")
                        .accountNumber("000000270100")
                        .nationalCheckDigit("U")
                        .build(), "SM86U0322509800000000270100"},
                {CountryCode.SA, new Bban.Builder()
                        .bankCode("80")
                        .accountNumber("000000608010167519")
                        .build(), "SA0380000000608010167519"},
                {CountryCode.RS, new Bban.Builder()
                        .bankCode("260")
                        .branchCode("26")
                        .accountNumber("0056010016113")
                        .nationalCheckDigit("79")
                        .build(), "RS35260005601001611379"},
                {CountryCode.SK, new Bban.Builder()
                        .bankCode("1200")
                        .accountNumber("0000198742637541")
                        .build(), "SK3112000000198742637541"},
                {CountryCode.SI, new Bban.Builder()
                        .bankCode("26")
                        .branchCode("330")
                        .accountNumber("00120390")
                        .nationalCheckDigit("86")
                        .build(), "SI56263300012039086"},
                {CountryCode.ES, new Bban.Builder()
                        .bankCode("2100")
                        .branchCode("0418")
                        .accountNumber("0200051332")
                        .nationalCheckDigit("45")
                        .build(), "ES9121000418450200051332"},
                {CountryCode.SE, new Bban.Builder()
                        .bankCode("500")
                        .accountNumber("00000058398257466")
                        .build(), "SE4550000000058398257466"},
                {CountryCode.CH, new Bban.Builder()
                        .bankCode("00762")
                        .accountNumber("011623852957")
                        .build(), "CH9300762011623852957"},
                {CountryCode.TN, new Bban.Builder()
                        .bankCode("10")
                        .branchCode("006")
                        .accountNumber("035183598478831")
                        .build(), "TN5910006035183598478831"},
                {CountryCode.TR, new Bban.Builder()
                        .bankCode("00061")
                        .accountNumber("0519786457841326")
                        .nationalCheckDigit("0")
                        .build(), "TR330006100519786457841326"},
                {CountryCode.AE, new Bban.Builder()
                        .bankCode("033")
                        .accountNumber("1234567890123456")
                        .build(), "AE070331234567890123456"},
                {CountryCode.GB, new Bban.Builder()
                        .bankCode("NWBK")
                        .branchCode("601613")
                        .accountNumber("31926819")
                        .build(), "GB29NWBK60161331926819"},
                {CountryCode.VG, new Bban.Builder()
                        .bankCode("VPVG")
                        .accountNumber("0000012345678901")
                        .build(), "VG96VPVG0000012345678901"}
        });
    }

}
