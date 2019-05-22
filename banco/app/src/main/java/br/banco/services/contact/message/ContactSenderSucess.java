package br.banco.services.contact.message;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.R;

public class ContactSenderSucess extends AppCompatActivity implements IContactMsg {

    private Context b;

    public String[] configDesign(Context a) {
        this.b = a;
        return b.getResources().getStringArray(R.array.alert_contact_success);
    }

}

