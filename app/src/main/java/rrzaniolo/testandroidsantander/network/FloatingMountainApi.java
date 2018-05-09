package rrzaniolo.testandroidsantander.network;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import rrzaniolo.testandroidsantander.network.models.response.GetCellsResponse;

/**
 * This interface holds all the Patterns for calling the Floating Mountain API.
 * */
public interface FloatingMountainApi {

    /**
     * Api base Url.
     * */
    String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    /**
     * Method to get form cells.
     * */
    @GET("/cells.json")
    Call<GetCellsResponse> getCells();

    /**
     * Method to get investment info.
     * */
    @GET("/found.json")
    Call<ResponseBody> getFound();
}
