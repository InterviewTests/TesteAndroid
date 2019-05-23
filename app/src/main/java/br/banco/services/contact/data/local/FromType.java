package br.banco.services.contact.data.local;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.contact.data.IContact;



public enum FromType {

    DATABASE {
        @Override
        public IContact returnMessage() {
            return new FromPreferences();
        }
    },
    PREERENCES {
        @Override
        public IContact returnMessage() {
            return new FromDatabase();
        }
    }

    ;

    public abstract IContact returnMessage();

}

