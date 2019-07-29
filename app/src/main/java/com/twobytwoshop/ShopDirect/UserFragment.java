package com.twobytwoshop.ShopDirect;

import android.app.DatePickerDialog;
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
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.UserInfo;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.utils.StateEnum;
import com.twobytwoshop.ShopDirect.viewmodel.HomeViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.bank_view)
    CardView bankView;
    @BindView(R.id.bank_name_view)
    View bankNameView;
    @BindView(R.id.account_no_view)
    View accountNoView;
    @BindView(R.id.spouse_name_view)
    View spouseNameView;
    @BindView(R.id.spouse_nric_view)
    View spouseNRICView;
    @BindView(R.id.spouse_birth_view)
    View spouseBirthView;
    @BindView(R.id.change_pwd_btn)
    Button changePwdBtn;
    @BindView(R.id.modified_btn)
    Button modifiedBtn;
    @BindArray(R.array.gender_arr)
    String[] genderArr;
    @BindArray(R.array.marital_arr)
    String[] maritalArr;

    private Unbinder unbinder;
    private IncludedLayout beneficiaryNameLayout = new IncludedLayout();
    private IncludedLayout beneficiaryNRICLayout = new IncludedLayout();
    private IncludedLayout beneficiaryRelationLayout = new IncludedLayout();
    private IncludedLayout memberIdLayout = new IncludedLayout();
    private IncludedLayout nameLayout = new IncludedLayout();
    private IncludedLayout nricLayout = new IncludedLayout();
    private IncludedLayout bankNameLayout = new IncludedLayout();
    private IncludedLayout accountNoLayout = new IncludedLayout();
    private IncludedLayout spouseNameLayout = new IncludedLayout();
    private IncludedLayout spouseNRICLayout = new IncludedLayout();
    private IncludedLayout spouseBirthLayot = new IncludedLayout();
    private HomeViewModel viewModel;
    private UserInfo userInfo;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private PopupWindow genderPopupWindow;
    private PopupWindow statePopupWindow;
    private PopupWindow maritalPopupWindow;
    private int selectedGender = -1;
    private int selectedState = -1;
    private int selectedMarital = -1;

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
        ButterKnife.bind(bankNameLayout, bankNameView);
        ButterKnife.bind(accountNoLayout, accountNoView);
        ButterKnife.bind(spouseNameLayout, spouseNameView);
        ButterKnife.bind(spouseNRICLayout, spouseNRICView);
        ButterKnife.bind(spouseBirthLayot, spouseBirthView);

        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);

        viewModel.getUserInfo().observe(this, userInfo -> {
            this.userInfo = userInfo;
            setData();
        });

        viewModel.status.observe(this, map -> {
            if ("user update".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
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
        viewModel.getUser();
    }

    private void setData() {
        beneficiaryNameLayout.title.setText("Beneficiary Name");
        beneficiaryNameLayout.value.setText(userInfo.getBeneficiary_name() != null ? userInfo.getBeneficiary_name() : "");
        beneficiaryNRICLayout.title.setText("Beneficiary NRIC/Passport No");
        beneficiaryNRICLayout.value.setText(userInfo.getBeneficiary_ic());
        beneficiaryRelationLayout.title.setText("Beneficiary Relationship");
        beneficiaryRelationLayout.value.setText(userInfo.getBeneficiary_Relationship());
        memberIdLayout.title.setText("Member ID");
        memberIdLayout.value.setText(userInfo.getUuid());
        nameLayout.title.setText("Name");
        nameLayout.value.setText(userInfo.getName());
        nricLayout.title.setText("NRIC/Passport No");
        nricLayout.value.setText(userInfo.getNRIC_Passport_No());
        String gender = userInfo.getGender();
        if ("M".equals(gender)) {
            genderEdit.setText(genderArr[0]);
        } else {
            genderEdit.setText(genderArr[1]);
        }
        String marital = userInfo.getMarital();
        if ("S".equals(marital)) {
            maritalEdit.setText(maritalArr[0]);
        } else {
            maritalEdit.setText(maritalArr[1]);
        }
        birthdayEdit.setText(userInfo.getBirthday());
        wechatEdit.setText(userInfo.getWechatID());
        mailEdit.setText(userInfo.getEmail());
        homeNoValue.setText(userInfo.getHome_Nos());
        officeNoValue.setText(userInfo.getOffice_Nos());
        mobileNoValue.setText(userInfo.getMobile_Nos());
        stateEdit.setText(StateEnum.getState(Integer.parseInt(userInfo.getState())));
        postcodeEdit.setText(userInfo.getPostcode());
        addressEdit.setText(userInfo.getAddress());
        bankNameLayout.title.setText("Bank Name");
        bankNameLayout.value.setText(userInfo.getBank_Name());
        accountNoLayout.title.setText("Account No");
        accountNoLayout.value.setText(userInfo.getAccount_No());
        spouseNameLayout.title.setText("Spouse Name");
        spouseNameLayout.value.setText(userInfo.getSpouse_name());
        spouseNRICLayout.title.setText("Spouse NRIC/Passport No");
        spouseNRICLayout.value.setText(userInfo.getSpouse_ic());
        spouseBirthLayot.title.setText("Spouse DATE OF BIRTH");
        spouseBirthLayot.value.setText(userInfo.getSpouse_birthday());

        if ("0".equals(userInfo.getMdid())) {
            bankView.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.change_pwd_btn, R.id.modified_btn, R.id.birthday_edit, R.id.gender_edit, R.id.state_edit, R.id.marital_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_pwd_btn:
                ((MainActivity) mActivity).startChangePwdFragment();
                break;
            case R.id.modified_btn:
                if (selectedGender != -1) {
                    userInfo.setGender(selectedGender == 0 ? "M" : "F");
                }
                if (selectedMarital != -1) {
                    userInfo.setMarital(selectedMarital == 0 ? "S" : "M");
                }
                if (selectedState != -1) {
                    userInfo.setState(String.valueOf(StateEnum.getStateNumber(stateEdit.getText().toString())));
                }
                userInfo.setWechatID(wechatEdit.getText().toString());
                userInfo.setEmail(mailEdit.getText().toString());
                userInfo.setHome_Nos(homeNoValue.getText().toString());
                userInfo.setOffice_Nos(officeNoValue.getText().toString());
                userInfo.setMobile_Nos(mobileNoValue.getText().toString());
                userInfo.setPostcode(postcodeEdit.getText().toString());
                userInfo.setAddress(addressEdit.getText().toString());

                viewModel.callUpdateUser(userInfo);
                break;
            case R.id.birthday_edit:
                createDatePicker();
                break;
            case R.id.gender_edit:
                KeyboardUtil.hideShowKeyboard(genderEdit, mActivity);
                initGenderPopWindows();
                break;
            case R.id.state_edit:
                KeyboardUtil.hideShowKeyboard(stateEdit, mActivity);
                initStatePopWindows();
                break;
            case R.id.marital_edit:
                KeyboardUtil.hideShowKeyboard(stateEdit, mActivity);
                initMaritalPopWindows();
                break;
        }
    }

    private void initGenderPopWindows() {
        if (genderPopupWindow != null) {
            genderPopupWindow.showAsDropDown(genderEdit, 0, 0, Gravity.BOTTOM);
            return;
        }

        createPopWindow("gender", genderEdit, Arrays.asList(genderArr), ((adapter, view, position) -> {
            genderEdit.setText(genderArr[position]);
            selectedGender = position;

            if (genderPopupWindow != null) {
                genderPopupWindow.dismiss();
            }
        }));
    }

    private void initMaritalPopWindows() {
        if (maritalPopupWindow != null) {
            maritalPopupWindow.showAsDropDown(maritalEdit, 0, 0, Gravity.BOTTOM);
            return;
        }

        createPopWindow("marital", maritalEdit, Arrays.asList(maritalArr), ((adapter, view, position) -> {
            maritalEdit.setText(maritalArr[position]);
            selectedMarital = position;

            if (maritalPopupWindow != null) {
                maritalPopupWindow.dismiss();
            }
        }));
    }

    private void initStatePopWindows() {
        if (statePopupWindow != null) {
            statePopupWindow.showAsDropDown(stateEdit, 0, 0, Gravity.BOTTOM);
            return;
        }

        ArrayList<String> list = StateEnum.getList();
        createPopWindow("state", stateEdit, list, (adapter, view, position) -> {
            stateEdit.setText(list.get(position));
            selectedState = StateEnum.getStateNumber(list.get(position));

            if (statePopupWindow != null) {
                statePopupWindow.dismiss();
            }
        });
    }

    private void createDatePicker() {
        String day = birthdayEdit.getText().toString();
        try {
            Date date = df.parse(day);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DatePickerDialog dialog = new DatePickerDialog(mActivity, (view, y, m, d) -> {
            calendar.set(Calendar.YEAR, y);
            calendar.set(Calendar.MONTH, m);
            calendar.set(Calendar.DAY_OF_MONTH, d);

            birthdayEdit.setText(df.format(calendar.getTime()));
            userInfo.setBirthday(df.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
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
        window.showAsDropDown(view, 0, 0, Gravity.BOTTOM);

        if ("state".equals(tag)) {
            statePopupWindow = window;
        } else if ("gender".equals(tag)) {
            genderPopupWindow = window;
        } else {
            maritalPopupWindow = window;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
