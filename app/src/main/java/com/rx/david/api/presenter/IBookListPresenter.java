package com.rx.david.api.presenter;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/5
 * Description:
 */
public interface IBookListPresenter {

    /**
     * 加载图书列表
     */
    void loadBooks(String q, String tag, int start, int count, String fields);

    /**
     * 取消加载
     */
    void cancelLoading();
}
