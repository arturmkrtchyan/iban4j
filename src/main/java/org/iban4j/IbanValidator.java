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

import org.iban4j.countryrules.CountryRulesAlgorithm;
import org.iban4j.countryrules.CountryRulesAlgorithms;
import org.iban4j.countryrules.CountryRulesRegistry;

/**
 * Non-static, reusable IBAN validator with configurable validation options.
 * 
 * This class provides thread-safe IBAN validation with customizable features
 * such as country-specific rules validation. Instances are immutable and can
 * be reused across multiple validation calls.
 * 
 * @since 1.0.0
 */
public final class IbanValidator {
    
    private final ValidationConfig config;
    
    private IbanValidator(Builder builder) {
        this.config = builder.config;
    }
    
    /**
     * Creates a new builder for IbanValidator.
     * 
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Validates the given IBAN string according to the configured options.
     * 
     * @param iban the IBAN string to validate
     * @throws IbanFormatException if the IBAN is invalid
     * @throws InvalidCheckDigitException if the IBAN has invalid check digit
     * @throws UnsupportedCountryException if the IBAN's country is not supported
     */
    public void validate(String iban) throws IbanFormatException, 
            InvalidCheckDigitException, UnsupportedCountryException {
        // Perform base IBAN validation
        IbanUtil.validate(iban);
        
        // Perform country-specific rules validation if enabled
        if (config != null && config.isEnabled()) {
            CountryRulesAlgorithms.ensureInitialized();
            Iban ibanObj = Iban.valueOf(iban);
            CountryRulesAlgorithm algorithm = CountryRulesRegistry.get(ibanObj.getCountryCode());
            
            if (algorithm != null && !algorithm.validate(ibanObj)) {
                // Note: COUNTRY_RULES_FAILED is currently only applicable to national check digit validation
                // since that's the only country-specific rule implemented at this time
                throw new IbanFormatException(
                    IbanFormatException.IbanFormatViolation.COUNTRY_RULES_FAILED,
                    iban,
                    "Country-specific rules validation failed for " + iban
                );
            }
        }
    }
    
    /**
     * Checks if the given IBAN string is valid according to the configured options.
     * 
     * @param iban the IBAN string to validate
     * @return true if the IBAN is valid, false otherwise
     */
    public boolean isValid(String iban) {
        try {
            validate(iban);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Builder for IbanValidator.
     */
    public static final class Builder {
        private ValidationConfig config = ValidationConfig.builder().build();
        
        /**
         * Sets the validation config for the validator.
         * 
         * @param config the validation config to use
         * @return this builder for method chaining
         */
        public Builder config(ValidationConfig config) {
            this.config = config != null ? config : ValidationConfig.builder().build();
            return this;
        }
        
        /**
         * Enables country-specific rules validation.
         * 
         * @return this builder for method chaining
         */
        public Builder enableCountryRules() {
            this.config = ValidationConfig.builder()
                .enableCountryRules(true)
                .build();
            return this;
        }
        
        /**
         * Builds the IbanValidator instance.
         * 
         * @return the configured IbanValidator
         */
        public IbanValidator build() {
            return new IbanValidator(this);
        }
    }
}
