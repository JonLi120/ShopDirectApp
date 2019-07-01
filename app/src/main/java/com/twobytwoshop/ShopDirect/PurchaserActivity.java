package com.twobytwoshop.ShopDirect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.utils.StateEnum;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaserActivity extends BaseActivity {

    private static String KEY_MAP = "KEY_MAP";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pay_btn)
    Button payBtn;
    @BindView(R.id.purchaser_name)
    View purchaserNameView;
    @BindView(R.id.purchaser_mail)
    View purchaserMailView;
    @BindView(R.id.purchaser_phone)
    View purchaserPhoneView;
    @BindView(R.id.receiver_name)
    View receiverNameView;
    @BindView(R.id.receiver_mail)
    View receiverMailView;
    @BindView(R.id.receiver_phone)
    View receiverPhoneView;
    @BindView(R.id.receiver_country)
    View receiverCountryView;
    @BindView(R.id.receiver_city)
    View receiverCityView;
    @BindView(R.id.receiver_area)
    View receiverAreaView;
    @BindView(R.id.receiver_postal_code)
    View receiverPostalCodeView;
    @BindView(R.id.receiver_address)
    View receiverAddressView;
    @BindView(R.id.payment_mode)
    EditText paymentMode;
    @BindArray(R.array.pay_arr)
    String[] payArr;

    private OrderViewModel viewModel;
    private IncludedLayout purchaserNameLayout = new IncludedLayout();
    private IncludedLayout purchaserMailLayout = new IncludedLayout();
    private IncludedLayout purchaserPhoneLayout = new IncludedLayout();
    private IncludedLayout receiverNameLayout = new IncludedLayout();
    private IncludedLayout receiverMailLayout = new IncludedLayout();
    private IncludedLayout receiverPhoneLayout = new IncludedLayout();
    private IncludedLayout receiverCountryLayout = new IncludedLayout();
    private IncludedLayout receiverCityLayout = new IncludedLayout();
    private IncludedLayout receiverAreaLayout = new IncludedLayout();
    private IncludedLayout receiverPostalCodeLayout = new IncludedLayout();
    private IncludedLayout receiverAddressLayout = new IncludedLayout();
    private PopupWindow popWindow;
    private PopupWindow payPopWindow;
    private int selectedState = -1;
    private int selectedPay = 20;

    static class IncludedLayout {
        @BindView(R.id.view_input_lab)
        TextView title;
        @BindView(R.id.view_input_edit)
        EditText value;
    }

    public static void startActivity(Context context, HashMap<String, String> map) {
        Intent intent = new Intent(context, PurchaserActivity.class);
        intent.putExtra(KEY_MAP, map);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser);
        ButterKnife.bind(this);
        ButterKnife.bind(purchaserNameLayout, purchaserNameView);
        ButterKnife.bind(purchaserMailLayout, purchaserMailView);
        ButterKnife.bind(purchaserPhoneLayout, purchaserPhoneView);
        ButterKnife.bind(receiverNameLayout, receiverNameView);
        ButterKnife.bind(receiverMailLayout, receiverMailView);
        ButterKnife.bind(receiverPhoneLayout, receiverPhoneView);
        ButterKnife.bind(receiverCountryLayout, receiverCountryView);
        ButterKnife.bind(receiverCityLayout, receiverCityView);
        ButterKnife.bind(receiverAreaLayout, receiverAreaView);
        ButterKnife.bind(receiverPostalCodeLayout, receiverPostalCodeView);
        ButterKnife.bind(receiverAddressLayout, receiverAddressView);

        setViewModel();
        setToolbar();
        init();

        HashMap<String, String> map = (HashMap<String, String>) getIntent().getSerializableExtra(KEY_MAP);
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("訂購人資訊");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void init() {
        purchaserNameLayout.title.setText("姓名");
        purchaserMailLayout.title.setText("電子郵件");
        purchaserPhoneLayout.title.setText("聯絡電話");
        receiverNameLayout.title.setText("姓名");
        receiverMailLayout.title.setText("電子郵件");
        receiverPhoneLayout.title.setText("聯絡電話");
        receiverCountryLayout.title.setText("國家");
        receiverCityLayout.title.setText("城市");
        receiverAreaLayout.title.setText("地區");
        receiverPostalCodeLayout.title.setText("郵遞區號");
        receiverAddressLayout.title.setText("地址");

        purchaserNameLayout.value.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        purchaserMailLayout.value.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        purchaserPhoneLayout.value.setInputType(InputType.TYPE_CLASS_PHONE);
        receiverNameLayout.value.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        receiverMailLayout.value.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        receiverPhoneLayout.value.setInputType(InputType.TYPE_CLASS_PHONE);
        receiverPostalCodeLayout.value.setInputType(InputType.TYPE_CLASS_NUMBER);
        receiverAddressLayout.value.setSingleLine(false);

        receiverCountryLayout.value.setFocusable(false);
        receiverCountryLayout.value.setText("Malaysia");
        receiverAreaLayout.value.setFocusable(false);
        receiverAreaLayout.value.setOnClickListener((view)-> initStatePopWindows());

        paymentMode.setText(payArr[0]);
        paymentMode.setOnClickListener((view -> initPayModePopWindows()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initStatePopWindows() {
        if (popWindow != null) {
            popWindow.showAsDropDown(receiverAreaLayout.value, 0, 0, Gravity.BOTTOM);
            return;
        }

        ArrayList<String> list = StateEnum.getList();
        createPopWindow("state", receiverAreaLayout.value, list, (adapter, view, position) -> {
            receiverAreaLayout.value.setText(list.get(position));
            selectedState = StateEnum.getStateNumber(list.get(position));

            if (popWindow != null) {
                popWindow.dismiss();
            }
        });
    }

    private void initPayModePopWindows() {
        if (payPopWindow != null) {
            payPopWindow.showAsDropDown(paymentMode, 0, 0, Gravity.TOP);
            return;
        }

        createPopWindow("pay", paymentMode, Arrays.asList(payArr), (adapter, view, position) -> {
            paymentMode.setText(payArr[position]);
            selectedState = position == 0 ? 20 : 10;

            if (payPopWindow != null) {
                payPopWindow.dismiss();
            }
        });
    }

    private void createPopWindow(String tag, View view, List<String> data, BaseQuickAdapter.OnItemClickListener listener) {
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

        if ("pay".equals(tag)) {
            window.showAsDropDown(view, 0, 0, Gravity.TOP);
            payPopWindow = window;
        } else {
            window.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
            popWindow = window;
        }

    }

    @OnClick({R.id.back_btn})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
    }
}
