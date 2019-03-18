package com.rx.david.api;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 请求日志打印
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {

   @Override
   public void log(String message) {
      Log.d("最终请求结果：", message);
   }
}
