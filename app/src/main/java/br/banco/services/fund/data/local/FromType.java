package br.banco.services.fund.data.local;

/**
 *
 *  configirar como as classes devem exibir dados
 */
import br.banco.services.fund.data.IFundData;


public enum FromType {

    DATABASE {
        @Override
        public IFundData returnMessage() {
            return new FromPreferences();
        }
    },
    PREERENCES {
        @Override
        public IFundData returnMessage() {
            return new FromDatabase();
        }
    }

    ;

    public abstract IFundData returnMessage();

}
