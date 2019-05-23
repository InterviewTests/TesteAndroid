package br.banco.services.contact.data.remote;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.contact.data.IContact;

public class FromInternational extends AppCompatActivity implements IContact {

    private Context b;

    public String configDesign(Context a) {
        // this.b = a;
        return "{'cells':[{'id':1,'type':2,'message':'Olá,primeiroseapresentecomoseunome:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'Nomecompleto','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'Gostariadecadastrarmeuemail','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";
    }

}
