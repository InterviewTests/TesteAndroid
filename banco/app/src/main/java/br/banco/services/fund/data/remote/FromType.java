package br.banco.services.fund.data.remote;

import br.banco.services.fund.data.IFundData;


public enum FromType {

    NATIONAL {
        @Override
        public IFundData returnMessage() {
            return new FromNational();
        }
    },
    INTERNATIONAL {
        @Override
        public IFundData returnMessage() {
            return new FromInternational();
        }
    }

    ;

    public abstract IFundData returnMessage();

}
