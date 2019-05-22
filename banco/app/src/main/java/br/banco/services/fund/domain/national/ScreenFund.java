package br.banco.services.fund.domain.national;

import android.os.Parcel;
import android.os.Parcelable;

public class ScreenFund  {


    int id;
    String Header;
    String Body;
    String Footer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getFooter() {
        return Footer;
    }

    public void setFooter(String footer) {
        Footer = footer;
    }


    //@Override
    public int describeContents () {
        return 0;
    }

    //@Override



}
