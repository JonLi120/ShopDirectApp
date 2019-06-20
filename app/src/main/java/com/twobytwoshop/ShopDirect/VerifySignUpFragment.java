package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;

import com.twobytwoshop.ShopDirect.core.BaseFragment;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VerifySignUpFragment extends BaseFragment {
    private static String KEY_SPONSOR = "KEY_SPONSOR";
    private static String KEY_NAME = "KEY_NAME";
    private static String KEY_PASSPORT = "KEY_PASSPORT";
    private static String KEY_GENDER = "KEY_GENDER";
    private static String KEY_BIRTH = "KEY_BIRTH";
    private static String KEY_PHONE = "KEY_PHONE";

    @BindView(R.id.sponsor_value)
    TextView sponsorValue;
    @BindView(R.id.name_value)
    TextView nameValue;
    @BindView(R.id.passport_value)
    TextView passportValue;
    @BindView(R.id.gender_value)
    TextView genderValue;
    @BindView(R.id.birth_value)
    TextView birthValue;
    @BindView(R.id.phone_value)
    TextView phoneValue;
    @BindArray(R.array.gender_arr)
    String[] genderArr;

    private String sponsor;
    private String name;
    private String passport;
    private int gender;
    private String phone;
    private String birth;
    private Unbinder unbinder;

    public static VerifySignUpFragment newInstance(String sponsor, String name, String passport,
                                                   int gender, String birth, String phone) {
        Bundle args = new Bundle();
        args.putString(KEY_SPONSOR, sponsor);
        args.putString(KEY_NAME, name);
        args.putString(KEY_PASSPORT, passport);
        args.putInt(KEY_GENDER, gender);
        args.putString(KEY_BIRTH, birth);
        args.putString(KEY_PHONE, phone);

        VerifySignUpFragment fragment = new VerifySignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        assert bundle != null;
        sponsor = bundle.getString(KEY_SPONSOR);
        name = bundle.getString(KEY_NAME);
        passport = bundle.getString(KEY_PASSPORT);
        gender = bundle.getInt(KEY_GENDER, 0);
        birth = bundle.getString(KEY_BIRTH);
        phone = bundle.getString(KEY_PHONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_verification, container, false);
        unbinder = ButterKnife.bind(this, view);

        sponsorValue.setText(sponsor);
        nameValue.setText(name);
        passportValue.setText(passport);
        genderValue.setText(genderArr[gender]);
        birthValue.setText(birth);
        phoneValue.setText(phone);

        return view;
    }

    @OnClick({R.id.back_btn, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
                break;
            case R.id.register_btn:
                ((LoginActivity) mActivity).startMainActivity();
                break;
        }
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }
}
