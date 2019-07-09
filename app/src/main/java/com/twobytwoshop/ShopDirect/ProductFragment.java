package com.twobytwoshop.ShopDirect;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.utils.KeyboardUtil;
import com.twobytwoshop.ShopDirect.viewmodel.ProductViewModel;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductFragment extends BaseFragment {

    private static final String KEY_PID = "KEY_PID";
    private static final String KEY_TYPE = "KEY_TYPE";

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
    private int qut = 1;
    private int type;
    private ProductInfoResponse.DataBean product;

    static ProductFragment newInstance(String pid, int type) {
        Bundle args = new Bundle();
        args.putString(KEY_PID, pid);
        args.putInt(KEY_TYPE, type);

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
            type = bundle.getInt(KEY_TYPE);
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

        viewModel.showLoading.observe(this, showLoadingBean -> {
            if (showLoadingBean.isShow()) {
                ((MainActivity) mActivity).showLoadingDialog(showLoadingBean.getContent());
            } else {
                ((MainActivity) mActivity).dismissLoadingDialog();
            }
        });

        viewModel.getProduct().observe(this, response -> {
            if (response.getCode().equals("100")) {
                product = response.getData();
                Glide.with(this)
                        .load(String.format(websiteFormat, response.getData().getMain_image()))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(productImg);

                productLab.setText(response.getData().getTitle());

                String exchange = response.getData().getExchange();
                String content = response.getData().getContent();

                if (content == null || content.isEmpty()) {
                    content = "No product description.";
                }

                if (exchange != null && !exchange.isEmpty()) {
                    productNumber.setVisibility(View.GONE);
                    productDiscountPrice.setVisibility(View.GONE);
                    productPrice.setText(response.getData().getExchange());
                } else {
                    productNumber.setText(String.format(numberFormat, response.getData().getSn()));
                    productPrice.setText(String.format(priceFormat, response.getData().getFixprice()));
                    productDiscountPrice.setText(String.format(priceFormat, response.getData().getPrice()));
                    productPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productDescription.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    productDescription.setText(Html.fromHtml(content));
                }
            }
        });

        viewModel.status.observe(this, map -> {
            if ("insert order".equals(map.get("tag"))) {
                if ((int) map.get("code") < 0) {
                    Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    activity.addShopCar();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.callProductInfo(pid);

        productQutValue.addTextChangedListener(listener);

        productQutValue.setOnFocusChangeListener((view1, b) -> {
            if (!b) {
                if (productQutValue.getText().toString().isEmpty()) {
                    productQutValue.setText("1");
                }
                KeyboardUtil.hideShowKeyboard(view1, mActivity);
            }
        });
    }

    @OnClick({R.id.product_add_car, R.id.product_qut_plus, R.id.product_qut_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_add_car:
                viewModel.insertOrder(createOrder(), type);
                break;
            case R.id.product_qut_plus:
                qut++;
                productQutValue.setText(String.valueOf(qut));
                break;
            case R.id.product_qut_sub:
                qut--;
                if (qut <= 0) {
                    qut = 1;
                }
                productQutValue.setText(String.valueOf(qut));
                break;
        }
    }

    private TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            productQutValue.removeTextChangedListener(this);
            String num = editable.toString().replace(".", "");
            if (num.length() != 0) {
                qut = Integer.valueOf(num);
            }
            editable.clear();
            editable.append(num);
            productQutValue.addTextChangedListener(this);
        }
    };

    private Order createOrder() {
        if (product == null) {
            return null;
        }
        Order order = new Order();
        order.setImg(product.getMain_image());
        order.setPid(product.getPID());
        if (type == 1)
            order.setPrice(product.getPrice());
        else
            order.setPrice(product.getExchange());
        order.setQut(Integer.valueOf(productQutValue.getText().toString()));
        order.setTag(type);
        order.setTitle(product.getTitle());
        return order;
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }
}
