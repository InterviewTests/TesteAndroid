package br.com.santander.testeandroid.ui.Main;

import android.support.v4.app.FragmentManager;

import java.util.List;

import br.com.santander.testeandroid.ui.Contact.ContactFragment;
import br.com.santander.testeandroid.ui.Funds.FundsFragment;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void setupTabs(FragmentManager fragmentManager) {
        TabsAdapter adapter = new TabsAdapter(fragmentManager);
        List<String> tabsTitles = view.getTabsTitles();

        adapter.add(new FundsFragment(), tabsTitles.get(0));
        adapter.add(new ContactFragment(), tabsTitles.get(1));

        view.setupViewPager(adapter);
        view.setupTabs();
    }
}
