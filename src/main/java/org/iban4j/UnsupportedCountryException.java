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

/**
 * Thrown to indicate that requested country is not supported.
 */
public class UnsupportedCountryException extends Iban4jException {

    private static final long serialVersionUID = -3733353745417164234L;

    private final String countryCode;

    /**
     * Constructs a <code>UnsupportedCountryException</code> with the specified country code.
     *
     * @param countryCode the country code.
     */
    public UnsupportedCountryException(String countryCode) {
        super(String.format("Country code '%s' is not supported.", countryCode));
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}