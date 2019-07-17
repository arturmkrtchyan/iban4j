package com.swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistryPattern {

    private static final String DIGITS_REGEX = "[0-9]";
    private static final char DIGITS_REPRESENTATION = 'n';

    private static final String UPPER_CASE_LETTERS_REGEX = "[A-Z]";
    private static final char UPPER_CASE_LETTERS_REPRESENTATION = 'a';

    private static final String UPPER_AND_LOWER_CASE_ALPHANUMERIC_REGEX = "[A-Za-z0-9]";
    private static final char UPPER_AND_LOWER_CASE_ALPHANUMERIC_REPRESENTATION = 'c';

    private static final String BLANK_SPACE_REGEX = "[ ]";
    private static final char BLANK_SPACE_REPRESENTATION = 'e';

    private static final char FIXED_LENGTH = '!';

    private static final String SINGLE_GROUP_REGEX = "[0-9]+!?[ance]";
    private static final Pattern SINGLE_GROUP_PATTERN = Pattern.compile(SINGLE_GROUP_REGEX);
    private static final Pattern MULTI_GROUP_PATTERN = Pattern.compile("^(" + SINGLE_GROUP_REGEX + ")+$");

    private final Pattern regexPattern;
    private final String registryPattern;

    private RegistryPattern(Pattern regexPattern, String registryPattern) {
        this.regexPattern = regexPattern;
        this.registryPattern = registryPattern;
    }

    public static RegistryPattern compile(String registryPattern) {
        return new RegistryPattern(buildRegexPattern(registryPattern), registryPattern);
    }

    private static Pattern buildRegexPattern(String swiftPattern) {
        StringBuilder builder = new StringBuilder("^");

        Matcher matcher = SINGLE_GROUP_PATTERN.matcher(swiftPattern);
        while (matcher.find()) {
            builder.append(groupToRegex(matcher.group()));
        }
        builder.append("$");

        return Pattern.compile(builder.toString());
    }

    private static String groupToRegex(String group) {
        String characterRegex = null;
        switch (group.charAt(group.length() - 1)) {
            case DIGITS_REPRESENTATION:
                characterRegex = DIGITS_REGEX;
                break;
            case UPPER_CASE_LETTERS_REPRESENTATION:
                characterRegex = UPPER_CASE_LETTERS_REGEX;
                break;
            case UPPER_AND_LOWER_CASE_ALPHANUMERIC_REPRESENTATION:
                characterRegex = UPPER_AND_LOWER_CASE_ALPHANUMERIC_REGEX;
                break;
            case BLANK_SPACE_REPRESENTATION:
                characterRegex = BLANK_SPACE_REGEX;
                break;
        }

        boolean fixedLength = group.charAt(group.length() - 2) == FIXED_LENGTH;
        String maximumLength = group.substring(0, group.length() - (fixedLength ? 2 : 1));

        return characterRegex + "{" + (fixedLength ? "" : "1,") + maximumLength + "}";
    }

    public Pattern getRegexPattern() {
        return regexPattern;
    }

    public String getRegistryPattern() {
        return registryPattern;
    }
}
