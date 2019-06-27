package com.twobytwoshop.ShopDirect.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<Integer> imgs = new ArrayList<>();

    public DrawerAdapter(@Nullable List<String> data) {
        super(R.layout.item_nav, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img = helper.getView(R.id.item_nav_img);

        img.setImageResource(imgs.get(helper.getAdapterPosition()));
        helper.setText(R.id.item_nav_text, item);

    }

    public void setImgs(List<Integer> imgs) {
        this.imgs = imgs;
    }
}
