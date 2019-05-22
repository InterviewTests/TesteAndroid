package br.banco.services.app.utils;

import android.util.Log;

import br.banco.services.fund.data.template.FromFund;
import static br.banco.services.fund.domain.Configclass.SCREEN_CLASS;

public class CompareClassAndData {


    public  boolean compareClassAndData(){

        // comparar se as config da classe sao iguais aos dados
        FromFund fromFund = new FromFund();

        boolean nameAll = false;
        boolean sizeAll = false;

        try {

            String fundName = fromFund.LOAD_DATA_NAME;
            String className = SCREEN_CLASS.Name;

            int fundSize = fromFund.LOAD_DATA_SIZE;
            int classSize =   SCREEN_CLASS.Size;

            //name = SCREEN_CLASS.Name == fromFund.LOAD_DATA_NAME;
            nameAll = fundName.equals(className);
            sizeAll = fundSize == classSize;

            // Log.d("FUND", "VIEW / nameAll -> " + className+ "=" + fundName);
            // Log.d("FUND", "VIEW / sizeAll -> " + classSize+ "=" + fundSize);

        }catch (Exception e){
            Log.d("FUND", "VIEW / CompareClassAndData -> ERROR! -> " + e.getMessage());
        }

        return (nameAll && sizeAll) ;
    }





}
