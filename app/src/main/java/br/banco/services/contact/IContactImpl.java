package br.banco.services.contact;

import android.widget.TextView;

public interface IContactImpl {

     interface PresenterImpl{
         void sendForm(Contact contact);
     }

     interface ModelImpl{
          void savePreferecnces(Contact contactForm);
          void clearferecnces(Contact contactForm);
    }
     interface ViewImpl{

         // start
         void drawFormView();

         // process
         void checkTyping(final TextView view);
         void checkFormValuesView();

         // final
         void sendFormView(Contact contactForm);
         void sendFormView2(Contact contactForm);
         void clearFormTextView();
     }

}
