package br.banco.services.fund.interactor;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.fund.domain.national.NationalFund;

public class ConvertGsonFormat {

    private List<NationalFund> nacional = new ArrayList<NationalFund>();

    public List<NationalFund> listGsonFormat() {
        return nacional;
    }



}