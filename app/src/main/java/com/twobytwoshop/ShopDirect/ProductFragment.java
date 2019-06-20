package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.twobytwoshop.ShopDirect.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductFragment extends BaseFragment {

    @BindView(R.id.product_img)
    ImageView productImg;
    @BindView(R.id.product_lab)
    TextView productLab;
    @BindView(R.id.product_number)
    TextView productNumber;
    @BindView(R.id.product_price)
    TextView productPrice;
    @BindView(R.id.product_discount_price)
    TextView productDiscountPrice;
    @BindView(R.id.product_qut_lab)
    TextView productQutLab;
    @BindView(R.id.product_qut_sub)
    ImageButton productQutSub;
    @BindView(R.id.product_qut_plus)
    ImageButton productQutPlus;
    @BindView(R.id.product_qut_value)
    EditText productQutValue;
    @BindView(R.id.product_add_car)
    Button productAddCar;
    @BindView(R.id.product_add_wish)
    Button productAddWish;
    @BindView(R.id.product_description_lab)
    TextView productDescriptionLab;
    @BindView(R.id.product_description)
    TextView productDescription;

    private Unbinder unbinder;
    private MainActivity activity;

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (MainActivity) mActivity;
        return view;
    }

    @OnClick({R.id.product_add_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_add_car:
                activity.addCarCount();
                break;
        }
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }
}
