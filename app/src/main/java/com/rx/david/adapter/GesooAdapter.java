package com.rx.david.adapter;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rx.david.R;
import com.rx.david.bean.HomeDataBean;

@SuppressWarnings("ALL")
public class GesooAdapter extends BaseQuickAdapter<HomeDataBean, BaseViewHolder> {

    /**
     * 构造器
     */
    public GesooAdapter() {
        super(R.layout.item_view, null);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean item) {
        helper.setText(R.id.textview, item.getName())
                .setOnClickListener(R.id.root_view, v -> Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show());
    }
}
