package io.github.pierry.better_call_me.domain.viewmodels;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {

  private Period month;
  private Period year;
  @SerializedName("12months") private Period twelveMonths;

  public Period getMonth() {
    return month;
  }

  public void setMonth(Period month) {
    this.month = month;
  }

  public Period getYear() {
    return year;
  }

  public void setYear(Period year) {
    this.year = year;
  }

  public Period getTwelveMonths() {
    return twelveMonths;
  }

  public void setTwelveMonths(Period twelveMonths) {
    this.twelveMonths = twelveMonths;
  }
}
