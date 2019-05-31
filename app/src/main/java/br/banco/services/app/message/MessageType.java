package br.banco.services.app.message;
/**
 *  configura mensagens de acordo com o tipo
 *
 *
 */
public enum MessageType {

    LOAD {
        @Override
        public IMessage returnMessage() {
            return new MessageLoad();
        }
    },
    CONFIG {
        @Override
        public IMessage returnMessage() {
            return new MessageConfig();
        }
    },
    NORMAL {
        @Override
        public IMessage returnMessage() {
            return new MessageNormal();
        }
    },
    SUCESS {
        @Override
        public IMessage returnMessage() {
            return new MessageSuccess();
        }
    },
    ERROR {
        @Override
        public IMessage returnMessage() {
            return new MessageError();
        }
    }
    ,
    SEND {
        @Override
        public IMessage returnMessage() {
            return new MessageSend();
        }
    }

    ;

    public abstract IMessage returnMessage();

}
