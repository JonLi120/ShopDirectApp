package com.twobytwoshop.ShopDirect.adapter;

import android.content.res.Resources;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.core.BaseApplication;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;

import java.util.List;

public class ProductListAdapter extends BaseQuickAdapter<ProductListResponse.DataBean, BaseViewHolder> {

    private String format_cp;
    private String format_website;
    private String format_price;
    private String format_pp;

    public ProductListAdapter(@Nullable List<ProductListResponse.DataBean> data) {
        super(R.layout.item_product, data);
        Resources res = BaseApplication.getInstance().getResources();
        format_price = res.getString(R.string.item_product_price);
        format_pp = res.getString(R.string.item_product_pp);
        format_cp = res.getString(R.string.item_product_cp);
        format_website = res.getString(R.string.format_website);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductListResponse.DataBean item) {
        helper.itemView.setTag(item.getPID());

        ImageView imgView = helper.getView(R.id.item_product_img);

        Glide.with(imgView).load(String.format(format_website, item.getMain_image()))
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imgView);

        helper.setText(R.id.item_product_title, item.getTitle());

        TextView ppLab = helper.getView(R.id.item_product_pp);
        TextView cpLab = helper.getView(R.id.item_product_cp);
        TextView price = helper.getView(R.id.item_product_price);
        TextView discountPrice = helper.getView(R.id.item_product_discount_price);

        String exchange = item.getExchange();

        if (exchange != null && !exchange.isEmpty()) {
            price.setText(exchange);
            discountPrice.setVisibility(View.GONE);
            ppLab.setVisibility(View.GONE);
            cpLab.setVisibility(View.GONE);
        } else {
            price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            price.setText(String.format(format_price, item.getFixprice()));
            discountPrice.setText(String.format(format_price, item.getPrice()));
            ppLab.setText(String.format(format_pp, item.getPp()));
            cpLab.setText(String.format(format_cp, item.getCp()));
        }
    }
}
