package br.banco.services.contact.message;

public enum ContactSenderType {

    ERROR {
        @Override
        public IContactMsg returnMessage() {
            return new ContactSenderError();
        }
    },
    SUCESS {
        @Override
        public IContactMsg returnMessage() {
            return new ContactSenderSucess();
        }
    };

    public abstract IContactMsg returnMessage();

}
