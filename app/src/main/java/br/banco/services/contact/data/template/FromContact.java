package br.banco.services.contact.data.template;

/**
 *  REGRAS para validar JSON
 *  Algumas regras podem ser definidas externamente como tamnho do arquivo: field:0
 *  getFile() > compareFile() >
 *
 *  RULE_PATCH = ?
 *  RULE_NAME = true
 *  RULE_DATA = true
 *  RULE_TYPE ?
 *  RULE_FIELDS = true;
 *  RULE_CHARS = true;
 *  RULE_BYTES ?;
 *
 */

import android.content.Context;
import android.support.annotation.Nullable;

public class FromContact {

        private Context b;

    /**
     *
     *  regras de formatacao do arquivo
     *
     */

        public final String RULE_PATCH ="[HTTP HERE]";

        public final String RULE_NAME = "cells";
        public final String RULE_DATA ="{'cells':[{'id':1,'type':2,'message':'FRASE:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'NOME','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'MESSAGE','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";
        public final String RULE_TYPE = "TEXT";
        public final int RULE_FIELDS = 6;
        public final int RULE_CHARS = 1000;
        public final int RULE_BYTES = 5000;


    /**
     *  Campos para receber arquivos externos
     *
     */

        public String Patch;
        public String Name;
        public String Data;
        public String Type;
        public int Field;
        public int Chars;
        public int Bytes;

        public FromContact(){

            //  this.Data  = RULE_DATA;
            //  this.Name  = RULE_NAME;
            //  this.Field  = RULE_FIELDS;
            //  this.Chars = RULE_CHARS;
            //  this.Bytes = RULE_BYTES;
            //  this.Type  = RULE_TYPE;

        }

        public String configDesign(Context a) {
            this.b = a;
            return RULE_DATA;
        }

         /**
         *  validador de dados
         */

        @Nullable
        public boolean setComparetor(
                String name,
                String data,
                String type,
                int field,
                int chars,
                int bytes
        ){

            boolean Rules = false;
            try {

                name    = (name == null) ? "" : name;
                data    = (data == null) ? "" : data;
                type    = (type == null) ? "" : type;
                field    = (field  < 1)    ? 0 : field  ;
                chars   = (chars < 1)    ? 0 : chars;
                bytes   = (bytes < 1)    ? 0 : bytes;

                Rules = getCompareName(name, RULE_NAME) &&
                        //getComparetor(data, RULE_DATA) &&
                       // getComparetor(type, RULE_TYPE) &&
                       // getComparetor(field, RULE_FIELDS) &&
                       // getComparetor(chars, RULE_CHARS) &&
                        getComparetor(bytes, RULE_BYTES);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  Rules;
        }

        @Nullable
        public boolean getCompareName(String a, String b){
            return (a.equals(b));
        }
        @Nullable
        public boolean getComparetor(int a, int b){
            return (a==b) || a == 0;
        }
        @Nullable
        public boolean getComparetor(double a, double b){
            return (a==b) || a == 0;
        }
        @Nullable
        public boolean getComparetor(boolean a, boolean b){
            return (a==b) || a == false;
        }

        @Nullable
        public boolean getComparetor(Object a, Object b){
            return (a.equals(b)) || a == null ;
        }


        /**
        *  setters ---------------
        */


    public String getPatch() {
        return Patch;
    }

    public void setPatch(String patch) {
        Patch = patch;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getField() {
        return Field;
    }

    public void setField(int field) {
        Field = field;
    }

    public int getChars() {
        return Chars;
    }

    public void setChars(int chars) {
        Chars = chars;
    }

    public int getBytes() {
        return Bytes;
    }

    public void setBytes(int bytes) {
        Bytes = bytes;
    }


        /**
         *  exibir dados na tela
         */

        public String getStringTest() {

            String nLine = System.getProperty( "line.separator" );
            return " " +
                    " RETURN FALSE ----------" + nLine + nLine +

                    " userCaseFalse=" + userCaseTrue() + nLine +
                    " Name=" + getName() + nLine +
                  //  " Data=" + Data + nLine +
                    " Type=" + getType() + nLine +
                    " Field=" + getField() + nLine +
                    " Chars=" + getChars() + nLine +
                    " Bytes=" + getBytes() + nLine + nLine +

                    " RETURN TRUE  ----------" + nLine + nLine +

                    " userCaseTrue=" + userCaseFalse() + nLine +
                    " Name=" + getName() + nLine +
                    //  " Data=" + Data + nLine +
                    " Type=" + getType() + nLine +
                    " Field=" + getField() + nLine +
                    " Chars=" + getChars() + nLine +
                    " Bytes=" + getBytes() + nLine + nLine +


                    " RESULT RULES -----------------" + nLine + nLine +

                    " RULE_NAME=" + RULE_NAME + nLine +
                    //" RULE_DATA=" + RULE_DATA + nLine +
                    " RULE_TYPE=" + RULE_TYPE + nLine  +
                    " RULE_FIELDS=" + RULE_FIELDS + nLine +
                    " RULE_CHARS=" + RULE_CHARS + nLine +
                    " RULE_BYTES=" + RULE_BYTES + nLine + nLine +

                    " userCaseFalse=" + userCaseNull() + nLine +

                    "";
        }

        /**
         *  USER CASES teste ---------------
         */


        public boolean userCaseTrue(){

            boolean usercase = false;
            usercase = false;

            usercase = setComparetor(
                    RULE_NAME,
                    RULE_DATA,
                    RULE_TYPE,
                    RULE_FIELDS,
                    RULE_CHARS,
                    RULE_BYTES);

            return usercase;
        }

        public boolean userCaseFalse(){
            boolean usercase = false;
            usercase = false;

            usercase = setComparetor(
                    "blablabla",
                    "bla bla bla",
                    "",
                    20,
                    0,
                    0);

            return usercase;
        }

        public boolean userCaseNull(){

            boolean usercase = false;
            usercase = false;

            usercase = setComparetor(
                    null,
                    null,
                    null,
                    -555,
                    -555,
                    -555);

            return usercase;
        }



        public void testeJson(){

            // public  String JSON_STRING ="{'cells':[{'id':1,'type':2,'message':'FRASE:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'NOME','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'MESSAGE','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";
            // setPatch(JSON_STRING); // arquivo externo
            //this.Patch = getPatch();

        }
        public void testePref(){

         }
        public void testeDB(){

        }




}


