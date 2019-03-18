package com.rx.david.api.model.impl;

import android.util.Log;

import com.rx.david.api.ApiCompleteListener;
import com.rx.david.api.BaseConstant;
import com.rx.david.api.ServiceFactory;
import com.rx.david.api.model.IGesooModel;
import com.rx.david.api.service.IGesooService;
import com.rx.david.bean.BaseResponse;
import com.rx.david.bean.HomeResultBean;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GesooModelImpl implements IGesooModel {

   /**
    * 获取首页信息
    */
   @Override
   public void loadHomeData(int maxType, ApiCompleteListener listener) {
      IGesooService iGesooService = ServiceFactory.createService(BaseConstant.BASE_URL, IGesooService.class);
      iGesooService.loadHomeData(maxType, 33.9901455, -117.9233364, "cn")
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<Response<HomeResultBean>>() {
             @Override
             public void onCompleted() {

             }

             @Override
             public void onError(Throwable e) {
                if (e instanceof UnknownHostException) {
                   listener.onFailed(null);
                   return;
                }
                listener.onFailed(new BaseResponse(404, e.getMessage()));
             }

             @Override
             public void onNext(Response<HomeResultBean> response) {
                if (response.isSuccessful()) {
                   Log.d("【页面获取结果】：", response.body().toString());
                   listener.onComplected(response.body());
                } else {
                   listener.onFailed(new BaseResponse(response.code(), response.message()));
                }
             }
          });
   }
}
