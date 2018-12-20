package com.seletiva.santander.investment.Utils;

public class StringUtils {
    public static final int REGULAR_PHONE_NUMBER_LENGTH = 10;
    public static final int REGULAR_PHONE_LONG_NUMBER_LENGTH = 11;
    private static final int AREA_CODE_INDEX = 1;
    private static final int HIFEN_INDEX = 5;
    private static final int HIFEN_INDEX_FOR_LONG_PHONE = 6;

    public static String formatAsPhoneNumber(String unformattedPhone) {
        final int phoneNumberLength = unformattedPhone.length();

        if (phoneNumberLength == REGULAR_PHONE_NUMBER_LENGTH || phoneNumberLength == REGULAR_PHONE_LONG_NUMBER_LENGTH) {
            StringBuilder builder = new StringBuilder();
            int hifenIndex = getHifenIndex(phoneNumberLength);

            for (int index = 0; index < unformattedPhone.length(); index++) {
                char phoneDigit = unformattedPhone.charAt(index);
                builder.append(phoneDigit);

                if (index == AREA_CODE_INDEX) {
                    builder.append(" ");
                } else if (index == hifenIndex) {
                    builder.append("-");
                }
            }

            return builder.toString();
        }

        return unformattedPhone;
    }

    public static String turnFormattedPhoneNumberToRawString(String formattedPhoneNumber) {
        return formattedPhoneNumber
                .replaceAll(" ", "")
                .replaceAll("-", "");
    }

    private static int getHifenIndex(int phoneNumberLength) {
        return (phoneNumberLength == REGULAR_PHONE_NUMBER_LENGTH) ? HIFEN_INDEX : HIFEN_INDEX_FOR_LONG_PHONE;
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
