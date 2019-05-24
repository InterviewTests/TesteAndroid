package br.banco.services.contact.interactor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.banco.services.R;
import br.banco.services.contact.data.template.FromContact;

public class LoadScreenForm extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teste);
        String JSON_STRING ="{'cells':[{'id':1,'type':2,'message':'FRASE:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'NOME','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'MESSAGE','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";


        TextView tvTest1;
        tvTest1 = (TextView) findViewById(R.id.tvTest);

        String TestClass = "USER CASE ";
       // FromScreen dadosTeste =  new FromScreen();
        FromContact fromContact = new FromContact();

        fromContact.setPatch(JSON_STRING);

        tvTest1.setText(fromContact.getStringTest());

    }


}
