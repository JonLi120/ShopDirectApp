package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.HomeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePwdFragment extends BaseFragment {

    @BindView(R.id.old_pwd_edit)
    EditText oldPwdEdit;
    @BindView(R.id.new_pwd_edit)
    EditText newPwdEdit;
    @BindView(R.id.again_pwd_edit)
    EditText againPwdEdit;
    private Unbinder unbinder;
    private HomeViewModel viewModel;

    public static ChangePwdFragment newInstance() {
        Bundle args = new Bundle();

        ChangePwdFragment fragment = new ChangePwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);

        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);

        viewModel.status.observe(this, map -> {
            if ("change password".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
            }
            if ("100".equals(map.get("code"))) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });

        viewModel.showLoading.observe(this, showLoadingBean -> {
            if (showLoadingBean.isShow()) {
                ((MainActivity) mActivity).showLoadingDialog(showLoadingBean.getContent());
            } else {
                ((MainActivity) mActivity).dismissLoadingDialog();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.confirm_btn)
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_btn) {
            if (!newPwdEdit.getText().toString().equals(againPwdEdit.getText().toString())) {
                Toast.makeText(mActivity, "密碼輸入不相同", Toast.LENGTH_LONG).show();
                return;
            }
            viewModel.callChangePwd(oldPwdEdit.getText().toString(), newPwdEdit.getText().toString());
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
