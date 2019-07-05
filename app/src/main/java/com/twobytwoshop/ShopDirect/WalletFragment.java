package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.UserInfo;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.viewmodel.HomeViewModel;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WalletFragment extends BaseFragment {

    @BindView(R.id.current_wallet_value)
    TextView currentWalletValue;
    @BindView(R.id.price_edit)
    EditText priceEdit;
    @BindString(R.string.item_product_price)
    String priceFormat;

    private Unbinder unbinder;
    private HomeViewModel viewModel;
    private UserInfo userInfo;

    public static WalletFragment newInstance() {
        Bundle args = new Bundle();

        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) mActivity).changeMenuLayout(true, false);

        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);

        viewModel.showLoading.observe(this, showLoadingBean -> {
            if (showLoadingBean.isShow()) {
                ((MainActivity) mActivity).showLoadingDialog(showLoadingBean.getContent());
            } else {
                ((MainActivity) mActivity).dismissLoadingDialog();
            }
        });

        viewModel.getUserInfo().observe(this, userInfo -> {
            this.userInfo = userInfo;
            currentWalletValue.setText(String.format(priceFormat, userInfo.getE_wallet()));
        });

        viewModel.status.observe(this, map ->{
            if ("wallet".equals(map.get("tag"))) {
                if ("100".equals(map.get("code"))) {
                    WebActivity.startActivity(mActivity, (String) map.get("url"));
                } else {
                    Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUser();

        priceEdit.setOnFocusChangeListener((view1, b) -> {
            if (!b) {
                KeyboardUtil.hideShowKeyboard(view1, mActivity);
            }
        });
    }

    @OnClick(R.id.confirm_btn)
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_btn) {
            if (priceEdit.getText().toString().isEmpty()) {
                Toast.makeText(mActivity, "請輸入儲值金額", Toast.LENGTH_LONG).show();
                return;
            }
            if (Long.valueOf(priceEdit.getText().toString()) > 20000) {
                Toast.makeText(mActivity, "金額不能大於20000", Toast.LENGTH_LONG).show();
                return;
            }
            viewModel.callStoreValue(priceEdit.getText().toString());
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
