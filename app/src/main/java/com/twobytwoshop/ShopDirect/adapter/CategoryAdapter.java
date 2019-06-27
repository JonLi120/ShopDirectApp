package com.twobytwoshop.ShopDirect.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<CategoryResponse.DataBean, BaseViewHolder> {

    public CategoryAdapter(@Nullable List<CategoryResponse.DataBean> data) {
        super(R.layout.item_category, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryResponse.DataBean item) {
        helper.itemView.setTag(item.getCAID());
        helper.setText(R.id.category_lab, item.getTitle());
    }
}
