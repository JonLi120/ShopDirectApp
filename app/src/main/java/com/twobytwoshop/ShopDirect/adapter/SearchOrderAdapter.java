package com.twobytwoshop.ShopDirect.adapter;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.models.api.response.SearchOrderResponse;

import java.util.List;

public class SearchOrderAdapter extends BaseQuickAdapter<SearchOrderResponse.DataBean, BaseViewHolder> {
    private String priceFormat = "RM %s";

    public SearchOrderAdapter(@Nullable List<SearchOrderResponse.DataBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchOrderResponse.DataBean item) {
        helper.itemView.setTag(item.getOID());
        helper.setText(R.id.item_order_no, item.getOID());
        helper.setText(R.id.item_order_amount, String.format(priceFormat, item.getTotal_price()));
        helper.setText(R.id.item_order_time, item.getBuild_datetime());
        setStatus(helper, item.getStatus());
        setPayStatus(helper, item.getPay_status());
        setTranStatus(helper, item.getTran_status());
    }

    private void setStatus(BaseViewHolder helper, String status) {
        TextView view = helper.getView(R.id.item_order_status);
        view.setVisibility(View.VISIBLE);
        Drawable drawable = view.getBackground();
        switch (status) {
            case "100":
                view.setText("新訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF50B428"), PorterDuff.Mode.SRC);
                break;
            case "200":
                view.setText("訂單異常");
                drawable.mutate().setColorFilter(Color.parseColor("#F52727"), PorterDuff.Mode.SRC);
                break;
            case "300":
                view.setText("已完成");
                drawable.mutate().setColorFilter(Color.parseColor("#FF0091EA"), PorterDuff.Mode.SRC);
                break;
            case "400":
                view.setText("封存訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "500":
                view.setText("取消訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            default:
                view.setVisibility(View.GONE);
        }
    }

    private void setPayStatus(BaseViewHolder helper, String status) {
        TextView view = helper.getView(R.id.item_pay_status);
        view.setVisibility(View.VISIBLE);
        Drawable drawable = view.getBackground();
        switch (status) {
            case "100":
                view.setText("尚未付款");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "200":
                view.setText("已付款");
                drawable.mutate().setColorFilter(Color.parseColor("#FF2885BD"), PorterDuff.Mode.SRC);
                break;
            default:
                view.setVisibility(View.GONE);
        }
    }

    private void setTranStatus(BaseViewHolder helper, String status) {
        TextView view = helper.getView(R.id.item_tran_status);
        view.setVisibility(View.VISIBLE);
        Drawable drawable = view.getBackground();
        switch (status) {
            case "100":
                view.setText("尚未出貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "200":
                view.setText("已出貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF209995"), PorterDuff.Mode.SRC);
                break;
            case "300":
                view.setText("公司取貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF6C7FF3"), PorterDuff.Mode.SRC);
                break;
            default:
                view.setVisibility(View.GONE);
        }
    }
}
