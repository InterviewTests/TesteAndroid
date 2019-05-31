package br.banco.services.fund.data.local;

/**
 *
 *  configirar como as classes devem exibir dados
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.fund.data.IFundData;

public class FromDatabase extends AppCompatActivity implements IFundData {

    private Context b;
    public String configDesign(Context a){

        this.b = a;
        return "";

    }

    public void getFund(){

    }

    public void checkFund(){


    }

}
