package br.banco.services.app.utils;

import android.util.Log;

public final class CharacterCheck {

    public int isNumber;
    public long isLong;
    public String Text;
    public String Simbol;

    public int getIsNumber(String isNumber) {
        try {

            Integer numberInt = Integer.valueOf(isNumber);
             return numberInt;
        }
        catch (NumberFormatException e) {
            Log.e("CONTACT", "ERROR = " + e.getMessage());
            return 0;
        }
    }


    public long getIsLong(String checkLong) {

        try {

            //Long numberLong = Long.parseLong(checkLong);
            long numberLong = Long.valueOf(checkLong);
           // Log.e("CONTACT", "SUCCESS = " + numberLong);
            return numberLong;
        }
        catch (NumberFormatException e) {
            //Log.e("CONTACT", "ERROR = " + e.getMessage());
            return 0;
        }
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getSimbol() {
        return Simbol;
    }

    public void setSimbol(String simbol) {
        Simbol = simbol;
    }
}
