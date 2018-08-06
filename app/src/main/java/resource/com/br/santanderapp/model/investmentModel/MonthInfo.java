package resource.com.br.santanderapp.model.investmentModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthInfo {

    @SerializedName("fund")
    private double fund;

    @SerializedName("CDI")
    private double cdi;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCdi() {
        return cdi;
    }

    public void setCdi(double cdi) {
        this.cdi = cdi;
    }
}
