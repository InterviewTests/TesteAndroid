package br.com.ibm.santander.wallacebaldenebre.model;

public class Info {
    private String name;
    private String data;

    public Info() {
    }

    public Info(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public Info(String name) {

    }

    public String getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}