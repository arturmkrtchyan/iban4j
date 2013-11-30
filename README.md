iban4j [![Build Status](https://travis-ci.org/arturmkrtchyan/iban4j.png)](https://travis-ci.org/arturmkrtchyan/iban4j)
======

A Java library for International Bank Account Number (<a href="http://en.wikipedia.org/wiki/ISO_13616" target="_blank">IBAN</a>) generation

##### Here are some quick examples:

How to generat IBAN

```java
 Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("19043")
                .accountNumber("00234573201")
                .build();
```
How to validate IBAN

```java
        try {
            IbanUtil.validate("AT611904300234573201");
            // valid
        } catch (IbanFormatException e) {
            // invalid
        }
```

##### Maven dependency: 
```
      <dependency>
          <groupId>org.iban4j</groupId>
          <artifactId>iban4j</artifactId>
          <version>1.0.0</version>
      </dependency>
```

## License
Copyright 2013 Artur Mkrtchyan.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
