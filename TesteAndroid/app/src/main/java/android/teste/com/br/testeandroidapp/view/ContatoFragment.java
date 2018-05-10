package android.teste.com.br.testeandroidapp.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.teste.com.br.testeandroidapp.R;
import android.teste.com.br.testeandroidapp.adapter.AdicionarCamposCellHelper;
import android.teste.com.br.testeandroidapp.adapter.CellAdapter;
import android.teste.com.br.testeandroidapp.asyncTask.DowloadJsonAsyncTask;
import android.teste.com.br.testeandroidapp.entity.Cell;
import android.teste.com.br.testeandroidapp.utils.JsonParser;
import android.teste.com.br.testeandroidapp.utils.ValidateUtils;
import android.teste.com.br.testeandroidapp.utils.exception.JsonParserException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class ContatoFragment extends Fragment implements DowloadJsonAsyncTask.JSONDownloaderCallback, AdicionarCamposCellHelper.SubmitCallback {

    private List<Cell> cellList;
    private LinearLayout fieldsContainer;

    private ContatoSubmitCallback contatoSubmitCallback;

    public ContatoFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ContatoFragment.
     */
    public static ContatoFragment newInstance(ContatoSubmitCallback contatoSubmitCallback) {
        ContatoFragment fragment = new ContatoFragment();
        fragment.setContatoSubmitCallback(contatoSubmitCallback);
        return fragment;
    }

    private void setContatoSubmitCallback(ContatoSubmitCallback contatoSubmitCallback){
        this.contatoSubmitCallback = contatoSubmitCallback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contato, container, false);
        fieldsContainer = (LinearLayout) view.findViewById(R.id.fields_container);

        baixarDados();

        return view;
    }

    /**
     * Baixa o json contendo os dados dos campos a serem exibidos
     */
    public void baixarDados(){
        String url = "https://floating-mountain-50292.herokuapp.com/cells.json";
        new DowloadJsonAsyncTask(this, getActivity()).execute(url);
    }

    /**
     * Lida com o json recebido
     * @param json Json recebido
     */
    @Override
    public void onPostExecute(String json) {
        try {
            cellList = JsonParser.toListCell(json);
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
        new CellAdapter(this, getActivity(), cellList, fieldsContainer).preencherLayout();
    }

    @Override
    public void onSubmitButtonPress() {
        Boolean camposValidos = ValidateUtils.validarCampos(getActivity(), fieldsContainer, cellList);

        if(camposValidos) {
            contatoSubmitCallback.onContatoSubmit();
        }
    }

    public interface ContatoSubmitCallback {
        public void onContatoSubmit();
    }
}
