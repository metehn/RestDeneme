package com.metehan.restdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("actor/add")
    Call<String> createActor(@Body Actor actor);
}
