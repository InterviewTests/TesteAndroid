package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode._utils.ActivityUtils;
import com.santander.wesleyalves.santandercode.cadastrousuario.CadastroUsuarioFragment;
import com.santander.wesleyalves.santandercode.contatosucesso.ContatoSucessoFragment;

public class FundosInvestimentoActivity extends AppCompatActivity {

    private Button btn_footer_investimento;
    private Button btn_footer_contato;
    private ConstraintLayout constraint_investimentos;
    private ConstraintLayout constraint_contato;
    private ConstraintLayout constraint_sucesso;

    private FundosInvestimentoFragment fundosInvestimentoFragment;
    private CadastroUsuarioFragment contatoFragment;
    private ContatoSucessoFragment contatoSucessoFragment;

    private final String TITULO_INVESTIMENTO = "Investimento";
    private final String TITULO_CONTATO = "Contato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos_investimento);

        InvestimentosFragment();

        definirObjetosLayout();
        definirListeners();
        definirTituloActivity(TITULO_INVESTIMENTO);
    }

    private void InvestimentosFragment() {
        fundosInvestimentoFragment =
                (FundosInvestimentoFragment) getSupportFragmentManager().findFragmentById(R.id.fundos_investimento_fragment);

        if (fundosInvestimentoFragment == null) {
            fundosInvestimentoFragment = FundosInvestimentoFragment.newInstance();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fundosInvestimentoFragment, R.id.fundos_investimento_fragment);
        }
    }

    private void ContatoFragment() {
        contatoFragment =
                (CadastroUsuarioFragment) getSupportFragmentManager().findFragmentById(R.id.contato_fragment);

        if (contatoFragment == null) {
            contatoFragment = contatoFragment.newInstance();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), contatoFragment, R.id.contato_fragment);

        }
    }

    private void SucessoFragment() {
        contatoSucessoFragment =
                (ContatoSucessoFragment) getSupportFragmentManager().findFragmentById(R.id.contato_sucesso_fragment);

        if (contatoSucessoFragment == null) {
            contatoSucessoFragment = contatoSucessoFragment.newInstance();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), contatoSucessoFragment, R.id.contato_sucesso_fragment);

        }
    }

    public void BtnInvestimentoClick() {
        definirTituloActivity(TITULO_INVESTIMENTO);

        constraint_contato.setVisibility(View.GONE);
        constraint_investimentos.setVisibility(View.VISIBLE);
        constraint_sucesso.setVisibility(View.GONE);

        btn_footer_investimento.setBackgroundResource(R.drawable.button_footer_pressed);
        btn_footer_contato.setBackgroundResource(R.drawable.button_footer);

        ViewGroup.LayoutParams params = btn_footer_investimento.getLayoutParams();
        params.height = 137;
        btn_footer_investimento.setLayoutParams(params);

        ViewGroup.LayoutParams paramsContato = btn_footer_contato.getLayoutParams();
        paramsContato.height = 130;
        btn_footer_contato.setLayoutParams(paramsContato);

        InvestimentosFragment();
    }

    public void BtnContatoClick() {
        definirTituloActivity(TITULO_CONTATO);

        constraint_investimentos.setVisibility(View.GONE);
        constraint_contato.setVisibility(View.VISIBLE);
        constraint_sucesso.setVisibility(View.GONE);

        btn_footer_contato.setBackgroundResource(R.drawable.button_footer_pressed);
        btn_footer_investimento.setBackgroundResource(R.drawable.button_footer);

        ViewGroup.LayoutParams params = btn_footer_investimento.getLayoutParams();
        params.height = 130;
        btn_footer_investimento.setLayoutParams(params);

        ViewGroup.LayoutParams paramsContato = btn_footer_contato.getLayoutParams();
        paramsContato.height = 137;
        btn_footer_contato.setLayoutParams(paramsContato);

        ContatoFragment();
    }

    public void SucessoContatoClick() {
        constraint_investimentos.setVisibility(View.GONE);
        constraint_contato.setVisibility(View.GONE);
        constraint_sucesso.setVisibility(View.VISIBLE);

        SucessoFragment();
    }

    public void definirTituloActivity(String tituloActivity) {
        SpannableString s = new SpannableString(tituloActivity);
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txt_nova_mensagem_color)), 0, tituloActivity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
    }

    private void definirListeners() {
        btn_footer_investimento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BtnInvestimentoClick();
            }
        });

        btn_footer_contato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BtnContatoClick();
            }
        });
    }

    private void definirObjetosLayout() {
        btn_footer_investimento = findViewById(R.id.btn_investimento);
        btn_footer_contato = findViewById(R.id.btn_contato);
        constraint_investimentos = findViewById(R.id.fundos_investimento_fragment);
        constraint_contato = findViewById(R.id.contato_fragment);
        constraint_sucesso = findViewById(R.id.contato_sucesso_fragment);
    }
}
