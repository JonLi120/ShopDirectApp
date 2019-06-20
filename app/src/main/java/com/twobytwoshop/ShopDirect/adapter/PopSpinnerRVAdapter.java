package com.twobytwoshop.ShopDirect.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class PopSpinnerRVAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PopSpinnerRVAdapter(@Nullable List<String> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, item);
    }
}
