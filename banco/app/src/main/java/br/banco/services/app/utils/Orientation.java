package br.banco.services.app.utils;

public class Orientation {

    int orientation;
    int horizontal;
    int vertical;
    int blok;

    public int getOrientation() {
       // int currentOrientation = getResources().getConfiguration().orientation;
        return orientation;
    }

    public void setOrientation(int orientation) {
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        this.orientation = orientation;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }



    public void setBlok(int blok) {
        /*
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
            */

        this.blok = blok;
    }
}
