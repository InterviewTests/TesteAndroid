package resource.com.br.santanderapp.model.investmentModel;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {

    @SerializedName("month")
    private MonthInfo month;

    @SerializedName("year")
    private YearInfo year;

    @SerializedName("12months")
    private TwelveMonth twelveMonth;

    public MonthInfo getMonth() {
        return month;
    }

    public void setMonth(MonthInfo month) {
        this.month = month;
    }

    public YearInfo getYear() {
        return year;
    }

    public void setYear(YearInfo year) {
        this.year = year;
    }

    public TwelveMonth getTwelveMonth() {
        return twelveMonth;
    }

    public void setTwelveMonth(TwelveMonth twelveMonth) {
        this.twelveMonth = twelveMonth;
    }
}
