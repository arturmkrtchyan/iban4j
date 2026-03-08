# Reference Documents

This directory contains official reference documents used for implementing IBAN validation features.

## TR201.pdf - Register of European Account Numbers

**Source**: [Czech National Bank](https://www.cnb.cz/export/sites/cnb/cs/platebni-styk/.galleries/iban/download/TR201.pdf)  
**Version**: TR201 V3.23 — FEBRUARY 2007  
**Purpose**: Comprehensive reference for national account number structures and check digit algorithms across European countries

### Key Information
- Contains detailed specifications for national account number formats
- Includes country-specific check digit algorithms
- Provides examples and validation rules for each supported country
- Covers both domestic account numbers and IBAN structures

### Countries Covered
The document includes specifications for numerous European countries including:
- Tunisia (with detailed modulo 97 algorithm)
- Germany, France, Italy, Spain, Netherlands
- Nordic countries (Denmark, Finland, Norway, Sweden)
- Eastern European countries (Poland, Czech Republic, Hungary, etc.)
- And many more...

### Usage in iban4j
This document serves as the authoritative reference for implementing national check digit validation in the iban4j library, ensuring compliance with official banking standards.

### File Details
- **File Size**: ~1.3 MB
- **Format**: PDF
- **Last Updated**: February 2007
- **Language**: English
