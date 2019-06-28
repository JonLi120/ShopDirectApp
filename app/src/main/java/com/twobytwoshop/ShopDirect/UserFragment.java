package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.twobytwoshop.ShopDirect.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {

    @BindView(R.id.beneficiary_title)
    TextView beneficiaryTitle;
    @BindView(R.id.beneficiary_name_view)
    View beneficiaryNameView;
    @BindView(R.id.beneficiary_nric_view)
    View beneficiaryNRICView;
    @BindView(R.id.beneficiary_relation_view)
    View beneficiaryRelationView;
    @BindView(R.id.user_info_title)
    TextView userInfoTitle;
    @BindView(R.id.member_id_view)
    View memberIdView;
    @BindView(R.id.name_view)
    View nameView;
    @BindView(R.id.nric_view)
    View nricView;
    @BindView(R.id.gender_edit)
    EditText genderEdit;
    @BindView(R.id.marital_edit)
    EditText maritalEdit;
    @BindView(R.id.birthday_edit)
    EditText birthdayEdit;
    @BindView(R.id.wechat_edit)
    EditText wechatEdit;
    @BindView(R.id.mail_edit)
    EditText mailEdit;
    @BindView(R.id.home_no_value)
    EditText homeNoValue;
    @BindView(R.id.office_no_value)
    EditText officeNoValue;
    @BindView(R.id.mobile_no_value)
    EditText mobileNoValue;
    @BindView(R.id.state_edit)
    EditText stateEdit;
    @BindView(R.id.postcode_edit)
    EditText postcodeEdit;
    @BindView(R.id.address_edit)
    EditText addressEdit;


    private Unbinder unbinder;
    IncludedLayout beneficiaryNameLayout = new IncludedLayout();
    IncludedLayout beneficiaryNRICLayout = new IncludedLayout();
    IncludedLayout beneficiaryRelationLayout = new IncludedLayout();
    IncludedLayout memberIdLayout = new IncludedLayout();
    IncludedLayout nameLayout = new IncludedLayout();
    IncludedLayout nricLayout = new IncludedLayout();


    public static UserFragment newInstance() {
        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    static class IncludedLayout {
        @BindView(R.id.lock_title_lab)
        TextView title;
        @BindView(R.id.lock_value)
        TextView value;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) mActivity).changeMenuLayout(true, false);


        ButterKnife.bind(beneficiaryNameLayout, beneficiaryNameView);
        ButterKnife.bind(beneficiaryNRICLayout, beneficiaryNRICView);
        ButterKnife.bind(beneficiaryRelationLayout, beneficiaryRelationView);
        ButterKnife.bind(memberIdLayout, memberIdView);
        ButterKnife.bind(nameLayout, nameView);
        ButterKnife.bind(nricLayout, nricView);

        return view;
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
