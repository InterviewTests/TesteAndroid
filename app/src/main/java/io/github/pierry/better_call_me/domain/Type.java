package io.github.pierry.better_call_me.domain;

public enum Type {

  field(1), text(2), image(3), checkbox(4), send(5);

  private int value;

  Type(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
