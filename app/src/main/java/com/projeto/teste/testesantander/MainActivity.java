package com.projeto.teste.testesantander;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private Investimento invest = new Investimento();
    private String jResult;

    public String getjResult() {
        return jResult;
    }

    public void setjResult(String str) {
        jResult = str;
    }


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.Investimento) {
                mTextMessage.setText("");
                final ImportStringJson asyncTask = new ImportStringJson(MainActivity.this);
                asyncTask.delegate = MainActivity.this;
                asyncTask.execute();
            } else {
                if (item.getItemId() == R.id.Contato) {
                    mTextMessage.setText("Não implementado...");
                } else {
                    return false;
                }
            }
            return true;
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mTextMessage = (TextView) findViewById(R.id.message);
            mTextMessage.setMovementMethod(new ScrollingMovementMethod());

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        @Override
        public void processFinish(String output) {
            //Here you will receive the result fired from async class
            //of onPostExecute(result) method.
            setjResult(output);
            //String tt = getjResult();

            showJson(getjResult());
            //String jr = getjResult();
            //invest = getInvestimento(tt);

            /// Toast.makeText(getApplicationContext(), "String retrived:" + tt, Toast.LENGTH_SHORT).show();
        }

        //Retorna objeto investimento as informações retornadas do JSON
        public Investimento getInvestimento(String jsonString) {

            Investimento invest = new Investimento();
            try {
                JSONObject investimentoJason = new JSONObject(jsonString);
                invest.setTelaJson(investimentoJason.getJSONObject("screen"));
                invest.setTitulo(invest.getTelaJson().getString("title"));
                invest.setNomeFundo(invest.getTelaJson().getString("fundName"));
                invest.setOQueE(invest.getTelaJson().getString("whatIs"));
                invest.setDefinicao(invest.getTelaJson().getString("definition"));
                invest.settituloRisco(invest.getTelaJson().getString("riskTitle"));
                invest.setrisco(invest.getTelaJson().getInt("risk"));
                invest.settituloInfo(invest.getTelaJson().getString("infoTitle"));
                String mI = invest.getTelaJson().getString("moreInfo");
                JSONObject moreInfoJsonObj = new JSONObject(mI);
                invest.setmoreInfoJson(moreInfoJsonObj);
                invest.setyearJson(moreInfoJsonObj.getJSONObject("year"));
                invest.setmonthJson(moreInfoJsonObj.getJSONObject("month"));
                invest.set12monthsJson(moreInfoJsonObj.getJSONObject("12months"));

                /// for (int i= 0; i<moreInfoArr.length(); i++ ){

                /// }


            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }
            return invest;
        }

        private String fillLeftBlank(String s, int len) {
            String sl = s;
            int nSp = (len - s.length());
            for (int i = 0; i < nSp; i++) {
                sl = " " + sl;
            }
            return sl;
        }

        public void showJson(String str) {

            String jr = getjResult();
            invest = getInvestimento(jr);
            int gRisco = invest.getrisco();
            String sgRisco = String.valueOf(gRisco);
            /// mTextMessage.
            mTextMessage.setBackgroundColor(rgb(255, 255, 255));
            mTextMessage.setTextSize(12);
            mTextMessage.setText("Investimento");
            mTextMessage.append("\n\n");
            mTextMessage.append(invest.getTitulo());
            mTextMessage.append("\n\n\n");
            mTextMessage.setTextSize(18);
            mTextMessage.append(invest.getNomeFundo());
            mTextMessage.append("\n\n\n");
            mTextMessage.append(invest.getOQueE());
            mTextMessage.append("\n\n");
            mTextMessage.append(invest.getDefinicao());
            mTextMessage.append("\n\n\n");
            mTextMessage.append(invest.gettituloRisco());
            mTextMessage.append("\n\n");
            mTextMessage.append("---<" + sgRisco + ">---" + "\n\n\n");
            mTextMessage.append(invest.gettituloInfo());
            mTextMessage.append("\n");
            ///mTextMessage.setTextAppearance();
            mTextMessage.append("\t\t\t\t\t             Fundo\t\t\t\t\t\t   CDI");
            mTextMessage.append("\n");
            try {
                String mesFund = invest.getmonthJson().getString("fund");
                mesFund = fillLeftBlank(mesFund, 10);
                String mesCDI = invest.getmonthJson().getString("CDI");
                mesCDI = fillLeftBlank(mesCDI, 10);
                String sLinha = "No mês             " + mesFund + "%           " + mesCDI + "%";
                mTextMessage.append(sLinha);
                mTextMessage.append("\n");
                String anoFund = invest.getyearJson().getString("fund");
                anoFund = fillLeftBlank(anoFund, 10);
                String anoCDI = invest.getyearJson().getString("CDI");
                anoCDI = fillLeftBlank(anoCDI, 10);
                mTextMessage.append("No Ano          " + anoFund + "%         " + anoCDI + "%");
                mTextMessage.append("\n");
                String v12mesesFund = invest.get12monthsJson().getString("fund");
                v12mesesFund = fillLeftBlank(v12mesesFund, 10);
                String v12mesesCDI = invest.get12monthsJson().getString("CDI");
                v12mesesCDI = fillLeftBlank(v12mesesCDI, 10);
                mTextMessage.append("12 meses        " + v12mesesFund + "%         " + v12mesesCDI + "%");
                mTextMessage.append("\n\n\n");
            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }
            // mTextMessage.append(invest.gettituloRisco());
            // mTextMessage.append("\n\n\n");
            //mTextMessage.append("---< " + sgRisco + " >---" + "\n\n\n");
            //mTextMessage.append(invest.gettituloInfo());

        }


    }

