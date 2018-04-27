package io.github.pierry.better_call_me.common;

import io.github.pierry.better_call_me.domain.Cell;

public class ContactHelper {

  private static final String EMAIL = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";
  private static final String PHONE = "(\\+\\d( )?)?([-( ]\\d{3}[-) ])( )?\\d{3}-\\d{4}";

  public boolean required(Cell cell, String text) {
    return cell.isRequired() && text.isEmpty();
  }

  public boolean validField(Cell cell, String text) {
    if (cell.getTypefield() == null) {
      return true;
    }
    switch (cell.getTypefield()) {
      case text:
        boolean isValid = !text.isEmpty();
      case email:
        return isEmail(text);
      case telNumber:
        return isPhoneNumber(text);
      default:
        return true;
    }
  }

  private boolean isEmail(String email) {
    return email.matches(EMAIL);
  }

  private boolean isPhoneNumber(String phone) {
    return true;
  }
}
