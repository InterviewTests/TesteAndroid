package santander.com.br.invest.utils

import junit.framework.TestCase.assertTrue
import org.junit.Test
import santander.com.br.invest.util.ValidatorFields

class UtilsTest {

  val EMPTY: String = ""
  val NULL: String? = null
  val NAME: String = "Teste Android"
  val VALID_EMAIL: String = "teste@android.com"
  val INVALID_EMAIL: String = "test@"
  val INVALID_PHONE = "12"
  val VALID_PHONE_1 = "(11)12345678"
  val VALID_PHONE_2 = "(11)123456789"

  @Test
  fun `fields are valid`() {
    assertTrue(`email is valid`(VALID_EMAIL))
    assertTrue(`phone is valid`(VALID_PHONE_1))
    assertTrue(`phone is valid`(VALID_PHONE_2))
    assertTrue(`name is valid`(NAME))
  }

  @Test
  fun `fields are invalid`() {
    assertTrue(`email is invalid`(INVALID_EMAIL))
    assertTrue(`phone is invalid`(INVALID_PHONE))
    assertTrue(`name is invalid`(EMPTY))
    assertTrue(`name is invalid`(NULL))
  }

  private fun `email is valid`(email: String): Boolean {
    return !ValidatorFields.isInvalidEmail(email)
  }

  private fun `phone is valid`(phone: String): Boolean {
    return !ValidatorFields.isPhoneInvalid(phone)
  }

  private fun `name is valid`(name: String?): Boolean {
    return !ValidatorFields.isInvalidName(name)
  }

  private fun `email is invalid`(email: String): Boolean {
    return ValidatorFields.isInvalidEmail(email)
  }

  private fun `phone is invalid`(phone: String): Boolean {
    return ValidatorFields.isPhoneInvalid(phone)
  }

  private fun `name is invalid`(name: String?): Boolean {
    return ValidatorFields.isInvalidName(name)
  }

}