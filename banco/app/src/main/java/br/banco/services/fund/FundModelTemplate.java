package br.banco.services.fund;

import android.util.Log;
import java.util.ArrayList;

public class FundModelTemplate {

    ArrayList<String[][][]> listArrayOptions = new ArrayList<String[][][]>();

    // LABELS

    private String NameLabel;
    private String MonthLabel;
    private String TimeLabel;

    // VALORES

    private String Name;
    private String Month;
    private String Time;


    public FundModelTemplate(){

        //showListOptionsTemplate();

    }


    // valores

    public String getName() {
        return Name;
    }

    public void setName(int name) {
        Name = listMoreInfoTemplate[name][0][1] ;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = listMoreInfoTemplate[month][1][1];
    }

    public String getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = listMoreInfoTemplate[time][2][1];
    }


    // labels


    public String getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(int nameLabel) {
        NameLabel = listMoreInfoTemplate[nameLabel][0][0] ;
    }

    public String getMonthLabel() {
        return MonthLabel;
    }

    public void setMonthLabel(int monthLabel) {
        MonthLabel = listMoreInfoTemplate[monthLabel][1][0];
    }

    public String getTimeLabel() {
        return TimeLabel;
    }

    public void setTimeLabel(int timeLabel) {
        TimeLabel = listMoreInfoTemplate[timeLabel][2][0];
    }


    // lista todos os fundos


    public void showListOptionsTemplate(){


        for(int count = 0; count < listMoreInfoTemplate.length; count ++){

            Log.d("FUND","FUNDOS / showListOptionsTemplate => ["+count+"] " );

            Log.d("FUND","NOME =  " + listMoreInfoTemplate[count][0][0] + " : " +listMoreInfoTemplate[count][0][1] );
            Log.d("FUND","MES = " + listMoreInfoTemplate[count][1][0] + " : " +listMoreInfoTemplate[count][1][1] );
            Log.d("FUND","12MESES =  " + listMoreInfoTemplate[count][2][0] + " : " +listMoreInfoTemplate[count][2][1] );
            Log.d("FUND"," ----------------------------------------- ");

            //  Log.d("FUND","START =  "+count+" = " + listOptions[count][0][1]);


        }

    }

    // detalha um fumdo

    public void showListOptionsObjectTemplate(FundModelTemplate fd){


        for(int count = 0; count < listMoreInfoTemplate.length; count ++){

            Log.d("FUND","FUNDOS / showListOptionsObjectTemplate => ["+count+"] " );

            Log.d("FUND","NOME =  " + fd.getNameLabel() + " : " +fd.getName() );
            Log.d("FUND","MES = " + fd.getMonthLabel() + " : " +fd.getMonth() );
            Log.d("FUND","12MESES =  " + fd.getTimeLabel() + " : " +fd.getTime() );
            Log.d("FUND"," ----------------------------------------- ");

            //  Log.d("FUND","START =  "+count+" = " + listOptions[count][0][1]);


        }

    }


    public void putListOptionsObjectTemplate(int count){

        setNameLabel(count);
        setMonthLabel(count);
        setTimeLabel(count);

        setName(count);
        setMonth(count);
        setTime(count);

        Log.d("FUND","FUNDOS / putListOptionsObjectTemplate() => ["+count+"] " );

        Log.d("FUND","NOME =  " + getNameLabel() + " : " + getName() );
        Log.d("FUND","MES = " + getMonthLabel() + " : " + getMonth() );
        Log.d("FUND","12MESES =  " + getTimeLabel() + " : " + getTime() );
        Log.d("FUND"," ----------------------------------------- ");


    }



    // template 01 -- LIST

    //static final String listMoreInfoTemplate[][][] =
     public static String listMoreInfoTemplate[][][] =
            { //0

                    { //0
                            { "title", "Zeus ZERO Plus Multimercado" },
                            { "month","0.1" },
                            { "12months","0.9" }
                    },
                    { //1
                            { "title", "Cronus UM Dinâmico Plus Multimercado Internacional " },
                            { "month","1.1" },
                            { "12months","1.9" }
                    },
                    { //2
                            { "title", "Hades DOIS Dinâmico Plus Multimercado" },
                            { "month","3.1" },
                            { "12months","2.9" }
                    },
                    { //3
                            { "title", "Herculos TRES Dinâmico Plus Debentures Incentivadas Renda Fixa " },
                            { "month","3.1" },
                            { "12months","3.9" }
                    },
                    { //4
                            { "title", "Zeus QUATRO Plus Multimercado" },
                            { "month","4.1" },
                            { "12months","4.9" }
                    },
                    { //5
                            { "title", "Cronus CINCO Dinâmico Plus " },
                            { "month","5.1" },
                            { "12months","5.9" }
                    },
                    { //6
                            { "title", "Hades SEIS Dinâmico Plus Multimercado" },
                            { "month","6.1" },
                            { "12months","6.9" }
                    },
                    { //7
                            { "title", "Herculos SETE Dinâmico Plus " },
                            { "month","7.1" },
                            { "12months","7.9" }
                    },
                    { //8
                            { "title", "Zeus OITO Dinâmico Plus Multimercado" },
                            { "month","8.1" },
                            { "12months","8.9" }
                    },
                    { //9
                            { "title", "Cronus NOVE Fundo Dinâmico Plus Multimercado" },
                            { "month","9.1" },
                            { "12months","9.9" }
                    },
                    { //10
                            { "title", "Hades DEZ Fundo Dinâmico Plus " },
                            { "month","10.1" },
                            { "12months","10.9" }
                    },
                    { //11
                            { "title", "Herculos ONZE Fundo Dinâmico Plus " },
                            { "month","11.1" },
                            { "12months","11.9" }
                    },
                    { //12
                            { "title", "Zeus DOZE Dinâmico Plus Multimercado" },
                            { "month","12.1" },
                            { "12months","12.9" }
                    },
                    { //13
                            { "title", "Cronus TREZE Fundo Dinâmico Plus " },
                            { "month","13.1" },
                            { "12months","13.9" }
                    },
                    { //14
                            { "title", "Hades QUATORZE Fundo Dinâmico Plus Multimercado" },
                            { "month","14.1" },
                            { "12months","14.9" }
                    },
                    { //15
                            { "title", "Herculos QUINZE Fundo Dinâmico Plus " },
                            { "month","15.1" },
                            { "12months","15.9" }
                    }


            };

    public String[][][][] createListInfoTemplate(){

        String[][][][] listInfoTemplate2 ={};

        for(int count = 0; count < listMoreInfoTemplate.length; count ++) {

            String[][][][]  listInfoTemplate =
                    {
                            { //0 -> nome

                                    { //0
                                            {"title", "" + count},
                                            {"name", "Taxa de administração"},
                                            {"data", "0,50%"},

                                    },
                                    { //1
                                            {"title", ""+ count},
                                            {"name", "Aplicação inicial"},
                                            {"data", "R$ 10.000,00"}
                                    },
                                    { //2
                                            {"title", ""+ count},
                                            {"name", "Movimentação mínima"},
                                            {"data", "R$ 1.000,00"}
                                    },
                                    { //3
                                            {"title", ""+ count},
                                            {"name", "Saldo mínimo"},
                                            {"data", "R$ 5.000,00"}
                                    },
                                    { //4
                                            {"title", ""+ count},
                                            {"name", "Resgate (valor bruto)"},
                                            {"data", "D+0"}
                                    },
                                    { //5
                                            {"title", ""+ count},
                                            {"name", "Cota (valor bruto)"},
                                            {"data", "D+1"}
                                    },
                                    { //6
                                            {"title", ""+ count},
                                            {"name", "Pagamento (valor bruto)"},
                                            {"data", "D+2"}
                                    }

                            }


                    };
        } //for

      return listInfoTemplate2;
    }

    // template 02




    static final String listMoreInfoDetailsTemplate[][][][] =
            {
                    { //01
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { //02
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 03
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 04
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }

                    },
                    { //05
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { //06
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 07
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 08
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }

                    },
                    { //09
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { //10
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 11
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 12
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }

                    },
                    { //13
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { //14
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 15
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }
                    },
                    { // 16
                            {  //month
                                    { "fund", "0.3" },
                                    { "CDI","0.3" }
                            },
                            {  //year
                                    { "fund", "13.01" },
                                    { "CDI","12.08" }
                            }
                            ,
                            {  //12months
                                    { "fund", "17.9" },
                                    { "CDI","17.6" }
                            }

                    }
            };


    public static String[][][] getListMoreInfoTemplate() {
        return listMoreInfoTemplate;
    }

    public static String[][][][] getListMoreInfoDetailsTemplate() {
        return listMoreInfoDetailsTemplate;
    }


    public final String SAVE_DATA_CONTACT ="{'cells':[{'id':1,'type':2,'message':'Olá,primeiroseapresentecomoseunome:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'Nomecompleto','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'Gostariadecadastrarmeuemail','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";

    public final String SAVE_DATA_FUND_ = "{'screen':{'title':'Fundosdeinvestimento','fundName':'VinciValoremFIMultimercado','whatIs':'Oqueé?','definition':'OFundotemporobjetivoproporcionaraosseuscotistasrentabilidadenolongoprazoatravésdeinvestimentos.','riskTitle':'Grauderiscodoinvestimento','risk':4,'infoTitle':'Maisinformaçõessobreoinvestimento','moreInfo':{'month':{'fund':0.3,'CDI':0.3},'year':{'fund':13.01,'CDI':12.08},'12months':{'fund':17.9,'CDI':17.6}},'info':[{'name':'Taxadeadministração','data':'0,50%'},{'name':'Aplicaçãoinicial','data':'R$10.000,00'},{'name':'Movimentaçãomínima','data':'R$1.000,00'},{'name':'Saldomínimo','data':'R$5.000,00'},{'name':'Resgate(valorbruto)','data':'D+0'},{'name':'Cota(valorbruto)','data':'D+1'},{'name':'Pagamento(valorbruto)','data':'D+2'}],'downInfo':[{'name':'Essenciais','data':null},{'name':'Desempenho','data':null},{'name':'Complementares','data':null},{'name':'Regulamento','data':null},{'name':'Adesão','data':null}]}}";
}