package com.twobytwoshop.ShopDirect;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.twobytwoshop.ShopDirect.adapter.OrderAdapter;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopCarFragment extends BaseFragment {

    private static String KEY_PROXY = "KEY_PROXY";

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
    private boolean isProxy = false;
    private Unbinder unbinder;

    public static ShopCarFragment newInstance(boolean isProxy) {

        Bundle args = new Bundle();
        args.putBoolean(KEY_PROXY, isProxy);

        ShopCarFragment fragment = new ShopCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isProxy = bundle.getBoolean(KEY_PROXY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity)mActivity).changeMenuLayout(true, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewModel();
        init();
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);

        viewModel.getOrders().observe(this, list -> {
            orders.clear();
            orders.addAll(list);
            adapter.replaceData(list);
            viewModel.callCarInfo(getOrderParams());
        });

        viewModel.status.observe(this, map->{
            if ("car_info".equals(map.get("tag"))) {
                if (!"100".equals(map.get("code"))) {
                    Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
                }
            }
        });

        viewModel.getCarInfo().observe(this, response -> {
            if (response != null && response.getData() != null) {
                String price = String.valueOf(response.getData().getProduct_price());
                String discount = String.valueOf(response.getData().getDiscount_price());
                String tran_price = String.valueOf(response.getData().getTran_price());
                String total_price = String.valueOf(response.getData().getOrder_price());

                shopAmount.setText(price);
                couponAmount.setText(discount);
                deliveryAmount.setText(tran_price);
                totalAmount.setText(total_price);
            }
        });
    }

    private void init() {
        viewModel.getOrder();
        shopCarRcv.setHasFixedSize(true);
        shopCarRcv.setLayoutManager(new LinearLayoutManager(mActivity));
        shopCarRcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
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
                    if (Objects.requireNonNull(adapter.getItem(position)).getQut() > 1) {
                        viewModel.updateOrderById((String) view.getTag(), -1);
                    }
                    break;
            }
        }));

        shopCarCoupon.setOnFocusChangeListener(((view, b) -> {
            if (!b) {
                KeyboardUtil.hideShowKeyboard(view, mActivity);
            }
        }));
    }

    @OnClick({R.id.keep_shopping_btn, R.id.send_order_btn, R.id.shop_car_delivery_type})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.keep_shopping_btn:
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
                break;
            case R.id.send_order_btn:
                ((MainActivity)mActivity).startPuchaserFragment(getOrderParams());
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
        View spinnerView = LayoutInflater.from(mActivity).inflate(R.layout.pop_gender, null);
        RecyclerView recyclerView = spinnerView.findViewById(R.id.pop_gender_rcv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

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

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}

