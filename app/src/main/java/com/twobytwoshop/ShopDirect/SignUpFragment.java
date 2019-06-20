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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.twobytwoshop.ShopDirect.adapter.PopSpinnerRVAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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

    private Unbinder unbinder;
    private LoginActivity activity;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private PopupWindow popupWindow;
    private int selectedGender = 0;

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

    @OnClick({R.id.next_btn, R.id.back_btn, R.id.birth_value, R.id.gender_value})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
                break;
            case R.id.next_btn:
                assert activity != null;
                activity.startSignUpVerificationFragment(sponsorValue.getText().toString(),
                        nameValue.getText().toString(), passportValue.getText().toString(),
                        selectedGender, birthValue.getText().toString(),
                        phoneValue.getText().toString());
                break;
            case R.id.gender_value:
                initCardPopWindows();
                break;
            case R.id.birth_value:
                createDatePicker();
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

    private void initCardPopWindows() {
        if (popupWindow != null) {
            popupWindow.showAsDropDown(genderValue,0,0, Gravity.BOTTOM);
            return;
        }

        View spinnerView = LayoutInflater.from(activity).inflate(R.layout.pop_gender, null);
        RecyclerView recyclerView = spinnerView.findViewById(R.id.pop_gender_rcv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        PopSpinnerRVAdapter adapter = new PopSpinnerRVAdapter(Arrays.asList(genderArr));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            genderValue.setText(genderArr[position]);
            selectedGender = position;
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        });

        int width = genderValue.getMeasuredWidth();

        popupWindow = new PopupWindow(spinnerView, width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(spinnerView);
        popupWindow.showAsDropDown(genderValue,0,0, Gravity.BOTTOM);
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }
}
