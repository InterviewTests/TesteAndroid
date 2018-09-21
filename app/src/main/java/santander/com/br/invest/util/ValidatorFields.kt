package santander.com.br.invest.util

import android.support.v4.util.PatternsCompat
import java.util.regex.Pattern

object ValidatorFields {

  fun isInvalidName(name: String?): Boolean = name.isNullOrEmpty()

  fun isPhoneInvalid(phone: String?): Boolean {
    val phonePattern = Pattern.compile(("(\\+[0-9]+[\\- \\.]*)?"
        + "(\\([0-9]+\\)[\\- \\.]*)?"
        + "([0-9][0-9\\- \\.]+[0-9])"))

    return phone.isNullOrEmpty() || !phonePattern.matcher(phone).matches()
  }

  fun isInvalidEmail(email: String?): Boolean = email.isNullOrEmpty()
      || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}
