package br.com.iomarsantos.testeandroid.ui.fundo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoFragment;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoFragment;

public class FundoPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public FundoPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return InvestimentoFragment.newInstance();
            case 1:
                return ContatoFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

}
