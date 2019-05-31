package br.banco.services.app.message;
/**
 *  configura mensagens de acordo com o tipo
 *
 *
 */
import android.content.Context;

public class Message  implements IMessage{

    public Context context;
    public String[] message;
    public IMessage imessage;

    public Message(IMessage imessage){
      this.imessage = imessage;
    }

    public String[] configDesign(Context context){
        return message;
    }

    public String[] configPernalized(Context context){
        return imessage.configDesign(context);
    }
}
