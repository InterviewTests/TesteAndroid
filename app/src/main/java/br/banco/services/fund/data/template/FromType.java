package br.banco.services.fund.data.template;

import br.banco.services.fund.data.IFundData;


public enum FromType {

    FUND_TYPE {
        @Override
        public IFundData returnMessage() {
            return new FromFund();
        }
    },
    MOREINFO_TYPE {
        @Override
        public IFundData returnMessage() {
            return new FomMoreinfo();
        }
    },
    INFO_TYPE {
        @Override
        public IFundData returnMessage() {
            return new FromInfo();
        }
    },

    DOWNLOAD_TYPE {
        @Override
        public IFundData returnMessage() {
            return new FromDownInfo();
        }
    }
    ;

    public abstract IFundData returnMessage();

}
