package br.com.tisoares.app.testeandroid.Model;

/**
 * Created by TIAGO SOARES on 14/12/2018.
 */
public class Info {

    private String name;
    private String data;

    public Info(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
