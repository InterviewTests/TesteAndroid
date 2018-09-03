package br.com.testeandroid;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.testeandroid.adapter.ViewPagerAdapter;
import br.com.testeandroid.feature.InvestimentoFragment;
import br.com.testeandroid.feature.contato.ContatoFragment;
import br.com.testeandroid.model.Tela;
import br.com.testeandroid.network.NetworkUrl;
import br.com.testeandroid.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewpager;

    InvestimentoFragment investimentoFragment;
    ContatoFragment contatoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(2);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewpager);
        configuracaoViewPager(viewpager);
    }

    private void configuracaoViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        investimentoFragment = new InvestimentoFragment();
        contatoFragment =new ContatoFragment();
        adapter.addFragment(investimentoFragment, getString(R.string.investimento));
        adapter.addFragment(contatoFragment,getString(R.string.contato));
        viewPager.setAdapter(adapter);
    }

}
