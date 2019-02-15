package com.rx.david;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rx.david.adapter.BookListAdapter;
import com.rx.david.api.presenter.impl.BookListPresenterImpl;
import com.rx.david.api.view.IBaseView;
import com.rx.david.bean.BookInfoResponse;
import com.rx.david.bean.BookListResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements IBaseView, SwipeRefreshLayout.OnRefreshListener {

    private static final String fields = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series,alt";

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager mLayoutManager;

    private BookListAdapter mListAdapter;
    private BookListPresenterImpl bookListPresenter;

    private int page = 0;
    private int count = 20;
    private String tag = "新书";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 初始化页面
        initview();
        // 实例化数据加载器
        initData();
        // 开始加载数据
        onRefresh();
    }

    /**
     * 加载数据
     */
    private void initData() {
        bookListPresenter = new BookListPresenterImpl(this);
    }

    /**
     * 初始化页面
     */
    private void initview() {
        // 初始化刷新控件
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setDistanceToTriggerSync(400);
        // 设置adapter
        mListAdapter = new BookListAdapter();
        recyclerview.setAdapter(mListAdapter);

        // 默认动画效果
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.addOnScrollListener(new RecyclerViewScrollDetector());
        // 设置布局管理器，第三个参数为是否逆向布局
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);
        // 可以提高效率
        recyclerview.setHasFixedSize(true);
        // 设置刷新事件
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
        bookListPresenter.loadBooks(null, tag, 0, count, fields);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof BookListResponse) {
            List<BookInfoResponse> list = ((BookListResponse) result).getBooks();
            if (list == null || list.size() == 0) {
                return;
            }
            mListAdapter.setNewData(list);
            page++;
        }
    }

    @Override
    public void addData(Object result) {
        final int start = mListAdapter.getItemCount();
        if (result instanceof BookListResponse) {
            List<BookInfoResponse> list = ((BookListResponse) result).getBooks();
            if (list == null || list.size() == 0) {
                return;
            }
            mListAdapter.addData(start, list);
            page++;
        }
    }

    /**
     * 加载更多
     */
    private void onLoadMore() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            bookListPresenter.loadBooks(null, tag, page * count, count, fields);
        }
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {

        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mListAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
