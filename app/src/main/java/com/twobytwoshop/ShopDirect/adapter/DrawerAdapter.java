package com.twobytwoshop.ShopDirect.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;

import java.util.List;

public class DrawerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DrawerAdapter(@Nullable List<String> data) {
        super(R.layout.item_nav, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.item_nav_img, R.mipmap.ic_launcher_round);
        helper.setText(R.id.item_nav_text, item);

    }
}
