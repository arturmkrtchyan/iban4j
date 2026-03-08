# National Check Digit Validation

## Table of Contents

- [Belgium](#belgium)
- [Bosnia and Herzegovina](#bosnia-and-herzegovina)
- [Croatia](#croatia)
- [Czech Republic](#czech-republic)
- [Estonia](#estonia)
- [Finland](#finland)
- [France](#france)
- [Hungary](#hungary)
- [Iceland](#iceland)
- [Italy](#italy)
- [Macedonia, Former Yugoslav Republic of](#macedonia-former-yugoslav-republic-of)
- [Montenegro](#montenegro)
- [Netherlands](#netherlands)
- [Norway](#norway)
- [Portugal](#portugal)
- [Serbia](#serbia)
- [Slovak Republic](#slovak-republic)
- [Slovenia](#slovenia)
- [Spain](#spain)
- [Sweden](#sweden)
- [Tunisia](#tunisia)

---

## Belgium

**Check algorithm**  
All Belgian financial institutions apply the same modulus-based validation method.  
The check digits are calculated by dividing the first 10 digits by 97. If the remainder is 00, then the check digits are 97. Otherwise the check digits are the remainders.  
**Valid IBAN:** BE68 5390 0754 7034

---

## Bosnia and Herzegovina

**Check algorithm**  
All Bosnia and Herzegovina commercial banks apply the same modulus-based validation method.
Check digits are calculated according to ISO 7064 MOD 97-10 over the entir account number. 
**Valid IBAN:** BA39 1290 0794 0102 8494

---

## Croatia

**Check digits**
A part of a bank code and an account number.

**Check algorithm**  
All Croatian banks apply the same modulus-based validation methods for both, bank code and account number.
The check digits are calculated according to the ISO 7064-MOD 11, 10.
There is no check digit applied to the entire structure. 
**Valid IBAN:** HR12 1001 0051 8630 0016

---

## Czech Republic

**Check digits**
A part of an account number.

**Check algorithm**  
A Modulus 11-check algorithm with weights is used to validate the account number structure.  
The weight table to be used:
1. the first part (prefix) is: 10, 5, 8, 4, 2, 1  
2. the second part (basic) is: 6, 3, 7, 9, 10, 5, 8, 4, 2, 1  
**Valid IBAN:** CZ65 0800 0000 1920 0014 5399

---

## Estonia
https://pangaliit.ee/settlements-and-standards/reference-number-of-the-invoice
**Check algorithm**  
The check digit is calculated according to the method of 7-3-1.  
1. a weight is attached to each digit of the account number, being 7,3,1,7,3,… from right to left  
2. each digit is multiplied by the attached weight and the results are added up  
3. this sum is subtracted from the next nearest multiple of ten  
4. the resulting number is used as check digit which is the last digit of the account number.  
**Valid IBAN:** EE38 2200 2210 2014 5685
**The IBAN structure**
https://pangaliit.ee/settlements-and-standards/bban-greater-than-iban-calculator
---

## Finland

**Check algorithm**  
The validation algorithm used by all Finnish institutions is the Modulus 10 with weights 2, 1, 2, 1 from right to left.
The first 13 digits of the account number – in computerised format - are used for calculating the check digit. The digits are multiplied by their respective weights and the resulting numbers are added up. The sum is deducted from the next number divisible by 10.
The result is the check digit which is included in the account number as the last digit. 
**Valid IBAN:** FI21 1234 5600 0007 85

---

## France

**Check algorithm**  
A uniform check algorithm is applied. Validation of the RIB is based on a modulus 97 method and is applied as follows:
1. Compose the RIB with 5-5-11-2 digits without separators and/or special characters.
   - bank code (5 digits)
   - branch code (5 digits)
   - account number (11 digits)
   - check digits (2 digits)
2. Add leading zeros and convert alphabetic characters to numeric value as follows: 

**Letter → digit table:**  
| AJ | BKS | CLT | DMU | ENV | FOW | GPX | HQY | IRZ |
|----|-----|-----|-----|-----|-----|-----|-----|-----|
| 1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |

**Check digits calculation**
This means that for calculating check digits we should fill the check digits with 00, calculate the remainder of division to 97 and subtract it from 97 and we will receive the FR check digits (clé RIB) that placed as last 2 digits in the FR IBAN

**Valid IBAN:** FR14 2004 1010 0505 0001 3M02 606

---

## Iceland

**Check digits**
A part of account number.

**Check algorithm**  
There is a modulus check on the first eight digits of a3 (kennitala/Identification Number). The rightmost of these eight digits is multiplied by 2, the next one by 3 and so on up to 7 after which the sequence is repeated. As such the last two digits are multiplied by 2 and 3 respectively. The results of these eight multiplications are added up and the sum is divided by 11. If the remainder is 0 then the check digit is 0. Otherwise the remainder is subtracted from 11 and the result is the check digit.
The check digit is the penultimate digit of kennitala and the last digit represents the century in which the person was born or the company registered. 
**Valid IBAN:** IS14 0159 2600 0513 7655 4469 31

---

## Italy

**Check algorithm**  
All financial institutions use the same validation method and have developed their own software.
The check digit is calculated on the 22 characters (bank, branch code and account number).
Each character is given a numeric value depending on whether the character is in an odd or in an even position in the string of 22. The first character on the left is in an odd position. 

1. **Odd transformation algorithm:**

| Letter | Value | Letter | Value | Letter | Value | Letter | Value | Letter | Value |
|--------|-------|--------|-------|--------|-------|--------|-------|--------|-------|
| A/0    | 1     | B/1    | 0     | C/2    | 5     | D/3    | 7     | E/4    | 9     |
| F/5    | 13    | G/6    | 15    | H/7    | 17    | I/8    | 19    | J/9    | 21    |
| K      | 2     | L      | 4     | M      | 18    | N      | 20    | O      | 11    |
| P      | 3     | Q      | 6     | R      | 8     | S      | 12    | T      | 14    |
| U      | 16    | V      | 10    | W      | 22    | X      | 25    | Y      | 24    |
| Z      | 23    |        |       |        |       |        |       |        |       |

2. **Even transformation algorithm:**

| Letter | Value | Letter | Value | Letter | Value | Letter | Value | Letter | Value |
|--------|-------|--------|-------|--------|-------|--------|-------|--------|-------|
| A/0    | 0     | B/1    | 1     | C/2    | 2     | D/3    | 3     | E/4    | 4     |
| F/5    | 5     | G/6    | 6     | H/7    | 7     | I/8    | 8     | J/9    | 9     |
| K      | 10    | L      | 11    | M      | 12    | N      | 13    | O      | 14    |
| P      | 15    | Q      | 16    | R      | 17    | S      | 18    | T      | 19    |
| U      | 20    | V      | 21    | W      | 22    | X      | 23    | Y      | 24    |
| Z      | 25    |        |       |        |       |        |       |        |       |  

The numbers are added up and the result is divided by 26. The remainder is converted into an alphabetic character, which is the check digit, according to: 0=A, 1=B, 2=C, …, 25=Z.  
**Valid IBAN:** IT60 X054 2811 1010 0000 0123 456

---

## Macedonia, Former Yugoslav Republic of

**Check algorithm**  
All Macedonian financial institutions apply the same modulus-based validation method.
The check digits are calculated according to the ISO 7604-MOD 97-10 over the bank code and account number. 
**Valid IBAN:** MK07 2501 2000 0058 984

---

## Montenegro

**Check algorithm**  
The check digits are calculated according to the ISO 7604-MOD 97-10 over the entire account number. 
**Valid IBAN:** ME25 5050 0001 2345 6789 51

---

## Netherlands

**Check algorithm**  
All financial institutions apply a modulus-based algorithm for validating their accounts, except for the Postbank.
If the first 3 digits of the account are zeros or if the account number is composed of 7 or less digits, it concerns a Postbank account for which no validation is possible.
The accounts held at financial institutions are validated as follows:
1. Starting at the right, each digit is multiplied by its respective weight, ranging from 1 to 10.
2. The sum of the resulting numbers is then divided by 11. For the account to be valid, the remainder should be zero. 

**Postbank accounts**
There is no clear algorithm to distinguish Postbank account with 3 leading zeroes from non Postbank account. So make sense to not have strict validation on any account that starts with 3 zeroes.
**Valid IBAN:** NL91 ABNA 0417 1643 00

---

## Norway

**Check algorithm**  
All banks apply a modulus-based method for the validation of the account structure.
The 10-digit account number is multiplied from left to right by the following weights: 5, 4, 3, 2, 7, 6, 5, 4, 3, 2. The resulting numbers are added up and divided by 11. The remainder is subtracted from 11 and becomes the check digit. If the remainder is 0, the check digit will be 0.
If digits 5 and 6 of the account number are zeros, the check digit is calculated on the 7, 8, 9 and 10th digit of the account number.
Account numbers for which the remainder is 1 (check digit 10) cannot be used. 
**Valid IBAN:** NO93 8601 1117 947

---

## Portugal

**Check algorithm**  
Accounts are validated using the Modulus 97/10-check algorithm. 
**Valid IBANs:** PT50 0002 0123 1234 5678 9015 4

---

## Serbia

**Check algorithm**  
The check digits are calculated according to the ISO 7604-MOD 97-10 over the entire account number. 
**Valid IBAN:** RS35 2600 0560 1001 6113 79

---

## Slovak Republic

**Check algorithm**  
A Modulus 11-check algorithm with weights is used to validate the account number structure. The weight table to be used for:
1. the first part (Prefix) is 10, 5, 8, 4, 2, 1
2. the second part (basic) is 6, 3, 7, 9, 10, 5, 8, 4, 2, 1
To validate the first and second parts of the account number, the digits are multiplied by their respective weights, added up and divided by 11. The remainder must be zero. 
**Valid IBAN:** SK31 1200 0000 1987 4263 7541

---

## Slovenia

**Check algorithm**  
All Slovenian financial institutions apply the same modulus-based validation method.
The check digits are calculated according to the ISO 7604-MOD 97-10 over the entire account number
**Valid IBAN:** SI56 1910 0000 0123 438

---

## Spain

**Check algorithm**  
A Modulus 11-check algorithm with weights is used to validate the account number structure.
The weight table to be used is the following: 1, 2, 4, 8, 5, 10, 9, 7, 3, 6.
To validate bank and branch codes, the digits are multiplied by their respective weights, added up and divided by 11. The remainder is subtracted from 11. The result is the check digit.
The second check digit is calculated in the same way.
If the remainder of the subtraction should be either 10 or 11, it is agreed that the check digit becomes 1 if the remainder is 10 and 0 if the remainder is 11. 
**Valid IBAN:** ES91 2100 0418 4502 0005 1332

---

## Tunisia

**Check algorithm**  
1. the elements presented in order are the bank code, the agency code and the account number forms an "N" number of 18 characters.
2. Multiply "N" by 100 = N'
3. Divide N' by 97
4. The remainder of this division will be subtracted from the number 97. The result will represent the key control known as the key of the RIB (the key RIB can take only the values from 01 to 97).

The check digit of the RIB:
1. The elements presented in order as the bank code, the branch code, the account number and the check key forms an N number of 20 characters.
2. By dividing N by 97, the remainder must be equal to zero 

This algoritm is the same that is used for [France](#france). But account is already contains only numbers.

**Valid IBAN:** TN59 1000 6035 1835 9847 8831