package com.twobytwoshop.ShopDirect;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
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
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.ProductViewModel;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductFragment extends BaseFragment {

    private static final String KEY_PID = "KEY_PID";

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

    @BindString(R.string.format_website)
    String websiteFormat;
    @BindString(R.string.product_fragment_number_format)
    String numberFormat;
    @BindString(R.string.item_product_price)
    String priceFormat;

    private Unbinder unbinder;
    private MainActivity activity;
    private String pid;
    private ProductViewModel viewModel;

    public static ProductFragment newInstance(String pid) {
        Bundle args = new Bundle();
        args.putString(KEY_PID, pid);

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            pid = bundle.getString(KEY_PID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (MainActivity) mActivity;

        setViewModel();
        return view;
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(ProductViewModel.class);

        viewModel.getProduct().observe(this, response -> {
            if (response.getCode().equals("100")) {
                Glide.with(this)
                        .load(String.format(websiteFormat, response.getData().getMain_image()))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(productImg);

                productLab.setText(response.getData().getTitle());
                productNumber.setText(String.format(numberFormat, response.getData().getSn()));
                productPrice.setText(String.format(priceFormat, response.getData().getFixprice()));
                productDiscountPrice.setText(String.format(priceFormat, response.getData().getPrice()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productDescription.setText(Html.fromHtml(response.getData().getContent(), Html.FROM_HTML_MODE_LEGACY));
                } else {
                    productDescription.setText(Html.fromHtml(response.getData().getContent()));
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.callProductInfo(pid);
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
