package lzacheu.com.br.santanderinvestimento.model.fund;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class MoreInfo {

    @SerializedName("month")
    private MoreInfoDetail month;

    @SerializedName("year")
    private MoreInfoDetail year;

    @SerializedName("12months")
    private MoreInfoDetail twelveMonths;

}
