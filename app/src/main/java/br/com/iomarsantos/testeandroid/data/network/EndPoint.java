package br.com.iomarsantos.testeandroid.data.network;

import br.com.iomarsantos.testeandroid.BuildConfig;

public final class EndPoint {

    public static final String ENDPOINT_CELLS = BuildConfig.BASE_URL + "/cells.json";

    public static final String ENDPOINT_FUND = BuildConfig.BASE_URL + "/fund.json";

    private EndPoint() {

    }

}
