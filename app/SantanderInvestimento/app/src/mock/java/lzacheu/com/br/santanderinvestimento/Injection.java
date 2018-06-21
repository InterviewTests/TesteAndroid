package lzacheu.com.br.santanderinvestimento;

import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.data.FakeFundRepository;
import lzacheu.com.br.santanderinvestimento.data.FundRepository;

public class Injection{

    public static ContactRepository provideContactRepository(){
        return new ContactRepository();
    }

    public static FundRepository provideFundRepository(){
        return FundRepository;
    }
}