package com.rx.david.api.service;

import com.rx.david.bean.HomeResultBean;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface IGesooService {

   /**
    * 获取首页信息
    */
   @FormUrlEncoded
   @POST("storeGetHome")
   Observable<Response<HomeResultBean>> loadHomeData(@Field("maxType") int maxType,
                                                     @Field("latitude") double latitude,
                                                     @Field("longitude") double longitude,
                                                     @Field("language") String language);
}
