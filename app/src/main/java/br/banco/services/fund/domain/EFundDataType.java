package br.banco.services.fund.domain;

import br.banco.services.fund.data.IFundData;
import br.banco.services.fund.data.remote.FromInternational;
import br.banco.services.fund.data.remote.FromNational;


public enum EFundDataType {

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
