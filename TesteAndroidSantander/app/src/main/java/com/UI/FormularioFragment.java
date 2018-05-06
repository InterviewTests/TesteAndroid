package com.UI;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.cerqueira.mellina.testeandroidsantander.R;
import com.entities.Componente;
import com.http.HttpCall;
import com.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FormularioFragment extends Fragment {

    private static final String URLCellsJSON = "https://floating-mountain-50292.herokuapp.com/cells.json";

    private final static int FIELD = 1;
    private final static int TEXT = 2;
    private final static int IMAGE = 3;
    private final static int CHECKBOX = 4;
    private final static int BUTTON = 5;

    private final static String INPUT_TYPE_TEXT = "1";
    private final static String INPUT_TYPE_TEL_NUMBER = "telnumber";
    private final static String INPUT_TYPE_EMAIL = "3";

    private static boolean CRIA_COMPONENTE = true;
    private static boolean ATUALIZA_COMPONENTE = false;

    private ConstraintLayout constraintLayout;
    private List<Componente> componentes;
    private Context context;
    private ReadContatoJSONTask task;
    private EditText editText;

    private List<View> componentesUI = new ArrayList<View>();

    private OnButtonSendListener listener;
    private TextWatcher onChangedListenerText = new TextWatcher() {

        int tamanho_anterior = 0;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            tamanho_anterior = s.length();

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            mascaraTelefoneEdittext(s);
        }

        private void mascaraTelefoneEdittext(Editable s) {
            if (tamanho_anterior < s.length()) {
                if (s.length() == 1) {
                    if (Character.isDigit(s.charAt(0))) {
                        s.insert(0, "(");
                    }
                } else if (s.length() == 3) {
                    s.append(")");
                } else if (s.length() == 8) {
                    s.append("-");
                } else if (s.length() > 13) {

                    if (Character.isDigit(s.charAt(9))) {
                        s.replace(8, 9, s.charAt(9) + "");
                    }
                    s.replace(9, 10, "-");

                }
            }
        }

    };
    private View.OnClickListener onClickListenerButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (entradaDeDadosEValidaEnviar()) {
                if (listener != null) {
                    listener.onSend();
                }
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {

                for (int i = 0; i < componentesUI.size(); i++) {
                    int id = componentesUI.get(i).getId();
                    if (id == 4) {
                        EditText ed = (EditText) componentesUI.get(i);
                        componentes.get(i).setHidden(false);
                        ed.setVisibility(View.VISIBLE);
                        criaEAtualizaDinamicamenteComponentesNaTela(componentes, ATUALIZA_COMPONENTE);
                    }
                }
            } else {
                for (int i = 0; i < componentesUI.size(); i++) {
                    int id = componentesUI.get(i).getId();
                    if (id == 4) {
                        componentes.get(i).setHidden(true);
                        EditText ed = (EditText) componentesUI.get(i);
                        ed.setVisibility(View.INVISIBLE);
                        criaEAtualizaDinamicamenteComponentesNaTela(componentes, ATUALIZA_COMPONENTE);
                    }
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //pega o Contexto da Activity onde o Fragment foi iniciado
        context = getActivity();

        View view = criaERetornaConstraintLayout();

        if (estaConectadoNaInternet()) {
            //Inicia a leitura do JSON
            task = new ReadContatoJSONTask();
            task.execute(URLCellsJSON);
        }

        return view;
    }

    private boolean estaConectadoNaInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private View criaERetornaConstraintLayout() {
        constraintLayout = new ConstraintLayout(context);
        constraintLayout.setId(R.id.constraintLayout);

        constraintLayout.setPadding(context.getResources().getInteger(R.integer.padding_lateral), 0, context.getResources().getInteger(R.integer.padding_lateral), 0);
        return constraintLayout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (task != null) {
            // Cancela a AsyncTask responsavel pela leitura do JSON quando a tela é destruida
            task.cancel(true);
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
///Verifica se a Activity implementou a interface do ButtonSend
        if (!(activity instanceof OnButtonSendListener)) {
            throw new RuntimeException("A activity deve implementar a interface FormularioFragment.OnButtonSendListener");
        }
//Atribui a activity ao listener do ButtonSend
        listener = (OnButtonSendListener) activity;
    }

    private void criaObjetosComponente(List<Componente> componentes, Componente p, JSONObject cell) throws JSONException {
        p.setId(cell.getInt("id"));
        p.setType(cell.getInt("type"));
        if (cell.getString("message") != null)
            p.setMessage(cell.getString("message"));
        if (cell.getString("typefield") != null)
            p.setTypefield(cell.getString("typefield"));
        p.setHidden(cell.getBoolean("hidden"));
        p.setTopSpacing(cell.getDouble("topSpacing"));
        if (cell.getString("show") != null)
            p.setShow(cell.getString("show"));
        p.setRequired(cell.getBoolean("required"));
        componentes.add(p);
    }

    private void criaEAtualizaDinamicamenteComponentesNaTela(List<Componente> s, boolean criaComponente) {

        for (int i = 0; i < s.size(); i++) {

            Componente c = s.get(i);
            int idComponenteAtual = c.getId();
            int idComponenteAnterior = constraintLayout.getId();
            if (idComponenteAtual != 1) {

                //pega o id do ultimo componente que não está escondido
                if (s.get(i - 1).isHidden()) {
                    idComponenteAnterior = s.get(i - 2).getId();
                } else {
                    idComponenteAnterior = s.get(i - 1).getId();
                }
            }

            if (criaComponente) {
                criaComponentesDeAcordoComOTipo(c);
            }

            adicionaComponentesNoLayout(c, idComponenteAtual, idComponenteAnterior);
        }

    }

    private void criaComponentesDeAcordoComOTipo(Componente c) {

        switch (c.getType()) {
            case FIELD:
                criaComponenteField(c);
                break;
            case TEXT:
                criaComponenteText(c);
                break;
            case CHECKBOX:
                criaComponenteCheckBox(c);
                break;
            case BUTTON:
                criaComponenteButton(c);
                break;
        }
    }

    private void criaComponenteButton(Componente c) {
        ConstraintLayout.LayoutParams clpcontactUs;
        Button button = new Button(context);
        button.setText(c.getMessage());
        button.setOnClickListener(onClickListenerButton);
        button.setId(Integer.parseInt(String.valueOf(c.getId())));
        clpcontactUs = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(clpcontactUs);
        constraintLayout.addView(button);
        componentesUI.add(button);
    }

    private void criaComponenteCheckBox(Componente c) {
        ConstraintLayout.LayoutParams clpcontactUs;
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(c.getMessage());
        checkBox.setId(Integer.parseInt(String.valueOf(c.getId())));
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        clpcontactUs = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        checkBox.setLayoutParams(clpcontactUs);
        constraintLayout.addView(checkBox);
        componentesUI.add(checkBox);
    }

    private void criaComponenteText(Componente c) {
        ConstraintLayout.LayoutParams clpcontactUs;
        TextView textView = new TextView(context);
        textView.setText(c.getMessage());
        textView.setId(Integer.parseInt(String.valueOf(c.getId())));
        clpcontactUs = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(clpcontactUs);
        constraintLayout.addView(textView);
        componentesUI.add(textView);
    }

    private void criaComponenteField(Componente c) {
        ConstraintLayout.LayoutParams clpcontactUs;
        editText = new EditText(context);
        editText.setHint(c.getMessage());
        editText.setId(Integer.parseInt(String.valueOf(c.getId())));
        if (c.isHidden()) {
            editText.setVisibility(View.INVISIBLE);
        }

        if (c.getTypefield() != null) {
            editText.setInputType(retornaInputType(c));
        }

        if (c.getTypefield().equals(INPUT_TYPE_TEL_NUMBER)) {
            //adiciona listener para acessar texto durante a digitacao do usuario
            editText.addTextChangedListener(onChangedListenerText);
            //adiciona restricao de no maximo 14 digitos
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        }
        clpcontactUs = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(clpcontactUs);
        constraintLayout.addView(editText);
        componentesUI.add(editText);
    }

    private void adicionaComponentesNoLayout(Componente c, int idComponenteAtual, int idComponenteAnterior) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        if (idComponenteAtual == 1) {
            constraintSet.connect(idComponenteAtual, ConstraintSet.TOP, idComponenteAnterior, ConstraintSet.TOP, (int) c.getTopSpacing());
        } else {
            constraintSet.connect(idComponenteAtual, ConstraintSet.TOP, idComponenteAnterior, ConstraintSet.BOTTOM, (int) c.getTopSpacing());
        }
        constraintSet.connect(idComponenteAtual, ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(idComponenteAtual, ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 0);

        //Adicionar corrente ?
            /*int[] rowChainIds = new int[5];
            rowChainIds[0] = 1;
            rowChainIds[1] = 2;
            rowChainIds[2] = 6;
            rowChainIds[3] = 3;
            rowChainIds[4] = 7;
            constraintSet.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, rowChainIds, null, ConstraintSet.CHAIN_SPREAD);*/
        constraintSet.applyTo(constraintLayout);
    }

    private boolean entradaDeDadosEValidaEnviar() {

        boolean dadosInvalidos = false;
        String textoDigitado = "";
        EditText ed;

        for (int i = 0; i < componentesUI.size(); i++) {

            switch (componentes.get(i).getTypefield()) {

                case INPUT_TYPE_TEXT: {
                     ed = (EditText) componentesUI.get(i);
                     textoDigitado = String.valueOf(ed.getText());

                    //verifica se o campo de texto esta vazio
                    if (textoDigitado.trim().isEmpty()) {
                        //altera a cor do edittext para vermelho
                        ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.red));
                        dadosInvalidos = true;
                    } else {
                        //altera a cor do edittext para verde
                        ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.green));
                    }
                    break;
                }
                case INPUT_TYPE_TEL_NUMBER: {
                     ed = (EditText) componentesUI.get(i);
                    textoDigitado = String.valueOf(ed.getText());
                    //verifica se o texto esta no formato de telefone
                    if (!ENumeroDeTelefone(textoDigitado)) {
                        //altera a cor do edittext para vermelho
                        ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.red));
                        dadosInvalidos = true;
                    } else {
                        //altera a cor do edittext para verde
                        ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.green));
                    }
                    break;
                }
                case INPUT_TYPE_EMAIL: {
                    ed = (EditText) componentesUI.get(i);
                    //verifica se o campo email esta visivel
                    if (ed.getVisibility() == View.VISIBLE) {
                         textoDigitado = String.valueOf(ed.getText());
                         //verifica se o texto digitado esta no formato de email
                        if (!EEmail(textoDigitado)) {
                            //altera a cor do edittext para vermelho
                            ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.red));
                            dadosInvalidos = true;
                        } else {
                            //altera a cor do edittext para verde
                            ViewCompat.setBackgroundTintList(ed, getResources().getColorStateList(R.color.green));
                        }
                    }
                    break;
                }
            }
        }

        if (dadosInvalidos) {
            return false;
        }
        return true;
    }

    //Expressão regular para (##) ####-#### || (##) #####-####
    public boolean ENumeroDeTelefone(String numeroTelefone) {
        return numeroTelefone.matches("\\(\\d\\d\\)\\d\\d\\d\\d\\-\\d\\d\\d\\d") ||
                numeroTelefone.matches("\\(\\d\\d\\)\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d");
    }

    //Expressão regular para xxx@xxx.xxx
    public boolean EEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

    private int retornaInputType(Componente c) {

        String inputType = c.getTypefield();
        if (inputType.equals(INPUT_TYPE_TEXT))
            return InputType.TYPE_CLASS_TEXT;

        if (inputType.equals(INPUT_TYPE_TEL_NUMBER))
            return InputType.TYPE_CLASS_PHONE;

        if (inputType.equals(INPUT_TYPE_EMAIL))
            return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;

        return InputType.TYPE_CLASS_TEXT;
    }

    //Interface criada para a activity implementar o botao enviar
    public interface OnButtonSendListener {
        void onSend();
    }

    private class ReadContatoJSONTask extends AsyncTask<String, Void, List<Componente>> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Componente> doInBackground(String... strings) {

            try {
                //Le a URL passada como argumento no médoto task.execute
                String url = strings[0];
                HttpCall http = new HttpCall(url);
                HttpResponse response = http.execute(HttpCall.Method.GET);

                //Objetos criados para auxiliar no busca das informacoes consumidas do JSON
                componentes = new ArrayList<Componente>();

                try {
                    //Le as informações vindas do JSON
                    JSONObject cells = new JSONObject(response.extractDataAsString());
                    JSONArray arraysCells = cells.getJSONArray("cells");

                    //preenche e cria os objetos de acordo com informacoes da JSON
                    for (int i = 0; i < arraysCells.length(); i++) {
                        Componente p = new Componente();
                        JSONObject cell = arraysCells.getJSONObject(i);

                        criaObjetosComponente(componentes, p, cell);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return componentes;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Componente> s) {
            criaEAtualizaDinamicamenteComponentesNaTela(s, CRIA_COMPONENTE);
            super.onPostExecute(s);
        }
    }
}
