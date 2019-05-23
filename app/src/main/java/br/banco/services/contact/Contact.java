package br.banco.services.contact;

/*
 @FAZER: Implementar o Parcelable por reduzir gasto de memoria
 */

//import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable {

    private String FullName;
    private String Email;
    private Long Phone;
    private int EmailSave;

    public Contact() {
        //  this.FullName = FullName;
        //  this.Email = Email;
        //  this.Phone = Phone;
        //  this.EmailSave = EmailSave;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }


    public int getEmailSave() {
        return EmailSave;
    }

    public void setEmailSave(int emailSave) {
        EmailSave = emailSave;
    }

    public void showValues(String TAG, String FUNCTION, ArrayList listValues){

        Log.i(TAG, "@TAG: " + TAG + " / " + FUNCTION);

        if(listValues.size()==4 || TAG != null) {
            Log.i(TAG, "FullName = " + listValues.get(0));
            Log.i(TAG, "Email = " + listValues.get(1));
            Log.i(TAG, "Phone = " + listValues.get(2));
            Log.i(TAG, "EmailSave = " + listValues.get(3));
        }else{
            Log.e(TAG, "Erro:  @TAG: " + TAG + " / " + FUNCTION + " lista incompleta!" );
        }
    }

    public void showObjetc(String TAG, String FUNCTION, Contact contact){

        Log.i(TAG, "@TAG: " + TAG + " / " + FUNCTION);

        if( contact!=null && TAG != null) {

            Log.d(TAG, " c.getFullName() = " + contact.getFullName());
            Log.d(TAG, " c.getFullName() = " + contact.getEmail());
            Log.d(TAG, " c.getFullName() = " + contact.getPhone());
            Log.d(TAG, " c.getFullName() = " + contact.getEmailSave());

        }else{
            Log.e(TAG, "Erro:  @TAG: " + TAG + " / " + FUNCTION + " objeto nulo!" );
        }
    }


}
