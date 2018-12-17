package br.com.tisoares.app.testeandroid.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.view.ContextThemeWrapper;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import br.com.tisoares.app.testeandroid.Config.AppConfig;
import br.com.tisoares.app.testeandroid.Helper.RequestFromServer;
import br.com.tisoares.app.testeandroid.Model.Field;
import br.com.tisoares.app.testeandroid.R;
import br.com.tisoares.app.testeandroid.Utils.Mask;

public class FormActivity extends AppCompatActivity {

    private ArrayList<Field> fieldList;

    private LinearLayout fnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        setTitle(R.string.ttl_contato);

        // List com os Fields Config
        this.fieldList = new ArrayList<Field>();

        fnd = findViewById(R.id.fundo);

        findViewById(R.id.btn_frm_investimento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreInvestimentos();
            }
        });

        solicitaFields();

    }

    /**
     * Abre a Activity FundosActivity
     */
    private void abreInvestimentos() {
        Intent i = new Intent(this, FundosActivity.class);
        startActivity(i);
    }

    /**
     * Solicita as configurações dos fields para o servidor
     */
    private void solicitaFields() {
        new RequestFromServer(this, AppConfig.SVR_CELLS_FORM) {
            @Override
            public void onReceive(String response) {
                recebeCampos(response);
            }

            @Override
            public void onError(String erros) {
                Toast.makeText(FormActivity.this, erros, Toast.LENGTH_LONG).show();
            }
        };
    }

    /**
     *  Recebe o JSONArray como string do servidor e cria o List de Field;
     * @param response String contendo o JSONArray com as infos dos campos
     */
    private void recebeCampos(String response) {
        try {
            JSONObject j = new JSONObject(response);
            JSONArray jsonArray = j.getJSONArray("cells");

            for (int i = 0; i < jsonArray.length(); i++){
                fieldList.add(new Field(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        criarComponentes();
    }

    /**
     * Cria os componentes visuais de acordo com as configs do servidor
     */
    private void criarComponentes() {
        for (Field f:fieldList) {
            switch (f.getType()){
                case text:
                    criaTxt(f);
                    break;
                case field:
                    criaEdit(f);
                    break;
                case image:
                    criaImage(f);
                    break;
                case checkbox:
                    criaCheckBox(f);
                    break;
                case send:
                    criaBtn(f);
                    break;
            }
        }
    }

    /**
     * Cria os Botões
     * @param f Objeto Field contendo as props
     */
    @SuppressLint("ResourceType")
    private void criaBtn(Field f) {
        int buttonStyle = R.style.Button;
        Button btn = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
        btn.setId(f.getId());
        btn.setText(f.getMessage());
        btn.setVisibility(f.isHidden()? View.INVISIBLE: View.VISIBLE);
        //btn.setBackgroundResource(R.drawable.button_draw);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(32, 32, 32, 32);

        fnd.addView(btn, layoutParams);
    }

    /**
     *  Vai para proxima tela caso os campos estejam válidos
     */
    private void enviar() {
        if (validaCampos()){
            Log.d("FORM", "FOI");
            Intent sucesso = new Intent(this, SucessoActivity.class);
            startActivity(sucesso);
        }else{
            Log.d("FORM", "NAO FOI");
        }
    }

    /**
     * Valida os dados dos campos do formulario
     * @return
     */
    private boolean validaCampos() {
        boolean result = true;
        for(Field fd: fieldList){
            if (fd.getType() == Field.CompType.field){
                if (fd.isRequired()){
                    EditText edt = fnd.findViewById(fd.getId());
                    if (edt.getVisibility() == View.VISIBLE){
                        if( TextUtils.isEmpty(edt.getText())) {
                            edt.setError("Campo obrigatório!");
                            result = false;
                        }else {
                            switch (fd.getTypefield()) {
                                case email:
                                    if (!isValidEmail(edt.getText())) {
                                        edt.setError("Email inválido!");
                                        result = false;
                                    }
                                    break;
                                case telnumber:
                                    if (edt.getText().length() < 14) {
                                        edt.setError("Telefone inválido!");
                                        result = false;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Cria os CheckBox
     * @param f Objeto Field contendo as props
     */
    private void criaCheckBox(final Field f) {
        CheckBox chk = new CheckBox(this);
        chk.setId(f.getId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            chk.setTextAppearance(R.style.styleCheckBox);
        }
        chk.setText(f.getMessage());
        chk.setVisibility(f.isHidden()? View.INVISIBLE: View.VISIBLE);
        if (f.getShow() > 0){
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    fnd.findViewById(f.getShow()).setVisibility(isChecked ? View.VISIBLE: View.INVISIBLE);
                }
            });
        }
        chk.setTag(f);
        fnd.addView(chk, getParams(f.getTopSpacing()));
    }

    /**
     * Cria os Images
     * @param f Objeto Field contendo as props
     */
    private void criaImage(Field f) {
        ImageView img = new ImageView(this);
        img.setId(f.getId());
        //img.setim f.getMessage());
        img.setVisibility(f.isHidden()? View.INVISIBLE: View.VISIBLE);
        img.setTag(f);
        fnd.addView(img);
    }

    /**
     * Cria os Texts
     * @param f Objeto Field contendo as props
     */
    private void criaTxt(Field f) {
        int txtStyle = R.style.styleLabel;
        TextView txt = new TextView(new ContextThemeWrapper(this, txtStyle), null, txtStyle);
        txt.setId(f.getId());
        txt.setText(f.getMessage());
        txt.setVisibility(f.isHidden()? View.INVISIBLE: View.VISIBLE);
        txt.setTag(f);
        fnd.addView(txt, getParams(f.getTopSpacing()));
    }

    /**
     * Cria os Edits
     * @param f Objeto Field contendo as props
     */
    //@RequiresApi(api = Build.VERSION_CODES.M)
    private void criaEdit(Field f) {
        EditText edt = new EditText(this);
        edt.setId(f.getId());
        //edt.setTextAppearance(R.style.styleEdit);
        edt.setHint(f.getMessage());
        edt.setVisibility(f.isHidden()? View.INVISIBLE: View.VISIBLE);
        switch (f.getTypefield()){
            case email:
                edt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case telnumber:
                edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                edt.addTextChangedListener(Mask.insert( "(##)#####-####", edt));
                break;
        }
        edt.setTag(f);

        fnd.addView(edt, getParams(f.getTopSpacing()));
    }

    /**
     * Define os parametros do layout
     * @param top Margin top
     * @return
     */
    private LinearLayout.LayoutParams getParams(int top){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, top, 0, 0);
        return layoutParams;
    }

    /**
     * Valida o Email
     * @param target valor para verificar
     * @return
     */
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
