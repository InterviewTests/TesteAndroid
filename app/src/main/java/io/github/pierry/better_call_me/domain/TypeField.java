package io.github.pierry.better_call_me.domain;

public enum TypeField {

  text(1), telNumber(2), email(3), number("telnumber");

  private int value;
  private String textValue;

  TypeField(int value) {
    this.value = value;
  }

  TypeField(String textValue) {
    this.textValue = textValue;
  }

  public int getValue() {
    return value;
  }

  public String getStringValue() {
    return textValue;
  }
}
