# National Check Digit Validation Documentation

---

## 📋 Table of Contents

1. [User Documentation](#-user-documentation)
2. [Developer Documentation](#-developer-documentation)
3. [Repository Structure](#-repository-structure)
4. [In-Code Documentation Examples](#-in-code-documentation-examples)
5. [GitHub Wiki Pages](#-github-wiki-pages)
6. [README Updates](#-readme-updates)

---

## 📖 User Documentation

### File: `docs/NATIONAL_CHECK_DIGITS.md`

```markdown
# National Check Digit Validation

## Overview

iban4j now supports validation of national check digits found in certain countries' IBAN structures. This provides an additional layer of validation beyond the standard IBAN MOD-97 check digits.

## Quick Start

### Basic Usage

```java
// Enhanced validation with national check digits
try {
    EnhancedIbanUtil.validateWithNationalCheckDigit("ES9121000418450200051332");
    System.out.println("✅ IBAN is fully valid");
} catch (InvalidNationalCheckDigitException e) {
    System.out.printf("❌ National check digit invalid: Expected %s, got %s%n", 
        e.getExpectedCheckDigit(), e.getActualCheckDigit());
}

// Simple boolean check
boolean isValid = EnhancedIbanUtil.isValidWithNationalCheckDigit("ES9121000418450200051332");
```

### Supported Countries

| Country       | Code | Check Digit | Example IBAN                    |
|---------------|------|-------------|---------------------------------|
| Spain 🇪🇸    | ES   | 2 digits    | ES9121000418**45**0200051332    |
| France 🇫🇷   | FR   | 2 digits    | FR1420041010050500013M026**06** |
| Italy 🇮🇹    | IT   | 1 letter    | IT60**X**0542811101000000123456 |
| Belgium 🇧🇪  | BE   | 2 digits    | BE68539007547034                |
| Portugal 🇵🇹 | PT   | 2 digits    | PT50000201231234567890154       |

### Migration from Standard Validation

```java
// Before: Standard validation only
boolean isValid = IbanUtil.isValid(iban);

// After: Enhanced validation with national check digits
boolean isValid = EnhancedIbanUtil.isValidWithNationalCheckDigit(iban);

// Backward compatibility: Standard validation still works
boolean standardValid = IbanUtil.isValid(iban); // Still works as before
```

### Error Handling

```java
try {
    EnhancedIbanUtil.validateWithNationalCheckDigit(iban);
} catch (InvalidNationalCheckDigitException e) {
    // National check digit specific error
    log.warn("National check digit validation failed for {}: {}", 
        e.getCountryCode(), e.getMessage());
} catch (InvalidCheckDigitException e) {
    // Standard IBAN check digit error
    log.warn("IBAN check digit validation failed: {}", e.getMessage());
} catch (IbanFormatException e) {
    // IBAN format error
    log.warn("IBAN format validation failed: {}", e.getMessage());
}
```

## FAQ

### Q: Will this break my existing code?

**A:** No, all existing `IbanUtil` methods continue to work exactly as before. National check digit
validation is opt-in.

### Q: What happens for countries without national check digits?

**A:** The validation passes normally. Only countries with national check digits are affected.

### Q: How do I know if a country supports national check digit validation?

```java
boolean supported = NationalCheckDigitValidatorFactory.isSupported(CountryCode.ES);
```

### Q: Can I use this with formatted IBANs?

**A:** Yes, formatted IBANs are supported:

```java
boolean valid = EnhancedIbanUtil.isValidWithNationalCheckDigit(
    "ES91 2100 0418 4502 0005 1332", IbanFormat.Default);
```

```