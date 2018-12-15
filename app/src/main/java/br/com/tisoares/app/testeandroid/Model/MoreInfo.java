package br.com.tisoares.app.testeandroid.Model;

/**
 * Created by TIAGO SOARES on 14/12/2018.
 */
public class MoreInfo {

    private Periodo month;
    private Periodo year;
    private Periodo _12months;

    public MoreInfo() {

    }

    public Periodo getMonth() {
        return month;
    }

    public void setMonth(Periodo month) {
        this.month = month;
    }

    public Periodo getYear() {
        return year;
    }

    public void setYear(Periodo year) {
        this.year = year;
    }

    public Periodo get_12months() {
        return _12months;
    }

    public void set_12months(Periodo _12months) {
        this._12months = _12months;
    }
}
