package lzacheu.com.br.santanderinvestimento;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickOnTabInvestments_VerifyIfInvestButtonIsVisible(){
        onView(withText("INVESTIMENTO")).perform(click());
        onView(withId(R.id.btn_invest)).perform(scrollTo(), click());
    }

    @Test
    public void clickOnTabContact_VerifyIfSubmitButtonIsVisible(){
        onView(withText("CONTATO")).perform(click());
        onView(withText("Enviar")).check(matches(isDisplayed()));
    }
}
