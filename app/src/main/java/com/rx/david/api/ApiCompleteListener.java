package com.rx.david.api;

import com.rx.david.bean.BaseResponse;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/5
 * Description: 网络请求回调接口
 */
public interface ApiCompleteListener {
   /**
    * 成功加载数据
    *
    * @param result 请求数据
    */
   void onComplected(Object result);

   /**
    * 加载失败
    *
    * @param msg 错误信息
    */
   void onFailed(BaseResponse msg);
}