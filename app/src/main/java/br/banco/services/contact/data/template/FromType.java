package br.banco.services.contact.data.template;

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

