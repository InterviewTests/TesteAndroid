package br.banco.services.fund.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.R;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.data.template.FromFund;
import br.banco.services.fund.data.template.FromScreen;
import br.banco.services.fund.domain.national.ScreenFund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;

/**

@rules:

    CLASS BASE: Fund
    CLASSE RELATORIO: ScreenFundTemplate OU ScreenFund
    DEPENDE DE: FromScreen;


 @param: String Name = screen
 @param: String Data = JSON ;
 @param: String Type = Text;
 @param: int Size = 10;
 @param: int Chars = 1000;
 @param: int Bytes = null;


 */

public class LoadScreen extends AsyncTask<String, String, String> {

    public ILoadTask delegate = null;
    private String STATUS = "error";

    public Context context;

    public FromScreen screenRule;
    public ScreenFundTemplate screenTemplate;
    public ReactAplication RX = new ReactAplication();

    public List<ScreenFundTemplate> listaTemplate = new ArrayList<ScreenFundTemplate>();
    public List<Object> listaExport = new ArrayList<Object>();

    public ArrayList listScreen = new ArrayList<>();
    public ArrayList listFund = new ArrayList<>();
    public ArrayList listMoreinfo = new ArrayList<>();
    public ArrayList listInfo = new ArrayList<>();
    public ArrayList listDownInfo = new ArrayList<>();

    public int  tScreen = 0; // < - carregar de pacote
    public int  tFund = 0;
    public int  tMoreingo = 0;
    public int  tInfo = 0;
    public int  tDownInfo = 0;

    private int SHARED_PREFERENCES = 1;
    private String JSON_CARREGADO = null;


    public LoadScreen(Context c) {

        this.screenRule = new FromScreen();
       // this.screenTemplate = new ScreenFundTemplate(Object); //

        this.SHARED_PREFERENCES = 1;
        this.JSON_CARREGADO = screenRule.LOAD_DATA_DEVICE;

        this.context = c;


        if(context == null){

           // delegate.processFinish(STATUS);
           // this.cancel(true);

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //this.cancel(true);
        // RX.onNext("Conectando..." );

    }

    @Override
    protected String doInBackground(String... loadValues) {

        String textValues = null;
        String urlServer = loadValues[0];

        textValues = "[ DADOS JSON AQUI - OBJETO]";

        try {

            if(SHARED_PREFERENCES == 1){




                Log.e("FUND",JSON_CARREGADO);


































                STATUS = "success";
            }else{

               RX.onNext("Nao foi possivel carregar preferencias ");
               STATUS = "error";
            }

        } catch (Exception e) {
            RX.onError(e);
            delegate.convertFinish(null,STATUS);
            e.printStackTrace();

        }

        return textValues;
    }

    protected void onProgressUpdate(String... progress) {
      //  RX.onNext("onProgressUpdate=" + progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        RX.onNext("onPostExecute->" + STATUS + " / result->"+result);
       // delegate.convertFinish(listaExport , STATUS);

    }

}

/**
*
* Sequencia de carregamento:
*
*  FUND, CONTACT:
*  View> Presenter-> Model> Screen> Rules>  dbase[Json / Share / dbase]
*  Rules[...] <Model <Presenter[view] <View[...]
*
*/
