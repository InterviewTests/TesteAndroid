package br.banco.services.app.utils;
/*
 CERTO: Jose Silva Santos
 ERRADO: Jose da Silva Santos

*/

import java.util.regex.Pattern;

public class ValidatorName { //implements TextWatcher

    //private boolean isValidName = false;

    public static final Pattern USER_NAME = Pattern.compile(
            "[a-zA-ZA-z\\-\\+]{1,50}"
    );


    public static boolean haveWords(String strName) {
        try {

            if(strName != null) {
                strName = strName.replace(" ","").trim();
            }

            return USER_NAME.matcher(strName).matches();

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean haveSize(String strName) {
        try {
            return strName != null
                    && strName.length() > 1
                    && strName.length() < 25
                    ;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean haveParts(String strName){
        try {

            String[] parts = {};
            if(strName != null) {
                strName = (strName.trim());
                parts = strName.split(" ");
            }

            return (parts.length > 1 && parts.length < 5) ;

        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isValidName(String strName) {

        //return ( haveWords(strName)  );
        return ( haveWords(strName) && haveSize(strName) && haveParts(strName) );
    }

    /*
    @Override
    final public void afterTextChanged(Editable editableText) {
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {}
    */

}
