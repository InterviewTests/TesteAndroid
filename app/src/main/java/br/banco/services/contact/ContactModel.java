package br.banco.services.contact;

// area, titulo, mensagem, botao texto, botao estilo

public class ContactModel  implements IContactImpl.ModelImpl {


    public ContactModel(){

        // do action
    }

    @Override
    public void savePreferecnces(Contact contactForm){
       // Log.d("CONTACT", " / MODEL / savePreferecnces -> SUCCESS  " );
    };

    @Override
    public void clearferecnces(Contact contactForm){
        //  Log.d("CONTACT", " / clearferecnces -> SUCCESS  " );
    };

    //public void updateContact(){}
    //public void readContact(){}
    //public void deleteContact(){}

}
