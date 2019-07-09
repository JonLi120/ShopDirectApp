package com.twobytwoshop.ShopDirect.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.models.api.response.OrderInfoResponse;

import java.util.List;

public class OrderInfoAdapter extends BaseQuickAdapter<OrderInfoResponse.DataBeanX.ProductListBean, BaseViewHolder> {

    private final String websiet;

    public OrderInfoAdapter(@Nullable List<OrderInfoResponse.DataBeanX.ProductListBean> data) {
        super(R.layout.item_order_info, data);

        websiet = BaseApplication.getInstance().getResources().getString(R.string.format_website);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfoResponse.DataBeanX.ProductListBean item) {
        ImageView img = helper.getView(R.id.item_product_img);
        Glide.with(BaseApplication.getInstance().getBaseContext())
                .load(String.format(websiet, item.getMain_image()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(img);

        helper.setText(R.id.item_product_title, item.getTitle());
        helper.setText(R.id.item_product_price, "RM：" + item.getFinal_price());
        helper.setText(R.id.item_product_qut, "訂購數量：" + item.getCount());
    }
}
