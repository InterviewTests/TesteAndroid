package io.github.pierry.better_call_me.domain.viewmodels;

public class Period {

  private double fund;
  private double cdi;

  public String getFund() {
    return String.valueOf(fund) + "%";
  }

  public void setFund(double fund) {
    this.fund = fund;
  }

  public String getCdi() {
    return String.valueOf(cdi) + "%";
  }

  public void setCdi(double cdi) {
    this.cdi = cdi;
  }
}
