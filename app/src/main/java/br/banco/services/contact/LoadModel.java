package br.banco.services.contact;

import android.content.Context;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.domain.ContactForm;
import br.banco.services.datasource.DataRepository;

public class LoadModel implements ILoad.Model {


   public static ILoad.Presenter presenter;
   public DataRepository repository;

   public final String APLICATION_FILE = "cels";
   ReactAplication RX = new ReactAplication();
   //Context context;
   ContactForm form;

   public LoadModel(ILoad.Presenter pres){

      this.presenter = pres;
      form = new ContactForm();


      //this.context = presenter.getContext();
   }


   public  void loadData(ContactForm form, Context context){

       repository = new DataRepository(context);
       repository.onLoad(context, APLICATION_FILE);



    //  LoadDataTask load = new LoadDataTask(context);
     // load.delegate = this;
      //load.execute(APLICATION_FILE);



    }

   public void processFinish(String output){

      RX.onNext("output SSSSSSSSSSS = " + output);
      presenter.onCompleted( form, 1);

   }

   public void saveData(ContactForm form, Context context){

   }

   public void clearData(ContactForm form, Context context){


   }




}


/*

Observer<String> myObserver = new Observer<String>() {
      @Override
      public void onCompleted() {
         // Called when the observable has no more data to emit
      }

      @Override
      public void onError(Throwable e) {
         // Called when the observable encounters an error
      }

      @Override
      public void onNext(String s) {
         // Called each time the observable emits data
         Log.d("MY OBSERVER", s);
      }
   };


 */