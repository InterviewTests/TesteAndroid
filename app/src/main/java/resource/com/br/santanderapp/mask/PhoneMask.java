package resource.com.br.santanderapp.mask;

public class PhoneMask {
    public String phoneMask(String phone) {
        return phone
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    public String unMaskPhone(String phone) {
        return phone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
