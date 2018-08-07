package resource.com.br.santanderapp.model.investmentModel;

import com.google.gson.annotations.SerializedName;

public class Investment{

    @SerializedName("screen")
    private Screen screen;



    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
