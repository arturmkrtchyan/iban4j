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

import java.io.Serializable;

public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    private CountryCode countryCode;
    private String checkDigit;
    private Bban bban;

    public Iban(CountryCode countryCode, Bban bban) throws IbanFormatException {
        this.countryCode = countryCode;
        this.bban = bban;
        this.checkDigit = IbanUtil.calculateCheckDigit(countryCode, bban);
        // TODO validation
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public Bban getBban() {
        return bban;
    }

    public static Iban valueOf() throws IbanFormatException {
        // TODO implement
        return null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(countryCode.name())
                .append(checkDigit)
                .append(bban.format(countryCode))
                .toString();
    }
}
