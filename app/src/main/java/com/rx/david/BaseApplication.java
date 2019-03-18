package com.rx.david;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

   private static BaseApplication application;

   @Override
   public void onCreate() {
      super.onCreate();
      application = this;
   }

   /**
    * 获取application
    *
    * @return
    */
   public static Context getApplication() {
      return application;
   }
}
