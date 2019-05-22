package br.banco.services.fund.interactor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import br.banco.services.R;
import br.banco.services.app.utils.JsonConvert;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.data.template.FromFund;
import br.banco.services.fund.data.template.FromScreen;

//import org.json.JSONException;
//import org.json.JSONObject;


public class LoadJson extends AppCompatActivity {

    public ReactAplication RX = new ReactAplication();
    public JSONObject jsonObject;
    private String TAG = "FUND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);

            Read();
         //   Update();
          //  Debug();
           // Test();


    }


    public void Read(){

        FromScreen fromScreen = new FromScreen();
        String jsonString = fromScreen.LOAD_DATA_DEVICE;
        JsonConvert convert = new JsonConvert();


        // OK -------------------- converter para OBJETO

        //0  screen = OBJ
       // convert.searchLevelZero(jsonString, "screen", 2000); //moreInfo


        // -------------------- converter para OBJETO

        // 1 //downInfo = OBJ
        // convert.searchLevelOne(jsonString, "moreInfo", 2000); // screen


        // -------------------- converter para OBJETO

        // 3 //downInfo = OBJ
       //  convert.searchLevelTree(jsonString, "moreInfo", "month", 2000); //


        //  -------------------- converter para ARRAY


        // 2 info, downInfo = Array
        // convert.searchLevelOneArray(jsonString, "info", 2000); // moreInfo
        // convert.searchLevelOneArray(jsonString, "downInfo", 2000); // moreInfo


        // 3 //info = Array
         convert.searchLevelTreeArray(jsonString, "info","name", 2000);
         //convert.searchLevelTreeArray(jsonString, "downInfo","data", 2000);

    }

    public void Update(){



    }


    public void Debug(){



    }


    public void Test(){



    }







}


