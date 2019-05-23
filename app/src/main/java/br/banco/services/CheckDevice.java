package br.banco.services;

/**
 *
 * Sequencia de carregamento:
 *
 *  FUND, CONTACT:
 *  View> Presenter-> Model> Screen> Rules>  dbase[Json / Share / dbase]
 *  Rules[...] <Model <Presenter[view] <View[...]
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.app.utils.Version;

public class CheckDevice extends AppCompatActivity  {

    /** VERSAO DO DISPOSITIVO
    /*  TELA DO DISPOSITIVO
    /*  RECURSOS ETC
    **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         Version version = new Version();
         final String osRelease = version.getOsRelease() ;
         final int sdkVersion = version.getSdkVersion();

        onCompatibleSucess(osRelease, sdkVersion);

    }

    void onCompatibleSucess(String osRelease, int sdkVersion){

        /// done decision

        //onCompatibleError
        //onAplicationStart

    }

    void onCompatibleError(String osRelease, int sdkVersion){
        /// done decision
    }

    void onAplicationStart(String osRelease, int sdkVersion){
        /// done decision

    }

}
