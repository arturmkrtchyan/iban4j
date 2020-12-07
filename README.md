iban4j 
======

![Test](https://github.com/Paylib-Services/iban4j/workflows/Test/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://raw.githubusercontent.com/arturmkrtchyan/iban4j/master/LICENSE.txt)

A Java library for generation and validation of the International Bank Account Numbers (<a href="http://en.wikipedia.org/wiki/ISO_13616" target="_blank">IBAN ISO_13616</a>) and Business Identifier Codes (<a href="http://en.wikipedia.org/wiki/ISO_9362" target="_blank">BIC ISO_9362</a>).

Forked from https://github.com/arturmkrtchyan/iban4j.

#### Iban quick examples:

```java
 // How to generate Iban
 Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("19043")
                .accountNumber("00234573201")
                .build();


 // How to create Iban object from String
 Iban iban = Iban.valueOf("DE89370400440532013000");

 // How to create Iban object from formatted String
 Iban iban = Iban.valueOf("DE89 3704 0044 0532 0130 00", IbanFormat.Default);

 // How to generate random Iban
 Iban iban = Iban.random(CountryCode.AT);
 Iban iban = Iban.random();
 Iban iban = new Iban.Builder()
                 .countryCode(CountryCode.AT)
                 .bankCode("19043")
                 .buildRandom();

 // How to validate Iban 
 try {
     IbanUtil.validate("AT611904300234573201");
     IbanUtil.validate("DE89 3704 0044 0532 0130 00", IbanFormat.Default);
     // valid
 } catch (IbanFormatException |
          InvalidCheckDigitException |
          UnsupportedCountryException e) {
     // invalid
 }
```

#### Bic quick examples:

```java
 //How to create Bic object from String
 Bic bic = Bic.valueOf("DEUTDEFF");


 //How to validate Bic
 try {
     BicUtil.validate("DEUTDEFF500");
     // valid
 } catch (BicFormatException e) {
     // invalid
 }
```

#### Maven dependency: 
```
<dependency>
  <groupId>org.iban4j</groupId>
  <artifactId>iban4j</artifactId>
  <version>3.2.1</version>
</dependency>
```

![Compatibility Badge](https://java.net/downloads/adoptopenjdk/compat.svg)

#### References

- http://en.wikipedia.org/wiki/ISO_13616
- http://en.wikipedia.org/wiki/ISO_9362
- https://www.ecb.europa.eu/paym/retpaym/paymint/sepa/shared/pdf/iban_registry.pdf

## License
Copyright 2015 Artur Mkrtchyan.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0

