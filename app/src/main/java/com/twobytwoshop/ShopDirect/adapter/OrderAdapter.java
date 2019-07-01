package com.twobytwoshop.ShopDirect.adapter;

import android.content.res.Resources;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.models.Order;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    private final String format_website;
    private final String format_price;

    public OrderAdapter(@Nullable List<Order> data) {
        super(R.layout.item_shop_car, data);

        Resources res = BaseApplication.getInstance().getResources();
        format_website = res.getString(R.string.format_website);
        format_price = res.getString(R.string.item_product_price);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.itemView.setTag(item.getPid());
        ImageView img = helper.getView(R.id.item_shop_car_img);

        Glide.with(img).load(String.format(format_website, item.getImg()))
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(img);

        helper.setText(R.id.item_shop_car_title, item.getTitle());

        if (item.getTag() == 1) {
            helper.setText(R.id.item_shop_car_price, String.format(format_price, item.getPrice()));
        } else {
            helper.setText(R.id.item_shop_car_price, item.getPrice());
        }

        helper.setText(R.id.item_shop_car_qut_value, String.valueOf(item.getTotal()));

        helper.setTag(R.id.item_shop_car_delete, item.getPid());
        helper.setTag(R.id.item_shop_car_qut_plus, item.getPid());
        helper.setTag(R.id.item_shop_car_qut_sub, item.getPid());

        helper.addOnClickListener(R.id.item_shop_car_qut_plus);
        helper.addOnClickListener(R.id.item_shop_car_qut_sub);
        helper.addOnClickListener(R.id.item_shop_car_delete);
    }
}
