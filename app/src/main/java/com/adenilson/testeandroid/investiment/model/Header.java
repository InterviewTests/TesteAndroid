package com.adenilson.testeandroid.investiment.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class Header {

    private String title;
    private String fundName;
    private String whatIs;
    private String definition;


    @ParcelConstructor
    public Header(String title, String fundName, String whatIs, String definition) {
        this.title = title;
        this.fundName = fundName;
        this.whatIs = whatIs;
        this.definition = definition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
