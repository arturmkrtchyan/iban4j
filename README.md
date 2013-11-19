iban4j [![Build Status](https://travis-ci.org/arturmkrtchyan/iban4j.png)](https://travis-ci.org/arturmkrtchyan/iban4j)
======

A Java library for International Bank Account Number (IBAN) generation

Here is a quick example: 
```java
 Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("19043")
                .accountNumber("00234573201")
                .build();
 ```

### Work In Progress
