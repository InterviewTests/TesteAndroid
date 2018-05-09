package rrzaniolo.testandroidsantander.network.investment.response;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

import rrzaniolo.testandroidsantander.network.investment.models.Screen;

/**
 * Model for the Investment Api response.
 * */
public class InvestmentResponse {
    //region --- Variable
    @SerializedName("screen")
    private Screen screen;
    public Screen getScreen() {
        return screen;
    }
    public void setScreen(Screen screen) {
        this.screen = screen;
    }
    //endregion

    //region --- Constructors
    public InvestmentResponse() { }

    public InvestmentResponse(Screen screen) {
        this.screen = screen;
    }
    //endregion
}
