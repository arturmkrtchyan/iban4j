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
