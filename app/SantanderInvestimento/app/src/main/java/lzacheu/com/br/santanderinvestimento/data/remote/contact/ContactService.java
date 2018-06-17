package lzacheu.com.br.santanderinvestimento.data.remote.contact;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by luiszacheu on 6/16/18.
 */

public interface ContactService {

    @GET("/cells.json")
    Call<ContactResponse> getContactFields();
}
