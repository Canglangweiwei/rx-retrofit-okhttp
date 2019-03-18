package com.rx.david.api.presenter.impl;

import com.rx.david.BaseApplication;
import com.rx.david.R;
import com.rx.david.api.ApiCompleteListener;
import com.rx.david.api.model.IGesooModel;
import com.rx.david.api.model.impl.GesooModelImpl;
import com.rx.david.api.presenter.IGesooPresenter;
import com.rx.david.api.view.IBaseView;
import com.rx.david.bean.BaseResponse;
import com.rx.david.bean.HomeResultBean;
import com.rx.david.util.NetworkUtils;

public class GesooPresenterImpl implements IGesooPresenter, ApiCompleteListener {

   private IBaseView iBaseView;
   private IGesooModel iGesooModel;

   public GesooPresenterImpl(IBaseView iBaseView) {
      this.iBaseView = iBaseView;
      iGesooModel = new GesooModelImpl();
   }

   /**
    * 成功加载数据
    *
    * @param result 请求数据
    */
   @Override
   public void onComplected(Object result) {
      if (result instanceof HomeResultBean) {
         iBaseView.refreshData(result);
         iBaseView.hideProgress();
      }
   }

   /**
    * 加载失败
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

   /**
    * 获取首页信息
    */
   @Override
   public void loadHomeData(int maxType) {
      if (!NetworkUtils.isConnected(BaseApplication.getApplication())) {
         iBaseView.showMessage(BaseApplication.getApplication().getString(R.string.poor_network));
         iBaseView.hideProgress();
         return;
      }
      iBaseView.showProgress();
      iGesooModel.loadHomeData(maxType, this);
   }
}
