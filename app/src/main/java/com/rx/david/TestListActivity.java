package com.rx.david;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rx.david.adapter.GesooAdapter;
import com.rx.david.api.presenter.IGesooPresenter;
import com.rx.david.api.presenter.impl.GesooPresenterImpl;
import com.rx.david.api.view.IBaseView;
import com.rx.david.bean.HomeDataBean;
import com.rx.david.bean.HomeResultBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestListActivity extends AppCompatActivity implements IBaseView {

   @Bind(R.id.recyclerview)
   RecyclerView recyclerview;

   private GesooAdapter gesooAdapter;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_test_list);
      ButterKnife.bind(this);

      // 初始化页面
      initview();
      // 实例化数据加载器
      initData();
   }

   private void initview() {
      // 设置adapter
      gesooAdapter = new GesooAdapter();
      recyclerview.setAdapter(gesooAdapter);

      // 默认动画效果
      recyclerview.setItemAnimator(new DefaultItemAnimator());
      // 设置布局管理器，第三个参数为是否逆向布局
      recyclerview.setLayoutManager(new LinearLayoutManager(this,
          LinearLayoutManager.VERTICAL, false));
      // 可以提高效率
      recyclerview.setHasFixedSize(true);
   }

   private void initData() {
      IGesooPresenter iGesooPresenter = new GesooPresenterImpl(this);
      iGesooPresenter.loadHomeData(7);
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      ButterKnife.unbind(this);
   }

   @Override
   public void showMessage(String msg) {
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
   }

   @Override
   public void showProgress() {

   }

   @Override
   public void hideProgress() {

   }

   @Override
   public void refreshData(Object result) {
      if (result instanceof HomeResultBean) {
         List<HomeDataBean> list = ((HomeResultBean) result).getResult().getModules();
         if (list == null || list.size() == 0) {
            return;
         }
         gesooAdapter.setNewData(list);
      }
   }

   @Override
   public void addData(Object result) {

   }
}
