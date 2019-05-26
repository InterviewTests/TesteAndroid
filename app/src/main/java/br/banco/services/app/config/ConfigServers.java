/*
   Endereços ficaram acessiveis para facilitar a mudança de servidores
*/
/**
 * carrega dados dos servidores json
 *
 */
package br.banco.services.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ConfigServers {


    /**
     *
     * carerga URL dos servidores REST
     *
     *
     */


    public  ArrayList<String> serverList = new ArrayList<String>();
    public String URLserver = "https://floating-mountain-50292.herokuapp.com/";
    String DataServer = null;

    public ConfigServers() {

        serverList.add("cells");
        serverList.add("fund");

    }

    public String getDataServer(String server) {

        String LoadServer = null;
        if(serverList.contains(server)){
            LoadServer = URLserver + server + ".json";
        }

        return LoadServer;
    }





}

