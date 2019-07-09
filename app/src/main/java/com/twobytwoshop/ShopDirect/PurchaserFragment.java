package com.twobytwoshop.ShopDirect;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.utils.StateEnum;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PurchaserFragment extends BaseFragment {

    private static String KEY_MAP = "KEY_MAP";

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
    private HashMap<String, String> map;
    private String payPointLab = "點數兌換";
    private int orderStatus = 1;
    private Unbinder unbinder;

    static class IncludedLayout {
        @BindView(R.id.view_input_lab)
        TextView title;
        @BindView(R.id.view_input_edit)
        EditText value;
    }

    public static PurchaserFragment newInstance(HashMap<String, String> map) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_MAP, map);

        PurchaserFragment fragment = new PurchaserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchaser, container, false);
        unbinder = ButterKnife.bind(this, view);

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

        orderStatus = sp.getOrderStatus();

        ((MainActivity)mActivity).changeMenuLayout(true, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        purchaserNameLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        purchaserMailLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        purchaserPhoneLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverNameLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverMailLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverPhoneLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverCountryLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverCityLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverAreaLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverPostalCodeLayout.value.setOnFocusChangeListener(new FocusChangeListener());
        receiverAddressLayout.value.setOnFocusChangeListener(new FocusChangeListener());

        setViewModel();
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity)mActivity).changeMenuLayout(true, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            map = (HashMap<String, String>)bundle.getSerializable(KEY_MAP);
        }
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);

        viewModel.getOrderInfo().observe(this, response -> {
            if ("100".equals(response.getCode())) {
                WebActivity.startActivity(mActivity, response.getUrl());
                ((MainActivity)mActivity).startSearchOrderFragment();
            }
        });

        viewModel.status.observe(this, map -> {
            if ("order_info".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
            }
        });

        viewModel.showLoading.observe(this, showLoadingBean -> {
            if (showLoadingBean.isShow()) {
                ((MainActivity)mActivity).showLoadingDialog(showLoadingBean.getContent());
            } else {
                ((MainActivity)mActivity).dismissLoadingDialog();
            }
        });
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
        receiverAddressLayout.value.setImeOptions(EditorInfo.IME_ACTION_DONE);

        receiverCountryLayout.value.setFocusable(false);
        receiverCountryLayout.value.setText("Malaysia");
        receiverAreaLayout.value.setFocusable(false);
        receiverAreaLayout.value.setOnClickListener((view)-> initStatePopWindows());

        if (orderStatus == 1) {
            paymentMode.setText(payArr[0]);
        } else {
            paymentMode.setText(payPointLab);
        }
        paymentMode.setOnClickListener((view -> initPayModePopWindows()));
    }

    public class FocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {
                KeyboardUtil.hideShowKeyboard(view, mActivity);
            }
        }
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

        List<String> data = new ArrayList<>();

        if (orderStatus == 1) {
            data.addAll(Arrays.asList(payArr));
        } else if (orderStatus == 2) {
            data.add(payPointLab);
        }

        createPopWindow("pay", paymentMode, data, (adapter, view, position) -> {
            paymentMode.setText(data.get(position));
            selectedPay = position == 0 ? 20 : 10;

            if (payPopWindow != null) {
                payPopWindow.dismiss();
            }
        });
    }

    private void createPopWindow(String tag, View view, List<String> data, BaseQuickAdapter.OnItemClickListener listener) {
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

        if ("pay".equals(tag)) {
            window.showAsDropDown(view, 0, 0, Gravity.TOP);
            payPopWindow = window;
        } else {
            window.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
            popWindow = window;
        }

    }

    @OnClick({R.id.back_btn, R.id.pay_btn})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
                break;
            case R.id.pay_btn:
                Map<String, String> map = createOrder();
                if (map != null) {
                    viewModel.buildOrder(map);
                }
                break;
        }
    }

    private Map<String, String> createOrder() {
        if (map != null) {
            map.put("pay_way", String.valueOf(selectedPay));
            map.put("o_name", purchaserNameLayout.value.getText().toString());
            map.put("o_email", purchaserMailLayout.value.getText().toString());
            map.put("o_phone", purchaserPhoneLayout.value.getText().toString());
            map.put("r_name", receiverNameLayout.value.getText().toString());
            map.put("r_email", receiverMailLayout.value.getText().toString());
            map.put("r_phone", receiverPhoneLayout.value.getText().toString());
            map.put("r_country", receiverCountryLayout.value.getText().toString());
            map.put("r_city", receiverCityLayout.value.getText().toString());
            map.put("r_area", receiverAreaLayout.value.getText().toString());
            map.put("r_zipcode", receiverPostalCodeLayout.value.getText().toString());
            map.put("r_address", receiverAddressLayout.value.getText().toString());

            return map;
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
