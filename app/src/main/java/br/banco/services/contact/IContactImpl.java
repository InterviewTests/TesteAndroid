package br.banco.services.contact;

import android.content.Context;
import android.view.View;
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

         void drawFormView();
         void checkTyping(final TextView view);
         void checkFormValuesView(View view);
         void sendFormView2(Contact contactForm);
         void clearFormTextView();
     }

     interface  LoadImpl{

         void onSuccessLoad(Context context, String local);
         void onErrorLoad(Context context, int code);

     }




}
