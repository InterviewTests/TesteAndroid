package br.com.santander.testeandroid.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.testeandroid.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements MainView {

    private ViewPager viewPager;
    private final int TAB_CONTACT_INDEX = 1;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == TAB_CONTACT_INDEX) {
                    updateTabLayout(0, false);
                    updateTabLayout(1, true);
                    return;
                }

                updateTabLayout(0, true);
                updateTabLayout(1, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        generateCustomTab(0, getResources().getString(R.string.funds));
        generateCustomTab(1, getResources().getString(R.string.contact));

        TabLayout.Tab tab = tabLayout.getTabAt(TAB_CONTACT_INDEX);
        tab.select();
    }

    @Override
    public void generateCustomTab(int index, String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView textView = view.findViewById(R.id.tv_tab);
        TextView textViewSelected = view.findViewById(R.id.tv_tab_selected);

        textView.setText(text);
        textViewSelected.setText(text);

        tabLayout.getTabAt(index).setCustomView(view);
    }

    @Override
    public void updateTabLayout(int index, boolean selected) {
        TabLayout.Tab tab = tabLayout.getTabAt(index);

        View view = tab.getCustomView();
        LinearLayout selectedView = view.findViewById(R.id.ll_selected_tab);

        if (selected) {
            selectedView.setVisibility(View.VISIBLE);
        } else {
            selectedView.setVisibility(View.GONE);
        }

        tab.setCustomView(view);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
