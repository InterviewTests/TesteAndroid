package br.banco.services.app.message;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.R;

public class MessageSuccess extends AppCompatActivity implements IMessage {

    private Context b;

    public String[] configDesign(Context a) {
        this.b = a;
        return b.getResources().getStringArray(R.array.alert_connection_sucess);
    }

}
