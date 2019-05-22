package br.banco.services.fund.domain;


public  enum ClassConfiguration {

    SCREEN_CLASS("screen",10),
    FUND_CLASS("fund",10),
    MOREINFO_CLASS("moreInfo",3),
    INFO_CLASS("info",2),
    DONWNLOAD_CLASS("downInfo",2);

    ClassConfiguration(String name, int size) {
        this.ClassName = name;
        this.ClassSize = size;
    }

    public String ClassName;
    public int ClassSize;

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getClassSize() {
        return ClassSize;
    }

    public void setClassSize(int classSize) {
        ClassSize = classSize;
    }
}
