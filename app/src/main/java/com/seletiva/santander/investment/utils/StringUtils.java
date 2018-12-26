package com.seletiva.santander.investment.utils;

public class StringUtils {
    public static final int REGULAR_PHONE_NUMBER_LENGTH = 10;
    public static final int REGULAR_PHONE_LONG_NUMBER_LENGTH = 11;
    public static final int FORMATTED_MAX_PHONE_LENGTH = 15;

    private static final int INITIAL_PARENTHESES_INDEX = 0;
    private static final int CLOSE_PARENTHESES_INDEX = 2;
    private static final int CLOSE_PARENTHESES_INSERTION_INDEX = 3;
    private static final int AREA_CODE_INDEX = 4;

    private static final int HIFEN_INDEX_FOR_PHONE = 5;
    private static final int HIFEN_INDEX_FOR_LONG_PHONE = 6;

    private static final int HIFEN_MIN_RANGE_FOR_PHONE = 7;
    private static final int HIFEN_MAX_RANGE_FOR_PHONE = 10;

    public static String formatAsPhoneNumber(String unformattedPhone) {
        StringBuilder builder = new StringBuilder();
        int hifenIndex = getHifenIndex(unformattedPhone.length());

        for (int index = 0; index < unformattedPhone.length(); index++) {
            char phoneDigit = unformattedPhone.charAt(index);
            builder.append(phoneDigit);

            // insert first '('
            if (index == INITIAL_PARENTHESES_INDEX) {
                builder.insert(0, "(");
            }

            // insert final ')' + ' ' for area code
            else if (index == CLOSE_PARENTHESES_INDEX) {
                builder.insert(CLOSE_PARENTHESES_INSERTION_INDEX, ")");
                builder.insert(AREA_CODE_INDEX, " ");
            }

            else if (index == hifenIndex) {
                builder.append("-");
            }
        }

        return builder.toString();
    }

    public static String turnFormattedPhoneNumberToRawString(String formattedPhoneNumber) {
        return formattedPhoneNumber
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll(" ", "")
                .replaceAll("-", "");
    }

    private static int getHifenIndex(int phoneNumberLength) {
        if (phoneNumberLength >= HIFEN_MIN_RANGE_FOR_PHONE
                && phoneNumberLength <= HIFEN_MAX_RANGE_FOR_PHONE)
            return HIFEN_INDEX_FOR_PHONE;
        else if (phoneNumberLength > HIFEN_MAX_RANGE_FOR_PHONE
                && phoneNumberLength <= REGULAR_PHONE_LONG_NUMBER_LENGTH)
            return HIFEN_INDEX_FOR_LONG_PHONE;

        return -1;
    }

    public static boolean isPhoneNumberValid(String phone) {
        String unformattedPhone = turnFormattedPhoneNumberToRawString(phone);

        return unformattedPhone.length() == REGULAR_PHONE_NUMBER_LENGTH ||
                unformattedPhone.length() == REGULAR_PHONE_LONG_NUMBER_LENGTH;
    }

    public static boolean validateEmailAdress(String email) {
        String regexForMail =
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+" +
                        "/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\" +
                        "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\" +
                        "x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a" +
                        "-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:" +
                        "[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][" +
                        "0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4" +
                        "][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?" +
                        ":[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a" +
                        "\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-" +
                        "\\x7f])+)\\])";

        return email.matches(regexForMail);
    }
}
