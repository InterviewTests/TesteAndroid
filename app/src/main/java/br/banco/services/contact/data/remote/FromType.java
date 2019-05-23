package br.banco.services.contact.data.remote;

import br.banco.services.contact.data.IContact;


public enum FromType {

    NATIONAL {
        @Override
        public IContact returnMessage() {
            return new FromInternational();
        }
    },
    INTERNATIONAL {
        @Override
        public IContact returnMessage() {
            return new FromNational();
        }
    }


    ;

    public abstract IContact returnMessage();

}

