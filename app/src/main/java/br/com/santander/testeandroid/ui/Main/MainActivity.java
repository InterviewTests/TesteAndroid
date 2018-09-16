package br.com.santander.testeandroid.ui.Main;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.testeandroid.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements MainView {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPresenter presenter = new MainPresenter(this);
        presenter.setupTabs(getSupportFragmentManager());
    }

    @Override
    public void setupViewPager(TabsAdapter adapter) {
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    @Override
    public List<String> getTabsTitles() {
        List<String> tabsTitles = new ArrayList<>();
        Resources resources = getResources();

        tabsTitles.add(resources.getString(R.string.funds));
        tabsTitles.add(resources.getString(R.string.contact));

        return tabsTitles;
    }

    @Override
    public void setupTabs() {
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
