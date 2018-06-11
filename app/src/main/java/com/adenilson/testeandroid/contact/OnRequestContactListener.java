package com.adenilson.testeandroid.contact;

import com.adenilson.testeandroid.networking.webservices.contact.CellsResponse;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public interface OnRequestContactListener {

    void onRequestContactSuccess(CellsResponse response);

    void onRequestContactFailed(int messageResourceId);
}
