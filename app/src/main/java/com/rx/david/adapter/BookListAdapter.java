package com.rx.david.adapter;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rx.david.R;
import com.rx.david.TestListActivity;
import com.rx.david.bean.BookInfoResponse;

@SuppressWarnings("ALL")
public class BookListAdapter extends BaseQuickAdapter<BookInfoResponse, BaseViewHolder> {

   public BookListAdapter() {
      super(R.layout.item_book_list, null);
   }

   @Override
   protected void convert(BaseViewHolder helper, BookInfoResponse item) {
      // 书册
      ImageView iv_book_img = helper.getView(R.id.iv_book_img);
      Glide.with(mContext)
          .load(item.getImages().getLarge())
          .into(iv_book_img);
      // 书名
      helper.setText(R.id.tv_book_title, item.getTitle())
          .setRating(R.id.ratingBar_hots, Float.valueOf(item.getRating().getAverage()) / 2)
          .setText(R.id.tv_hots_num, item.getRating().getAverage())
          .setText(R.id.tv_book_info, item.getInfoString())
          .setText(R.id.tv_book_description, String.format("\u3000%s", item.getSummary()))
          .setOnClickListener(R.id.iv_book_img, v -> mContext.startActivity(new Intent(mContext, TestListActivity.class)));
   }
}
