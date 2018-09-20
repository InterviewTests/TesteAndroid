package br.com.santander.testeandroid.utils.validators;

public class PhoneValidator {
    public static boolean isEmpty(String phone) {
        return phone.isEmpty();
    }

    public static boolean isInvalid(String phone) {
        return phone.length() < 14 || phone.length() > 15;
    }
}
