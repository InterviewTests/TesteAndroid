package lzacheu.com.br.santanderinvestimento.contact;

import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.data.FundRepository;

public class Injection{

    public static ContactRepository provideContactRepository(){
        return new ContactRepository();
    }

    public static FundRepository provideFundRepository(){
        return new FundRepository();
    }
}