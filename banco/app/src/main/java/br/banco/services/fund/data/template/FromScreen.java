package br.banco.services.fund.data.template;

/**
 *
 *  REGRAS para validar JSON
 *  Algumas regras podem ser definidas externamente como tamnho do arquivo: size:0
 *
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.Arrays;

import br.banco.services.fund.data.IFundData;

public class FromScreen extends AppCompatActivity implements IFundData {

    private Context b;

    String Name;
    String Data;
    String Type;
    int Size;
    int Chars;
    int Bytes;

    //---------- get external / get patern -------------

   // public final String LOAD_DATA_DEVICE ="{'cells':[{'id':1,'type':2,'message':'Olá,primeiroseapresentecomoseunome:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'Nomecompleto','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'Gostariadecadastrarmeuemail','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";
    public final String LOAD_DATA_DEVICE ="{JSON HERE}";

    public final String LOAD_DATA_NAME = "screen";
    public final int LOAD_DATA_SIZE = 10; // fields
    public final int LOAD_DATA_CHARS = 1000; // 1mb
    public final int LOAD_DATA_BYTES = 5000; // nao definido
    public final String LOAD_DATA_TYPE = "TEXT";

    public FromScreen(){

        this.Data  = LOAD_DATA_DEVICE;
        this.Name  = LOAD_DATA_NAME;
        this.Size  = LOAD_DATA_SIZE;
        this.Chars = LOAD_DATA_CHARS;
        this.Bytes = LOAD_DATA_BYTES;
        this.Type  = LOAD_DATA_TYPE;

    }

    public String configDesign(Context a) {
        this.b = a;
        return LOAD_DATA_DEVICE;
    }

    /**
     *
     *  VALIDATORS ---------------
     */

    @Nullable
   public boolean setComparetor(
                    String name,
                    String data,
                    String type,
                    int size,
                    int chars,
                    int bytes
   ){

       boolean Rules = false;
       try {

                name    = (name == null) ? "" : name;
                data    = (data == null) ? "" : data;
                type    = (type == null) ? "" : type;
                size    = (size  < 1)    ? 0 : size  ;
                chars   = (chars < 1)    ? 0 : chars;
                bytes   = (bytes < 1)    ? 0 : bytes;

                Rules = getComparetor(data, LOAD_DATA_DEVICE) &&
                getComparetor(name, LOAD_DATA_NAME) &&
                getComparetor(size, LOAD_DATA_SIZE) &&
                getComparetor(chars, LOAD_DATA_CHARS) &&
                getComparetor(bytes, LOAD_DATA_BYTES)&&
                getComparetor(type, LOAD_DATA_TYPE);

       } catch (Exception e) {
           e.printStackTrace();
       }
       return  Rules;
   }

   // @SuppressWarnings();
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
    public boolean getComparetor(String a, String b){
        return (a.equals(b)) || a == "" ;
    }
    @Nullable
    public boolean getComparetor(Object a, Object b){
        return (a.equals(b)) || a == null ;
    }

    /**
     *
     *  SETERS ---------------
     */


    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    /**
     *
     *  USER CASES retorno ---------------
     */

    public String getStringTest() {

        String nLine = System.getProperty( "line.separator" );


        return " " +
                " ------CLASS: FromScreen----------" + nLine + nLine +

                " Name=" + Name + nLine +
                " Data=" + Data + nLine +
                " Type=" + Type + nLine +
                " Size=" + Size + nLine +
                " Chars=" + Chars + nLine +
                " Bytes=" + Bytes + nLine + nLine +

                " -------------EXTERNAL-----------------" + nLine + nLine +

                " LOAD_DATA_DEVICE=" + LOAD_DATA_DEVICE + nLine +
                " LOAD_DATA_NAME=" + LOAD_DATA_NAME + nLine +
                " LOAD_DATA_SIZE=" + LOAD_DATA_SIZE + nLine +
                " LOAD_DATA_CHARS=" + LOAD_DATA_CHARS + nLine +
                " LOAD_DATA_BYTES=" + LOAD_DATA_BYTES + nLine +
                " LOAD_DATA_TYPE=" + LOAD_DATA_TYPE + nLine + nLine +

                "---------------USER CASE---------------" + nLine + nLine +
                " userCaseFalse=" + userCaseTrue() + nLine +
                " userCaseTrue=" + userCaseFalse() + nLine +
                " userCaseFalse=" + userCaseNull() + nLine +

                "";
    }

    /**
     *
     *  USER CASES teste ---------------
     */


    public boolean userCaseTrue(){

        boolean usercase = false;
        usercase = false;

        usercase = setComparetor(
                  LOAD_DATA_NAME,
                  LOAD_DATA_DEVICE,
                  LOAD_DATA_TYPE,
                  LOAD_DATA_SIZE,
                  LOAD_DATA_CHARS,
                  LOAD_DATA_BYTES);

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





}
