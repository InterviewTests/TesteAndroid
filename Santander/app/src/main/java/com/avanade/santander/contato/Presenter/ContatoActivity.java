//package com.avanade.santander.contato.IPresenter;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//
//import com.avanade.santander.Injection;
//import com.avanade.santander.R;
//import com.avanade.santander.util.ActivityUtils;
//
//public class ContatoActivity extends FragmentActivity {
//
//    ContatoPresenter contatoPresenter;
//
//
//    public static final String EXTRA_TASK_ID = "TASK_ID";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.common_layout_activity);
//
//        // Get the requested task id
//        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);
//
//        ContatoFragment contatoFragment = (ContatoFragment)getSupportFragmentManager().findFragmentById(R.id.container);
//
//        if (contatoFragment == null) {
//            contatoFragment = ContatoFragment.newInstance(taskId);
//
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                    contatoFragment, R.id.container);
//        }
//
//        // Create the presenter
//        new ContatoPresenter(
//                Injection.provideUseCaseHandler(),
//                contatoFragment,
//                Injection.provideGetCells(getApplicationContext());
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//
//}
