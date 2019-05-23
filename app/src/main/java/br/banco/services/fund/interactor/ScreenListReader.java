package br.banco.services.fund.interactor;

import java.util.ArrayList;
import java.util.List;

public class ScreenListReader {


    public void readItens(){


        List<Object> lista = new ArrayList<Object>();
        lista.add ("bla");
        List<String> subLista = new ArrayList<String>();
        subLista.add ("ble");
        subLista.add ("bli");
        lista.add (subLista);
        for (Object o : lista) {
            if (o instanceof List) {
                // este é o cast "inseguro".
                for (String s: (List<String>)o)
                {
                    System.out.print(s + ",");
                }
                System.out.println ();
            } else {
                String s = (String) o;
                System.out.println ("s=" + s);
            }
        }



    }
}
