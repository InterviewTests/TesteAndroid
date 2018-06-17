package lzacheu.com.br.santanderinvestimento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.data.FundRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
    }
}
