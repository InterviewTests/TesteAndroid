package br.com.iomarsantos.testeandroid.ui.fundo.contato;

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
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ContatoFragmentTest {

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

        onView(withId(R.id.fragment_contato))
                .check(matches(isDisplayed()));

        onView(withId(R.id.view_flipper))
                .check(matches(isDisplayed()));

        onView(withId(R.id.linear_layout_contato_container))
                .check(matches(isDisplayed()));

        onView(withId(R.id.constraint_layout_cell_type_text))
                .check(matches(isDisplayed()));

        onView(withId(R.id.constraint_layout_cell_type_checkbox))
                .check(matches(isDisplayed()));

        onView(withId(R.id.constraint_layout_cell_type_button))
                .check(matches(isDisplayed()));

    }

    @Test
    public void checkViewsNotDisplay() {
        onView(withId(R.id.constraint_layout_cell_type_image))
                .check(doesNotExist());
    }

}
