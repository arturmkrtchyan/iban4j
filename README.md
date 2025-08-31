iban4j
======

A Java library for the generation, validation, and parsing of
International Bank Account Numbers (IBAN) and Business Identifier Codes (BIC).

[![Build Status](https://github.com/arturmkrtchyan/iban4j/actions/workflows/java-ci.yml/badge.svg)](https://github.com/arturmkrtchyan/iban4j/actions/workflows/java-ci.yml) [![Coverage Status](https://img.shields.io/coveralls/arturmkrtchyan/iban4j.svg)](https://coveralls.io/r/arturmkrtchyan/iban4j) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.iban4j/iban4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.iban4j/iban4j)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://raw.githubusercontent.com/arturmkrtchyan/iban4j/master/LICENSE.txt)

---

### Features

- **IBAN Generation and Validation**:
  Supports creating valid IBANs and verifying their format and check digits.
- **BIC Validation**:
  Checks the validity of Business Identifier Codes.
- **Multiple Formats**:
  Handles both standard and formatted string representations.
- **Customizable Padding**:
  Offers options for zero-padding account and bank codes.

---

## Getting Started

### Maven Dependency

To use **iban4j** in your Maven project, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.iban4j</groupId>
    <artifactId>iban4j</artifactId>
    <version>3.2.11-RELEASE</version>
</dependency>
````

#### Gradle Dependency

To include **iban4j** into your Gradle project, add the following dependency to your `build.gradle` or `build.gradle.kts` file.

For a **Groovy DSL** (`build.gradle`) file:

```groovy
dependencies {
    implementation 'org.iban4j:iban4j:3.2.11-RELEASE'
}
```

For a **Kotlin DSL** (`build.gradle.kts`) file:

```kotlin
dependencies {
    implementation("org.iban4j:iban4j:3.2.11-RELEASE")
}
```

After adding the dependency, run `gradle build`. Gradle will automatically download the library and include it in your project.

-----

### Usage Examples

#### IBAN Examples

##### Generate an IBAN

```java
// Using the Builder pattern
Iban iban = new Iban.Builder()
    .countryCode(CountryCode.AT)
    .bankCode("19043")
    .accountNumber("00234573201")
    .build();
```

##### Create an `Iban` object from a string

```java
// From a raw IBAN string
Iban iban = Iban.valueOf("DE89370400440532013000");

// From a formatted IBAN string
Iban iban = Iban.valueOf("DE89 3704 0044 0532 0130 00", IbanFormat.Default);
```

##### Generate a random IBAN

```java
// For a specific country
Iban iban = Iban.random(CountryCode.AT);

// For a random country
Iban iban = Iban.random();

// Using the builder for more control
Iban iban = new Iban.Builder()
    .countryCode(CountryCode.AT)
    .bankCode("19043")
    .buildRandom();
```

##### Validate an IBAN

```java
try {
    // Validate a raw IBAN
    IbanUtil.validate("AT611904300234573201");
    // Validate a formatted IBAN

    IbanUtil.validate("DE89 3704 0044 0532 0130 00", IbanFormat.Default);
    System.out.println("IBAN is valid");
} catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException ex) {
    System.err.println("IBAN is invalid: " + ex.getMessage());
}
```

##### Enable left-padding for generated IBANs

```java
// Left-pad account number, bank code, and branch code with zeros
Iban iban = new Iban.Builder()
    .leftPadding(true)
    .countryCode(CountryCode.DE)
    .bankCode("66280099")
    .accountNumber("123456700")
    .build();

// Change the default padding character from '0' to '1'
Iban ibanWithCustomPadding = new Iban.Builder()
    .leftPadding(true)
    .paddingCharacter('1')
    .countryCode(CountryCode.DE)
    .bankCode("66280099")
    .accountNumber("123456700")
    .build();
```

#### BIC Examples

##### Create a `Bic` object from a string

```java
Bic bic = Bic.valueOf("DEUTDEFF");
```

##### Validate a BIC

```java
try {
    BicUtil.validate("DEUTDEFF500");
    System.out.println("BIC is valid");
} catch (BicFormatException ex) {
    System.err.println("BIC is invalid: " + ex.getMessage());
}
```

-----

### Compatibility

This library requires [Java 11](https://adoptium.net/temurin/releases/?version=11) or higher.

-----

### References

 - [ISO 13616 International Bank Account Number (IBAN)](http://en.wikipedia.org/wiki/ISO_13616)
 - [ISO 9362 Business Identifier Codes (BIC)](http://en.wikipedia.org/wiki/ISO_9362)
 - [ECB IBAN Registry](https://www.ecb.europa.eu/paym/retpaym/paymint/sepa/shared/pdf/iban_registry.pdf)

-----

### License

Copyright 2015 Artur Mkrtchyan.

Licensed under the Apache License, Version 2.0: [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

