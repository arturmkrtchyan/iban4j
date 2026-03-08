package org.iban4j;

import java.util.Objects;

/**
 * Configuration for national check digit validation.
 * 
 * This class provides configuration options for enabling national check digit validation.
 * 
 * @since 1.0.0
 */
public class ValidationConfig {
    
    private final boolean enableNationalCheckDigitValidation;
    
    private ValidationConfig(Builder builder) {
        this.enableNationalCheckDigitValidation = builder.enableNationalCheckDigitValidation;
    }
    
    /**
     * Creates a new builder for ValidationConfig.
     * 
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Checks if national check digit validation is enabled.
     * 
     * @return true if validation is enabled
     */
    public boolean isEnabled() {
        return enableNationalCheckDigitValidation;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ValidationConfig that = (ValidationConfig) obj;
        return enableNationalCheckDigitValidation == that.enableNationalCheckDigitValidation;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(enableNationalCheckDigitValidation);
    }
    
    @Override
    public String toString() {
        return "ValidationConfig{" +
                "enabled=" + enableNationalCheckDigitValidation +
                '}';
    }
    
    /**
     * Builder for ValidationConfig.
     */
    public static class Builder {
        private boolean enableNationalCheckDigitValidation = false;
        
        /**
         * Enables or disables national check digit validation.
         * 
         * @param enabled true to enable validation
         * @return this builder
         */
        public Builder enableNationalCheckDigitValidation(boolean enabled) {
            this.enableNationalCheckDigitValidation = enabled;
            return this;
        }

        // Alias to avoid API churn during rename
        public Builder enableCountryRules(boolean enabled) {
            this.enableNationalCheckDigitValidation = enabled;
            return this;
        }
        
        /**
         * Builds the ValidationConfig instance.
         * 
         * @return the configured ValidationConfig
         */
        public ValidationConfig build() {
            return new ValidationConfig(this);
        }
    }
}
