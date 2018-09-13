package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nataliafavero on 12/09/18.
 */

public class ScreenResponse {

    @SerializedName("screen")
    @Expose
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }
}
