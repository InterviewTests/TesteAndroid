package br.com.iomarsantos.testeandroid;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.di.component.DaggerTestComponent;
import br.com.iomarsantos.testeandroid.di.component.TestComponent;
import br.com.iomarsantos.testeandroid.di.module.ApplicationTestModule;

public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;
    private Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public Repository getRepository() {
        return mTestComponent.getRepository();
    }

    private void setupDaggerTestComponentInApplication() {
        TesteAndroidApp application = ((TesteAndroidApp) mContext.getApplicationContext());
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
        application.setComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
