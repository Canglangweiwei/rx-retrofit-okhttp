package com.rx.david.api;

import com.rx.david.BaseApplication;
import com.rx.david.util.NetworkUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/5
 * Description:
 */
@SuppressWarnings("ALL")
public class ServiceFactory {

   private volatile static OkHttpClient okHttpClient = null;
   private volatile static Retrofit mRetrofit = null;
   private static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 20;
   private static final int DEFAULT_MAX_AGE = 60 * 60;
   private static final int DEFAULT_MAX_STALE_ONLINE = DEFAULT_MAX_AGE * 24;
   private static final int DEFAULT_MAX_STALE_OFFLINE = DEFAULT_MAX_AGE * 24 * 7;

   public static OkHttpClient getOkHttpClient() {
      if (okHttpClient == null) {
         synchronized (OkHttpClient.class) {
            if (okHttpClient == null) {
               // 缓存
               File cacheFile = new File(BaseApplication.getApplication().getCacheDir(), "responses");
               Cache cache = new Cache(cacheFile, DEFAULT_CACHE_SIZE);
               // log日志
               HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
               logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

               okHttpClient = new OkHttpClient.Builder()
                   .cache(cache)
                   .readTimeout(60000, TimeUnit.SECONDS)
                   .writeTimeout(60000, TimeUnit.SECONDS)
                   .connectTimeout(60000, TimeUnit.SECONDS)
                   .addInterceptor(REQUEST_INTERCEPTOR)
                   .addNetworkInterceptor(RESPONSE_INTERCEPTOR)
                   .addInterceptor(logInterceptor)
                   // 添加公共请求头
                   .addInterceptor(chain -> {
                      Request request = chain.request()
                          .newBuilder()
                          .addHeader("Content-Type", "application/json")
                          .addHeader("X-Parse-Application-Id", BaseConstant.X_PARSE_APPLICATION_ID)
                          .build();
                      return chain.proceed(request);
                   })
                   // 添加公共参数
                   .addInterceptor(chain -> {
                      Request original = chain.request();
                      HttpUrl originalHttpUrl = original.url();

                      HttpUrl url = originalHttpUrl.newBuilder()
                          .addQueryParameter("language", "cn")
                          .build();

                      // Request customization: add request headers
                      Request.Builder requestBuilder = original.newBuilder()
                          .url(url);
                      return chain.proceed(requestBuilder.build());
                   })
                   .build();
            }
         }
      }
      return okHttpClient;
   }

   public static Retrofit getRetrofit(String baseUrl) {
      if (mRetrofit == null) {
         synchronized (Retrofit.class) {
            if (mRetrofit == null) {
               mRetrofit = new Retrofit.Builder()
                   .client(getOkHttpClient())
                   .baseUrl(baseUrl)
                   .addConverterFactory(GsonConverterFactory.create())
                   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                   .build();
            }
         }
      }
      return mRetrofit;
   }

   /**
    * 拦截缓存
    */
   private static final Interceptor REQUEST_INTERCEPTOR = chain -> {
      Request request = chain.request();
      // 向服务期请求数据缓存1个小时
      CacheControl tempCacheControl = new CacheControl.Builder()
          .maxStale(DEFAULT_MAX_STALE_ONLINE, TimeUnit.SECONDS)
          .build();
      request = request.newBuilder()
          .cacheControl(tempCacheControl)
          .build();
      return chain.proceed(request);
   };

   /**
    * 拦截
    */
   private static final Interceptor RESPONSE_INTERCEPTOR = chain -> {
      // 针对那些服务器不支持缓存策略的情况下，使用强制修改响应头，达到缓存的效果
      // 响应拦截只不过是出于规范，向服务器发出请求，至于服务器搭不搭理我们我们不管他，我们在响应里面做手脚，有网没有情况下的缓存策略
      Request request = chain.request();
      Response originalResponse = chain.proceed(request);
      int maxAge;
      // 缓存的数据
      if (!NetworkUtils.isConnected(BaseApplication.getApplication())) {
         maxAge = DEFAULT_MAX_STALE_OFFLINE;
      } else {
         maxAge = DEFAULT_MAX_STALE_ONLINE;
      }
      return originalResponse.newBuilder()
          .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
          .removeHeader("Cache-Control")
          .header("Cache-Control", "public, max-age=" + maxAge)
          .build();
   };

   public static <T> T createService(String baseUrl, Class<T> serviceClazz) {
      Retrofit retrofit = new Retrofit.Builder()
          .client(getOkHttpClient())
          .baseUrl(baseUrl)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();
      return retrofit.create(serviceClazz);
   }
}