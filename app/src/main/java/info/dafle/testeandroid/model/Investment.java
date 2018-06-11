package info.dafle.testeandroid.model;

import android.graphics.Color;

public class Investment {

    private String title;
    private String firstValue;
    private String secondValue;
    private boolean showImageDownload = false;
    private int textColor = Color.BLACK;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public boolean isShowImageDownload() {
        return showImageDownload;
    }

    public void setShowImageDownload(boolean showImageDownload) {
        this.showImageDownload = showImageDownload;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
