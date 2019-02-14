package com.rx.david.api.presenter.impl;

import com.rx.david.BaseApplication;
import com.rx.david.R;
import com.rx.david.api.ApiCompleteListener;
import com.rx.david.api.model.IBookListModel;
import com.rx.david.api.model.impl.BookListModelImpl;
import com.rx.david.api.presenter.IBookListPresenter;
import com.rx.david.api.view.IBaseView;
import com.rx.david.bean.BaseResponse;
import com.rx.david.bean.BookListResponse;
import com.rx.david.util.NetworkUtils;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/5
 * Description:
 */
public class BookListPresenterImpl implements IBookListPresenter, ApiCompleteListener {

    private IBaseView iBaseView;
    private IBookListModel mBookListModel;

    public BookListPresenterImpl(IBaseView view) {
        iBaseView = view;
        mBookListModel = new BookListModelImpl();
    }

    /**
     * 加载数据
     */
    @Override
    public void loadBooks(String q, String tag, int start, int count, String fields) {
        if (!NetworkUtils.isConnected(BaseApplication.getApplication())) {
            iBaseView.showMessage(BaseApplication.getApplication().getString(R.string.poor_network));
            iBaseView.hideProgress();
            return;
        }
        iBaseView.showProgress();
        mBookListModel.loadBookList(q, tag, start, count, fields, this);
    }

    @Override
    public void cancelLoading() {
        mBookListModel.cancelLoading();
    }

    /**
     * 访问接口成功
     *
     * @param result 返回结果
     */
    @Override
    public void onComplected(Object result) {
        if (result instanceof BookListResponse) {
            int index = ((BookListResponse) result).getStart();
            if (index == 0) {
                iBaseView.refreshData(result);
            } else {
                iBaseView.addData(result);
            }
            iBaseView.hideProgress();
        }
    }

    /**
     * 请求失败
     *
     * @param msg 错误信息
     */
    @Override
    public void onFailed(BaseResponse msg) {
        iBaseView.hideProgress();
        if (msg == null) {
            return;
        }
        iBaseView.showMessage(msg.getMsg());
    }
}
