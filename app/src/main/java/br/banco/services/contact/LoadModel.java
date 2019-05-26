package br.banco.services.contact;

import android.content.Context;

import br.banco.services.app.config.ConfigServers;
import br.banco.services.contact.domain.ContactForm;
import br.banco.services.datasource.DataRepository;


public class LoadModel implements ILoad.Model {

   public static ILoad.Presenter presenter;
   DataRepository repository;
   public final String AREA_CONTACT =  "AREA_CONTACT";

   public LoadModel(ILoad.Presenter pres){

      this.presenter = pres;
      //presenter = presenter;
   }


   public  void loadData(ContactForm form, Context context){

        // task - > AREA_CONTACT
        repository = new DataRepository(context);

   }

   public void saveData(ContactForm form, Context context){

   }

   public void clearData(ContactForm form, Context context){


   }


}
