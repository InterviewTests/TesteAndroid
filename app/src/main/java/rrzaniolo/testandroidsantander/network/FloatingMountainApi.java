package rrzaniolo.testandroidsantander.network;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import rrzaniolo.testandroidsantander.network.models.response.GetCellsResponse;

/**
 * This interface holds all the Patterns for calling the Floating Mountain API.
 * */
public interface FloatingMountainApi {

    @GET("/cells.json")
    public Response<GetCellsResponse> getCells();

    @GET("/found.json")
    public Response<ResponseBody> getFound();
}
