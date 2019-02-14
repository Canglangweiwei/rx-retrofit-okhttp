package com.rx.david.api.model;

import com.rx.david.api.ApiCompleteListener;

public interface IGesooModel {

    /**
     * 获取首页信息
     */
    void loadHomeData(int maxType, ApiCompleteListener listener);
}
