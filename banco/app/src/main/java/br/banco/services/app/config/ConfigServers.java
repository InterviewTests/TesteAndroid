/*
   Endereços ficaram acessiveis para facilitar a mudança de servidores
*/

package br.banco.services.app.config;

public final class ConfigServers {

    public String contactAdress ="https://floating-mountain-50292.herokuapp.com/cells.json";
    public String fundAdress ="https://floating-mountain-50292.herokuapp.com/fund.json";
    public String AlertAdress ="";
    public String MessageAdress ="";

    public String getContactAdress() {
        return contactAdress;
    }

    public void setContactAdress(String contactAdress) {
        this.contactAdress = contactAdress;
    }

    public String getFundAdress() {
        return fundAdress;
    }

    public void setFundAdress(String fundAdress) {
        this.fundAdress = fundAdress;
    }


    public String getAlertAdress() {
        return AlertAdress;
    }

    public void setAlertAdress(String alertAdress) {
        AlertAdress = alertAdress;
    }

    public String getMessageAdress() {
        return MessageAdress;
    }

    public void setMessageAdress(String messageAdress) {
        MessageAdress = messageAdress;
    }
}

