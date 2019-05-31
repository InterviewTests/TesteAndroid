package br.banco.services.fund.data.template;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.fund.data.IFundData;

public class FromFund extends AppCompatActivity implements IFundData {


    private Context b;

    public String configDesign(Context a) {
        this.b = a;
        return LOAD_DATA_DEVICE;
    }

    public final String LOAD_DATA_DEVICE = "{'screen':{'title':'Fundosdeinvestimento','fundName':'VinciValoremFIMultimercado','whatIs':'Oqueé?','definition':'OFundotemporobjetivoproporcionaraosseuscotistasrentabilidadenolongoprazoatravésdeinvestimentos.','riskTitle':'Grauderiscodoinvestimento','risk':4,'infoTitle':'Maisinformaçõessobreoinvestimento','moreInfo':{'month':{'fund':0.3,'CDI':0.3},'year':{'fund':13.01,'CDI':12.08},'12months':{'fund':17.9,'CDI':17.6}},'info':[{'name':'Taxadeadministração','data':'0,50%'},{'name':'Aplicaçãoinicial','data':'R$10.000,00'},{'name':'Movimentaçãomínima','data':'R$1.000,00'},{'name':'Saldomínimo','data':'R$5.000,00'},{'name':'Resgate(valorbruto)','data':'D+0'},{'name':'Cota(valorbruto)','data':'D+1'},{'name':'Pagamento(valorbruto)','data':'D+2'}],'downInfo':[{'name':'Essenciais','data':null},{'name':'Desempenho','data':null},{'name':'Complementares','data':null},{'name':'Regulamento','data':null},{'name':'Adesão','data':null}]}}";
    public final String LOAD_DATA_NAME = "screen";
    public final int LOAD_DATA_SIZE = 10;


    public void getFund(){

    }

    public void checkFund(){


    }





}
