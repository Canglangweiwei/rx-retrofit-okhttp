package com.rx.david.api.view;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/1/10
 * Description:
 */
public interface IBaseView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

    void addData(Object result);
}