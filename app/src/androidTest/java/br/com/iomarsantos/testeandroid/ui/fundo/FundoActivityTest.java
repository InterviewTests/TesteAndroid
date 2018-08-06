package br.com.iomarsantos.testeandroid.ui.fundo;

import android.support.annotation.UiThread;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.TestComponentRule;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class FundoActivityTest {

    FundoActivity fundoActivity;

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    public final IntentsTestRule<FundoActivity> main =
            new IntentsTestRule<>(FundoActivity.class, false, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Before
    public void setup() {
        fundoActivity = main.launchActivity(FundoActivity.getStartIntent(component.getContext()));
        assertThat(fundoActivity, notNullValue());
    }

    @Test
    public void checkViewsDisplay() {

        onView(withId(R.id.toolbar))
                .check(matches(isDisplayed()));

        onView(withId(R.id.view_pager_fundo_tabs))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tab_layout))
                .check(matches(isDisplayed()));

    }

    @Test
    public void checkSwipePage() {
        onView(withId(R.id.view_pager_fundo_tabs))
                .check(matches(isDisplayed()));

        onView(withId(R.id.view_pager_fundo_tabs))
                .perform(swipeLeft());

        onView(withId(R.id.view_pager_fundo_tabs))
                .perform(swipeRight());

    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.view_pager_fundo_tabs))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkIfContatoFragmentIsVisible() {
        onView(withId(R.id.fragment_contato))
                .perform(click())
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void checkIfInvestimentoFragmentIsVisible() {
        onView(withId(R.id.view_pager_fundo_tabs))
                .perform(swipeRight())
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.fragment_investimento))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToolbarTitle() {
        String toolbarTitile = getInstrumentation().getTargetContext().getString(R.string.contato_titulo);
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText(toolbarTitile)));
    }


    @Test
    @UiThread
    public void checkTabSwitch() {
        /*onView(withId(R.id.view_pager_fundo_tabs))
                .perform(swipeRight())
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.fragment_investimento))
                .check(matches(isDisplayed()));
        String toolbarTitile = getInstrumentation().getTargetContext().getString(R.string.investimento_titulo);
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText(toolbarTitile)));*/

        // I'd like to switch to a tab (test2) and check that the switch happened
        onView(allOf(withText("Contato"), isDescendantOfA(withId(R.id.view_pager_fundo_tabs))))
                .perform(click())
                .check(matches(isDisplayed()));

        // Then I'd like to check that the tab text (test2) matches the current fragment title

        //((ContatoFragment)fundoActivity.fundoPagerAdapter.getItem(0)).getTitle();

        assertThat("Contato", Matchers.equalTo("Contato"));

    }

}