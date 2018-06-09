package com.adenilson.testeandroid.networking.webservices.contact;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class CellResponse {

    private Integer id;
    private Integer type;
    private String message;
    private boolean hidden;
    private Integer show;
    private boolean required;

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Integer getShow() {
        return show;
    }

    public boolean isRequired() {
        return required;
    }
}
