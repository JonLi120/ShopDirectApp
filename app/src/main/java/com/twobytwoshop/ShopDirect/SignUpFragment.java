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
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.utils.StateEnum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignUpFragment extends BaseFragment {

    @BindView(R.id.sponsor_lab)
    TextView sponsorLab;
    @BindView(R.id.sponsor_value)
    TextInputEditText sponsorValue;
    @BindView(R.id.sponsor_layout)
    TextInputLayout sponsorLayout;
    @BindView(R.id.custom_lab)
    TextView customLab;
    @BindView(R.id.name_value)
    TextInputEditText nameValue;
    @BindView(R.id.name_layout)
    TextInputLayout nameLayout;
    @BindView(R.id.passport_value)
    TextInputEditText passportValue;
    @BindView(R.id.passport_layout)
    TextInputLayout passportLayout;
    @BindView(R.id.gender_lab)
    TextView genderLab;
    @BindView(R.id.birth_lab)
    TextView birthLab;
    @BindView(R.id.gender_value)
    TextView genderValue;
    @BindView(R.id.birth_value)
    TextView birthValue;
    @BindView(R.id.phone_value)
    TextInputEditText phoneValue;
    @BindView(R.id.phone_layout)
    TextInputLayout phoneLayout;
    @BindView(R.id.term_check_box)
    CheckBox termCheckBox;
    @BindView(R.id.term_lab)
    TextView termLab;
    @BindView(R.id.back_btn)
    Button backBtn;
    @BindView(R.id.next_btn)
    Button nextBtn;
    @BindArray(R.array.gender_arr)
    String[] genderArr;
    @BindView(R.id.spouse_name_value)
    TextInputEditText spouseNameValue;
    @BindView(R.id.spouse_name_layout)
    TextInputLayout spouseNameLayout;
    @BindView(R.id.spouse_ic_value)
    TextInputEditText spouseIcValue;
    @BindView(R.id.spouse_ic_layout)
    TextInputLayout spouseIcLayout;
    @BindView(R.id.state_value)
    TextInputEditText stateValue;
    @BindView(R.id.state_layout)
    TextInputLayout stateLayout;
    @BindView(R.id.postcode_value)
    TextInputEditText postcodeValue;
    @BindView(R.id.postcode_layout)
    TextInputLayout postcodeLayout;
    @BindView(R.id.address_value)
    TextInputEditText addressValue;
    @BindView(R.id.address_layout)
    TextInputLayout addressLayout;

    private Unbinder unbinder;
    private LoginActivity activity;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private PopupWindow genderPopupWindow;
    private PopupWindow statePopupWindow;
    protected int selectedGender = 0;
    protected int selectedState = -1;
    protected String sponsor = "";
    protected String name = "";
    protected String passport = "";
    protected String birth = "";
    protected String phone = "";
    protected String spouseName = "";
    protected String spouseIC = "";
    protected String state = "";
    protected String postcode = "";
    protected String address = "";

    static SignUpFragment newInstance() {
        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = ((LoginActivity) mActivity);
        return view;
    }

    @OnClick({R.id.next_btn, R.id.back_btn, R.id.birth_value, R.id.gender_value, R.id.state_value})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
                break;
            case R.id.next_btn:
                if (!termCheckBox.isChecked()) {
                    Toast.makeText(mActivity, "Please agree term.", Toast.LENGTH_LONG).show();
                    return;
                }
                assert mActivity != null;

                sponsor = sponsorValue.getText() == null ? "" : sponsorValue.getText().toString();
                name = nameValue.getText() == null ? "" : nameValue.getText().toString();
                passport = passportValue.getText() == null ? "" : passportValue.getText().toString();
                birth = birthValue.getText().toString();
                phone = phoneValue.getText() == null ? "" : phoneValue.getText().toString();
                spouseName = spouseNameValue.getText() == null ? "" : spouseNameValue.getText().toString();
                spouseIC = spouseIcValue.getText() == null ? "" : spouseIcValue.getText().toString();
                state = stateValue.getText() == null ? "" : stateValue.getText().toString();
                postcode = postcodeValue.getText() == null ? "" : postcodeValue.getText().toString();
                address = addressValue.getText() == null ? "" : addressValue.getText().toString();

                ((LoginActivity) mActivity).viewModel.checkRegister(sponsor,
                        name,
                        passport,
                        selectedGender == 0 ? "M" : "F",
                        birth,
                        phone,
                        spouseName,
                        spouseIC,
                        String.valueOf(selectedState),
                        postcode,
                        address);
                break;
            case R.id.gender_value:
                KeyboardUtil.hideShowKeyboard(genderValue, mActivity);
                initGenderPopWindows();
                break;
            case R.id.birth_value:
                KeyboardUtil.hideShowKeyboard(birthValue, mActivity);
                createDatePicker();
                break;
            case R.id.state_value:
                KeyboardUtil.hideShowKeyboard(stateValue, mActivity);
                initStatePopWindows();
                break;
        }
    }

    private void createDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(activity, (view, y, m, d) -> {
            calendar.set(Calendar.YEAR, y);
            calendar.set(Calendar.MONTH, m);
            calendar.set(Calendar.DAY_OF_MONTH, d);

            birthValue.setText(df.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void initGenderPopWindows() {
        if (genderPopupWindow != null) {
            genderPopupWindow.showAsDropDown(genderValue, 0, 0, Gravity.BOTTOM);
            return;
        }

        createPopWindow("gender", genderValue, Arrays.asList(genderArr), ((adapter, view, position) -> {
            genderValue.setText(genderArr[position]);
            selectedGender = position;

            if (genderPopupWindow != null) {
                genderPopupWindow.dismiss();
            }
        }));
    }

    private void initStatePopWindows() {
        if (statePopupWindow != null) {
            statePopupWindow.showAsDropDown(stateValue, 0, 0, Gravity.BOTTOM);
            return;
        }

        ArrayList<String> list = StateEnum.getList();
        createPopWindow("state", stateValue, list, (adapter, view, position) -> {
            stateValue.setText(list.get(position));
            selectedState = StateEnum.getStateNumber(list.get(position));

            if (statePopupWindow != null) {
                statePopupWindow.dismiss();
            }
        });
    }

    private void createPopWindow(String tag, View view, List<String> data, BaseQuickAdapter.OnItemClickListener listener) {
        View spinnerView = LayoutInflater.from(activity).inflate(R.layout.pop_gender, null);
        RecyclerView recyclerView = spinnerView.findViewById(R.id.pop_gender_rcv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

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
        } else {
            genderPopupWindow = window;
        }
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }
}
