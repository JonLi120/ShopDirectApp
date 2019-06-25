package com.twobytwoshop.ShopDirect;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.twobytwoshop.ShopDirect.core.BaseActivity;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.utils.DisplayUtil;
import com.twobytwoshop.ShopDirect.viewmodel.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.account_edit)
    EditText accountEdit;
    @BindView(R.id.pwd_edit)
    EditText pwdEdit;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindDrawable(R.drawable.ic_account)
    Drawable ic_account;
    @BindDrawable(R.drawable.ic_lock)
    Drawable ic_lock;
    @BindColor(R.color.colorGreen)
    int green;

    private Unbinder unbinder;

    static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        int size = DisplayUtil.convertDpToPx(mActivity, 40);

        ic_account.setBounds(60, 0, (int)(size * 1.5), size);
        ic_lock.setBounds(60, 0, (int)(size * 1.5), size);

        accountEdit.setCompoundDrawables(ic_account, null, null, null);
        accountEdit.setCompoundDrawablePadding(100);
        pwdEdit.setCompoundDrawables(ic_lock, null, null, null);
        pwdEdit.setCompoundDrawablePadding(100);

        loginBtn.getBackground().mutate().setColorFilter(green, PorterDuff.Mode.SRC_ATOP);
    }

    @OnClick({R.id.login_btn})
    void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            assert mActivity != null;
            ((LoginActivity)mActivity).viewModel.callLogin(accountEdit.getText().toString(), pwdEdit.getText().toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
