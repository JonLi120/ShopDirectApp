package com.twobytwoshop.ShopDirect;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.twobytwoshop.ShopDirect.adapter.OrderInfoAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.api.response.OrderInfoResponse;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderInfoFragment extends BaseFragment {
    private static String KEY_OID = "KEY_OID";
    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.order_date)
    TextView orderDate;
    @BindView(R.id.order_pay_way)
    TextView orderPayWay;
    @BindView(R.id.order_tran_way)
    TextView orderTranWay;
    @BindView(R.id.order_discount_code)
    TextView orderDiscountCode;
    @BindView(R.id.order_product_price)
    TextView orderProductPrice;
    @BindView(R.id.order_tran_price)
    TextView orderTranPrice;
    @BindView(R.id.order_discount_price)
    TextView orderDiscountPrice;
    @BindView(R.id.order_status)
    TextView orderStatus;
    @BindView(R.id.pay_status)
    TextView payStatus;
    @BindView(R.id.tran_status)
    TextView tranStatus;
    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.order_info_view)
    CardView orderInfoView;
    @BindView(R.id.purchaser_lab)
    TextView purchaserLab;
    @BindView(R.id.order_name)
    TextView orderName;
    @BindView(R.id.order_mail)
    TextView orderMail;
    @BindView(R.id.order_phone)
    TextView orderPhone;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_mail)
    TextView receiverMail;
    @BindView(R.id.receiver_phone)
    TextView receiverPhone;
    @BindView(R.id.receiver_country)
    TextView receiverCountry;
    @BindView(R.id.receiver_city)
    TextView receiverCity;
    @BindView(R.id.receiver_postal_code)
    TextView receiverPostalCode;
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    @BindView(R.id.product_lab)
    TextView productLab;
    @BindView(R.id.product_rcv)
    RecyclerView productRcv;
    @BindView(R.id.receiver_state)
    TextView receiverState;
    private Unbinder unbinder;
    private String oid;
    private OrderViewModel viewModel;
    private OrderInfoAdapter adapter;

    public static OrderInfoFragment newInstance(String oid) {
        Bundle args = new Bundle();
        args.putString(KEY_OID, oid);

        OrderInfoFragment fragment = new OrderInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            oid = bundle.getString(KEY_OID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity)mActivity).changeMenuLayout(true, false);

        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);

        viewModel.showLoading.observe(this, bean -> {
            if (bean.isShow()) {
                ((MainActivity) mActivity).showLoadingDialog(bean.getContent());
            } else {
                ((MainActivity) mActivity).dismissLoadingDialog();
            }
        });

        viewModel.status.observe(this, map -> {
            if ("order_info_search".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getOrderInfoResponse().observe(this, this::setViewData);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.searchOrderInfo(oid);

        productRcv.setLayoutManager(new LinearLayoutManager(mActivity));
        productRcv.setHasFixedSize(true);
        productRcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        adapter = new OrderInfoAdapter(null);
        productRcv.setAdapter(adapter);

        Drawable drawable = divider.getBackground();
        drawable.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC);
    }

    private void setViewData(OrderInfoResponse response) {
        OrderInfoResponse.DataBeanX bean = response.getData();
        orderNo.setText(bean.getOID());
        orderDate.setText(bean.getBuild_datetime());
        orderPayWay.setText("付款方式：" + bean.getPay_way());
        orderTranWay.setText("配送方式：" + bean.getTran_way());
        orderDiscountCode.setText("優惠碼：" + bean.getDiscount_code());
        orderProductPrice.setText("購物金額：RM " + bean.getProduct_price());
        orderTranPrice.setText("運費金額：RM " + bean.getTran_price());
        orderDiscountPrice.setText("折扣金額：RM " + bean.getDiscount_price());
        orderPrice.setText("訂單總金額：RM " + bean.getTotal_price());

        OrderInfoResponse.DataBeanX.DataBean dataBean = bean.getData();
        orderName.setText("訂購人姓名：" + dataBean.getO_name());
        orderMail.setText("訂購人信箱：" + dataBean.getO_email());
        orderPhone.setText("訂購人電話：" + dataBean.getO_phone());
        receiverName.setText("收貨人姓名：" + dataBean.getR_name());
        receiverMail.setText("訂購人信箱：" + dataBean.getR_email());
        receiverPhone.setText("訂購人電話：" + dataBean.getR_phone());
        receiverCountry.setText("國家：" + dataBean.getR_country());
        receiverCity.setText("城市：" + dataBean.getR_city());
        receiverState.setText("地區：" + dataBean.getR_area());
        receiverPostalCode.setText("郵遞區號：" + dataBean.getR_zipcode());
        receiverAddress.setText("地址：" + dataBean.getR_address());

        setStatus(bean.getStatus());
        setPayStatus(bean.getPay_status());
        setTranStatus(bean.getTran_status());

        adapter.replaceData(bean.getProduct_list());
    }

    private void setStatus(String status) {
        orderStatus.setVisibility(View.VISIBLE);
        Drawable drawable = orderStatus.getBackground();
        switch (status) {
            case "100":
                orderStatus.setText("新訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF50B428"), PorterDuff.Mode.SRC);
                break;
            case "200":
                orderStatus.setText("訂單異常");
                drawable.mutate().setColorFilter(Color.parseColor("#F52727"), PorterDuff.Mode.SRC);
                break;
            case "300":
                orderStatus.setText("已完成");
                drawable.mutate().setColorFilter(Color.parseColor("#FF0091EA"), PorterDuff.Mode.SRC);
                break;
            case "400":
                orderStatus.setText("封存訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "500":
                orderStatus.setText("取消訂單");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            default:
                orderStatus.setVisibility(View.GONE);
        }
    }

    private void setPayStatus( String status) {
        payStatus.setVisibility(View.VISIBLE);
        Drawable drawable = payStatus.getBackground();
        switch (status) {
            case "100":
                payStatus.setText("尚未付款");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "200":
                payStatus.setText("已付款");
                drawable.mutate().setColorFilter(Color.parseColor("#FF2885BD"), PorterDuff.Mode.SRC);
                break;
            default:
                payStatus.setVisibility(View.GONE);
        }
    }

    private void setTranStatus(String status) {
        tranStatus.setVisibility(View.VISIBLE);
        Drawable drawable = tranStatus.getBackground();
        switch (status) {
            case "100":
                tranStatus.setText("尚未出貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF918F8F"), PorterDuff.Mode.SRC);
                break;
            case "200":
                tranStatus.setText("已出貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF209995"), PorterDuff.Mode.SRC);
                break;
            case "300":
                tranStatus.setText("公司取貨");
                drawable.mutate().setColorFilter(Color.parseColor("#FF6C7FF3"), PorterDuff.Mode.SRC);
                break;
            default:
                tranStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
