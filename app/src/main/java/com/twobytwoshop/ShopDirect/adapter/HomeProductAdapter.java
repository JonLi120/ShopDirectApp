package com.twobytwoshop.ShopDirect.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.models.Product;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<HomeResponse.DataBean.ProductsBean> list;
    private String format_price, format_pp, format_cp, format_website;
    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view , String tag);
    }

    public HomeProductAdapter(RecyclerView rcv) {
        this.context = rcv.getContext();
        format_price = this.context.getResources().getString(R.string.item_product_price);
        format_pp = this.context.getResources().getString(R.string.item_product_pp);
        format_cp = this.context.getResources().getString(R.string.item_product_cp);
        format_website = this.context.getResources().getString(R.string.format_website);
    }

    public void setData(List<HomeResponse.DataBean.ProductsBean> list) {
        if (this.list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(list.get(position).getPID());
        holder.setItemProductImg(list.get(position).getMain_image());
        holder.setItemProductTitle(list.get(position).getTitle());
        holder.setItemProductPrice(list.get(position).getFixprice());
        holder.setItemProductDiscountPrice(list.get(position).getPrice());
        holder.setItemProductPp(list.get(position).getPp());
        holder.setItemProductCp(list.get(position).getCp());
//        holder.setItemProductFavorite(list.get(position).isFavorite());
//
//        holder.itemProductFavoriteImg.setOnClickListener((view)->{
//            holder.setItemProductFavorite(!list.get(position).isFavorite());
//            list.get(position).setFavorite(!list.get(position).isFavorite());
//        });
    }

    @Override
    public int getItemCount() {
        return list != null? list.size(): 0;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (String) view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemProductImg;
        TextView itemProductTitle;
        TextView itemProductPrice;
        TextView itemProductDiscountPrice;
        TextView itemProductPp;
        TextView itemProductCp;
        ImageView itemProductFavoriteImg;

        ViewHolder(@NonNull View view) {
            super(view);
            itemProductImg = view.findViewById(R.id.item_product_img);
            itemProductTitle = view.findViewById(R.id.item_product_title);
            itemProductPrice = view.findViewById(R.id.item_product_price);
            itemProductDiscountPrice = view.findViewById(R.id.item_product_discount_price);
            itemProductPp = view.findViewById(R.id.item_product_pp);
            itemProductCp = view.findViewById(R.id.item_product_cp);
            itemProductFavoriteImg = view.findViewById(R.id.item_product_favorite_img);
        }

        void setItemProductTitle(String title) {
            itemProductTitle.setText(title);
        }

        void setItemProductImg(String path) {
            path = String.format(format_website, path);
            Glide.with(context).load(path).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(itemProductImg);
        }

        void setItemProductPrice(String price) {
            itemProductPrice.setText(String.format(format_price, price));
        }

        void setItemProductDiscountPrice(String price) {
            itemProductDiscountPrice.setText(String.format(format_price, price));
        }

        void setItemProductPp(String pp) {
            itemProductPp.setText(String.format(format_pp, pp));
        }

        void setItemProductCp(String cp) {
            itemProductCp.setText(String.format(format_cp, cp));
        }

        void setItemProductFavorite(boolean b) {
            if (b) {
                Glide.with(context).load(R.drawable.ic_favorite).into(itemProductFavoriteImg);
            } else {
                Glide.with(context).load(R.drawable.ic_favorite_border).into(itemProductFavoriteImg);
            }
        }
    }
}
