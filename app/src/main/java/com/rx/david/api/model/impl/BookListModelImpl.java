package com.rx.david.api.model.impl;

import com.rx.david.api.ApiCompleteListener;
import com.rx.david.api.BaseConstant;
import com.rx.david.api.ServiceFactory;
import com.rx.david.api.model.IBookListModel;
import com.rx.david.api.service.IBookListService;
import com.rx.david.bean.BaseResponse;
import com.rx.david.bean.BookListResponse;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/5
 * Description:
 */
public class BookListModelImpl implements IBookListModel {

   /**
    * 获取图书列表
    */
   @Override
   public void loadBookList(String q, final String tag, int start, int count, String fields, final ApiCompleteListener listener) {
      IBookListService iBookListService = ServiceFactory.createService(BaseConstant.HOST_URL_DOUBAN, IBookListService.class);
      iBookListService.getBookList(q, tag, start, count, fields)
          .subscribeOn(Schedulers.io())               // 请求在io线程中执行
          .observeOn(AndroidSchedulers.mainThread())  // 最后在主线程中执行
          .subscribe(new Subscriber<Response<BookListResponse>>() {

             @Override
             public void onStart() {
                super.onStart();
             }

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
             public void onNext(Response<BookListResponse> bookListResponse) {
                if (bookListResponse.isSuccessful()) {
                   listener.onComplected(bookListResponse.body());
                } else {
                   listener.onFailed(new BaseResponse(bookListResponse.code(), bookListResponse.message()));
                }
             }
          });
   }

   @Override
   public void cancelLoading() {

   }
}
