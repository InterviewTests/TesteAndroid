package android.teste.com.br.testeandroidapp.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.teste.com.br.testeandroidapp.R;
import android.teste.com.br.testeandroidapp.adapter.ScreenAdapter;
import android.teste.com.br.testeandroidapp.asyncTask.DowloadJsonAsyncTask;
import android.teste.com.br.testeandroidapp.entity.Screen;
import android.teste.com.br.testeandroidapp.utils.JsonParser;
import android.teste.com.br.testeandroidapp.utils.exception.JsonParserException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class InvestimentoFragment extends Fragment implements DowloadJsonAsyncTask.JSONDownloaderCallback {

    private Screen screen;
    private LinearLayout fieldsContainer;

    public InvestimentoFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment InvestimentoFragment.
     */
    public static InvestimentoFragment newInstance() {
        InvestimentoFragment fragment = new InvestimentoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investimento, container, false);
        fieldsContainer = (LinearLayout) view.findViewById(R.id.fields_container);

        baixarDados();

        return view;
    }

    /**
     * Baixa o json contendo os dados dos campos a serem exibidos
     */
    public void baixarDados(){
        String url = "https://floating-mountain-50292.herokuapp.com/fund.json";
        new DowloadJsonAsyncTask(this, getActivity()).execute(url);
    }

    @Override
    public void onPostExecute(String json) {
        try {
            screen = JsonParser.toScreen(json);
            exibirCampos();
        } catch (JsonParserException e) {
            exibirMensagemErroJson(e.getMessage());
        }
    }

    /**
     * Exibe uma mensagem e pergunta se o usuário deseja
     * baixar os dados novamente
     * @param message Mensagem a ser exibida
     */
    private void exibirMensagemErroJson(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(message)
                .setMessage("Deseja tentar novamente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baixarDados();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * Exibe os campos em tela
     */
    private void exibirCampos() {
        new ScreenAdapter(getContext(), fieldsContainer, screen).preencherLayout();
    }

}
