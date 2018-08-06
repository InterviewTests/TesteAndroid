package br.com.iomarsantos.testeandroid.ui.fundo.investimento;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.TestComponentRule;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class InvestimentoFragmentTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    public final IntentsTestRule<FundoActivity> main =
            new IntentsTestRule<>(FundoActivity.class, false, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Before
    public void setup() {
        FundoActivity fundoActivity = main.launchActivity(FundoActivity.getStartIntent(component.getContext()));
        fundoActivity.getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void checkViewsDisplay() {

        onView(withId(R.id.view_pager_fundo_tabs))
                .perform(swipeRight());

        onView(withId(R.id.text_view_investimento_titulo))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_view_investimento_nome_fundo))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_view_investimento_o_que))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_view_investimento_definicao))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_view_investimento_titulo_risco))
                .check(matches(isDisplayed()));

        onView(withId(R.id.degree_risk_view_investimento_one))
                .check(matches(isDisplayed()));

        onView(withId(R.id.degree_risk_view_investimento_two))
                .check(matches(isDisplayed()));

        onView(withId(R.id.degree_risk_view_investimento_three))
                .check(matches(isDisplayed()));

        onView(withId(R.id.degree_risk_view_investimento_four))
                .check(matches(isDisplayed()));

        onView(withId(R.id.degree_risk_view_investimento_five))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_view_investimento_info_titulo))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.investiment_view_more_info_investment))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.divider_investimento_dois))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view_investimento_more_informations))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view_investimento_downinfo_more_informations))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.button_investimento_investir))
                .perform(scrollTo(), click())
                .check(matches(isDisplayed()));

    }


}
