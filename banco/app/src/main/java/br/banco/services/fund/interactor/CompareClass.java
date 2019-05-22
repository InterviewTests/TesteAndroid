package br.banco.services.fund.interactor;

import android.util.Log;
import br.banco.services.fund.data.template.FromFund;
import static br.banco.services.fund.domain.Configclass.*;

public class CompareClass {


    public  boolean compareClass(Class clas1, Class clas2 ){

         boolean equals = false;
        try {

            equals = (clas1.getClass().equals( clas1.getClass() ));

        }catch (Exception e){
            Log.e("FUND", " "+getClass().getName()+"->ERROR!-> " + e.getMessage());
        }
        return (equals);
    }



    public  boolean compareClassAndData(FromFund fromFund){

        // comparar se as config da classe sao iguais aos dados
        fromFund = new FromFund();

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
            Log.e("FUND", " "+getClass().getName()+"->ERROR!-> " + e.getMessage());
        }

        return (nameAll && sizeAll) ;
    }

}
