package io.github.pierry.better_call_me.domain;

public class Cell {

  private long id;
  private int type;
  private String message;
  private Object typefield;
  private boolean hidden;
  private double topSpacing;
  private Integer show;
  private boolean required;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Type getType() {
    for (Type t : Type.values()) {
      if (t.getValue() == type) {
        return t;
      }
    }
    return null;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public double getTopSpacing() {
    return topSpacing;
  }

  public void setTopSpacing(double topSpacing) {
    this.topSpacing = topSpacing;
  }

  public Integer getShow() {
    return show;
  }

  public void setShow(Integer show) {
    this.show = show;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public TypeField getTypefield() {
    for (TypeField t : TypeField.values()) {
      if (typefield == null) {
        return null;
      }
      if (typefield instanceof Double) {
        if (t.getValue() == Double.valueOf(typefield.toString())) {
          return t;
        }
      }
      if (typefield instanceof String) {
        if (t.getStringValue() == null){
          continue;
        }
        if (t.getStringValue().equals(typefield.toString())) {
          return t;
        }
      }
    }
    return null;
  }

  public void setTypefield(Object typefield) {
    this.typefield = typefield;
  }
}
