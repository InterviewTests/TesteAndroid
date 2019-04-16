package br.com.ricardo.testeandroid.model;

public class DownInfo {

    private String name;
    private String data;

    public DownInfo(String name, String data) {
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
