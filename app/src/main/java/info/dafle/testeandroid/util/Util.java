package info.dafle.testeandroid.util;

import android.text.TextUtils;

public class Util {

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidPhoneNumber(CharSequence target) {
        int lengt_phone = target.toString()
                .replace("(","")
                .replace(")","")
                .replace("-","")
                .replace(" ","")
                .length();
        return lengt_phone == 10 || lengt_phone == 11;
    }
}
