package com.santander.wesleyalves.santandercode.contatosucesso;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode._utils.FontUtils;
import com.santander.wesleyalves.santandercode.fundosinvestimento.FundosInvestimentoActivity;


public class ContatoSucessoFragment extends Fragment implements ContatoSucessoContract.View {

    private View root;
    private String tituloActivity = "Contato";

    private TextView txt_nova_mensagem;
    private TextView txt_obrigado;
    private TextView txt_enviada_sucesso;

    public static ContatoSucessoFragment newInstance() {
        return new ContatoSucessoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_contato_sucesso, container, false);

        definirTituloActivity();
        definirObjetosLayout();
        definirFontes();
        definirListeners();

        return root;
    }

    private void NovaMensagemClick() {
        ((FundosInvestimentoActivity) getActivity()).BtnContatoClick();
    }

    public void definirTituloActivity() {
        SpannableString s = new SpannableString(tituloActivity);
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.txt_nova_mensagem_color)), 0, tituloActivity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((FundosInvestimentoActivity) getActivity()).getSupportActionBar().setTitle(s);
    }

    public void definirObjetosLayout() {
        txt_nova_mensagem = root.findViewById(R.id.txt_nova_mensagem);
        txt_enviada_sucesso = root.findViewById(R.id.txt_enviada_sucesso);
        txt_obrigado = root.findViewById(R.id.txt_obrigado);
    }

    public void definirFontes() {
        Typeface custom_font = Typeface.createFromAsset(root.getContext().getAssets(), FontUtils.DINPRO_MEDIUM);
        txt_obrigado.setTypeface(custom_font);
        txt_enviada_sucesso.setTypeface(custom_font);
        txt_nova_mensagem.setTypeface(custom_font);
    }

    public void definirListeners() {
        txt_nova_mensagem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NovaMensagemClick();
            }
        });
    }
}
