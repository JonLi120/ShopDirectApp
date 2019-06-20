package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twobytwoshop.ShopDirect.adapter.HomeProductAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.models.Product;
import com.twobytwoshop.ShopDirect.utils.CustomImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_header_lab)
    TextView homeHeaderLab;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_rcv)
    RecyclerView homeRcv;
    @BindString(R.string.home_fragment_head_member)
    String member_format;
    @BindString(R.string.home_fragment_head_proxy)
    String proxy_format;

    private Unbinder unbinder;
    private long id = 312013;
    private Integer[] images = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};
    private MainActivity activity;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (MainActivity) mActivity;
        initView();
        return view;
    }

    private void initView() {
        homeHeaderLab.setText(String.format(member_format, id));

        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        homeBanner.setImageLoader(new CustomImageLoader());
        homeBanner.setImages(Arrays.asList(images));
        homeBanner.isAutoPlay(true);
        homeBanner.setDelayTime(4000);
        homeBanner.setIndicatorGravity(BannerConfig.CENTER);
        homeBanner.setOnBannerListener(position -> Log.d("test", "initView: " + position));
        homeBanner.start();

        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Product item = new Product();
            item.setTitle("測試測試測試測試測試測試測試測試測試測試測試 " + i);
            item.setPrice(1000);
            item.setDiscount_price(99.9f);
            item.setPp(2120);
            item.setCp(980);
            item.setFavorite(i % 2 == 0);
            list.add(item);
        }

        homeRcv.setHasFixedSize(true);
        homeRcv.setLayoutManager(new LinearLayoutManager(mActivity));
        homeRcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        HomeProductAdapter adapter = new HomeProductAdapter(homeRcv);
        homeRcv.setAdapter(adapter);
        adapter.setData(list);
        adapter.setOnItemClickListener(((view, position) -> {
            activity.startProductFragment();
            activity.changeMenuLayout(true, false);
        }));
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
