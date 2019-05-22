package br.banco.services.fund.domain;

public enum Configclass {


    SCREEN_CLASS("screen",10),
    FUND_CLASS("fund",10),
    MOREINFO_CLASS("moreInfo",3),
    INFO_CLASS("info",2),
    DONWNLOAD_CLASS("downInfo",2);

    public String Name;
    public int Size;

    Configclass(String name, int size) {
        this.Name = name;
        this.Size = size;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

}
