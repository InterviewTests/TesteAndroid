package lzacheu.com.br.santanderinvestimento.util;

import java.util.regex.Pattern;

/**
 * Created by luiszacheu on 21/06/18.
 */
public class ValidatorUtils {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean validationEmail(String email){
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validationText(String text){
        return text != null && !text.isEmpty();
    }
}
