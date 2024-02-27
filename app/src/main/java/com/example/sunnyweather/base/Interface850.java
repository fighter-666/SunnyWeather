package com.example.sunnyweather.base;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface850 {

    //复合广告位信息接口
    @Json
    @POST("query/compoundADSpace")
    Call<CompoundADSpaceResponse> compoundADSpace(@Body Request req);
}
