package br.banco.services.fund.domain.national;

import android.os.Parcel;
import android.os.Parcelable;

public class ScreenFundParcel implements Parcelable {

    public static final String ID_KEY = "id";
    public static final String KEY_FAVORITE = "key-favorite";

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
    public void writeToParcel (Parcel dest, int flags) {

        dest.writeLong (id);
        dest.writeString (Header);
        dest.writeString (Body);
        dest.writeString (Footer);
    }

    public ScreenFundParcel(Parcel in) {

        this.id = in.readInt();
        this.Header = in.readString();
        this.Body = in.readString();
        this.Footer = in.readString();
    }

    public static final Creator <ScreenFundParcel> CREATOR = new Creator <ScreenFundParcel> () {
        public ScreenFundParcel createFromParcel (Parcel source) {
            return new ScreenFundParcel(source);
        }

        @Override
        public ScreenFundParcel[] newArray (int size) {
            return new ScreenFundParcel[size];
        }
    };

}
