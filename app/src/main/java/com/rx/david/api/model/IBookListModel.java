package com.rx.david.api.model;

import com.rx.david.api.ApiCompleteListener;

public interface IBookListModel {
   /**
    * 获取图书接口
    */
   void loadBookList(String q, String tag, int start, int count, String fields, ApiCompleteListener listener);

   /**
    * 取消加载数据
    */
   void cancelLoading();
}
