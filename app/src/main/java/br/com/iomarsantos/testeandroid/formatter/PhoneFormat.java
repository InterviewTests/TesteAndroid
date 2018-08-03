package br.com.iomarsantos.testeandroid.formatter;

public class PhoneFormat {
    public String format(String phone) {
        return phone
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    public String remove(String phone) {
        return phone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
