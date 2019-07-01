package com.twobytwoshop.ShopDirect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.twobytwoshop.ShopDirect.adapter.OrderAdapter;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity {

    private static String KEY_PROXY = "KEY_PROXY";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    AppBarLayout toolbarLayout;
    @BindString(R.string.shop_car_activity_title)
    String shop_car_title;
    @BindView(R.id.shop_car_rcv)
    RecyclerView shopCarRcv;
    @BindView(R.id.shop_car_delivery_lab)
    TextView shopCarDeliveryLab;
    @BindView(R.id.shop_car_delivery_type)
    TextView shopCarDeliveryType;
    @BindView(R.id.shop_car_coupon_lab)
    TextView shopCarCouponLab;
    @BindView(R.id.shop_car_coupon)
    EditText shopCarCoupon;
    @BindView(R.id.amount1)
    TextView amount1;
    @BindView(R.id.shop_amount)
    TextView shopAmount;
    @BindView(R.id.coupon_amount)
    TextView couponAmount;
    @BindView(R.id.delivery_amount)
    TextView deliveryAmount;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.amount2)
    TextView amount2;
    @BindView(R.id.keep_shopping_btn)
    Button keepShoppingBtn;
    @BindView(R.id.send_order_btn)
    Button sendOrderBtn;
    @BindArray(R.array.trans_arr)
    String[] transArr;

    private OrderViewModel viewModel;
    private OrderAdapter adapter;
    private PopupWindow popWindow;
    private int selectedDelivery = 100;
    private List<Order> orders = new ArrayList<>();
    private boolean isProxy;

    public static void startActivity(Context context, boolean isProxy) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        intent.putExtra(KEY_PROXY, isProxy);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);

        isProxy = getIntent().getBooleanExtra(KEY_PROXY, false);

        setViewModel();
        setToolbar();
        init();
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);

        viewModel.getOrders().observe(this, list -> {
            orders.clear();
            orders.addAll(list);
            adapter.replaceData(list);
            viewModel.callCarInfo(getOrderParams());
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(shop_car_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void init() {
        viewModel.getOrder();
        shopCarRcv.setHasFixedSize(true);
        shopCarRcv.setLayoutManager(new LinearLayoutManager(this));
        shopCarRcv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new OrderAdapter(null);
        shopCarRcv.setAdapter(adapter);

        adapter.setOnItemChildClickListener(((adapter1, view, position) -> {
            switch (view.getId()) {
                case R.id.item_shop_car_delete:
                    orders.remove(position);
                    adapter.remove(position);
                    viewModel.deleteOrder((String) view.getTag());
                    break;
                case R.id.item_shop_car_qut_plus:
                    viewModel.updateOrderById((String) view.getTag(), 1);
                    break;
                case R.id.item_shop_car_qut_sub:
                    viewModel.updateOrderById((String) view.getTag(), -1);
                    break;
            }
        }));

        shopCarCoupon.setOnFocusChangeListener(((view, b) -> {
            if (!b) {
                KeyboardUtil.hideShowKeyboard(view, this);
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.keep_shopping_btn, R.id.send_order_btn, R.id.shop_car_delivery_type})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.keep_shopping_btn:
                finish();
                break;
            case R.id.send_order_btn:
                PurchaserActivity.startActivity(this, getOrderParams());
                break;
            case R.id.shop_car_delivery_type:
                initPopWindow();
                break;
        }
    }

    private void initPopWindow() {
        if (popWindow != null) {
            popWindow.showAsDropDown(shopCarDeliveryType, 0, 0, Gravity.BOTTOM);
            return;
        }

        createPopWindow(shopCarDeliveryType, Arrays.asList(transArr), (adapter, view, position) -> {
            shopCarDeliveryType.setText(transArr[position]);
            if (position == 0) {
                selectedDelivery = 100;
            } else {
                selectedDelivery = 300;
            }
            if (popWindow != null) {
                popWindow.dismiss();
            }
        });
    }

    private void createPopWindow(View view, List<String> data, BaseQuickAdapter.OnItemClickListener listener) {
        View spinnerView = LayoutInflater.from(this).inflate(R.layout.pop_gender, null);
        RecyclerView recyclerView = spinnerView.findViewById(R.id.pop_gender_rcv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PopSpinnerRVAdapter adapter = new PopSpinnerRVAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(listener);

        int width = view.getMeasuredWidth();

        PopupWindow window = new PopupWindow(spinnerView, width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(spinnerView);
        window.showAsDropDown(view, 0, 0, Gravity.BOTTOM);

        popWindow = window;
    }

    private HashMap<String, String> getOrderParams() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uuid", sp.getUUID());
        map.put("isMD", isProxy? "y" : "n");
        map.put("tran_way", String.valueOf(selectedDelivery));
        map.put("carts", getCarJSON());
        map.put("discount_code", shopCarCoupon.getText().toString());
        return map;
    }

    private String getCarJSON() {
        List<Order> list = adapter.getData();
        StringBuilder json = new StringBuilder("{");
        if (list.size() > 0) {
            for (Order item : list) {
                json.append("\"").append(item.getPid()).append("\":\"").append(item.getTotal()).append("\",");
            }
            return json.deleteCharAt(json.lastIndexOf(",")).append("}").toString();
        }
        return "{}";
    }
}

