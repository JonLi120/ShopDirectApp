package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twobytwoshop.ShopDirect.adapter.HomeProductAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.models.Product;
import com.twobytwoshop.ShopDirect.utils.CustomImageLoader;
import com.twobytwoshop.ShopDirect.viewmodel.HomeViewModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
    @BindString(R.string.format_website)
    String websiteFormat;

    private Unbinder unbinder;
    private MainActivity activity;
    private HomeViewModel viewModel;
    private HomeProductAdapter adapter;

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
        setViewModel();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);

        viewModel.showLoading.observe(this, showLoadingBean -> {
            if (showLoadingBean.isShow()) {
                activity.showLoadingDialog(showLoadingBean.getContent());
            } else {
                activity.dismissLoadingDialog();
            }
        });

        viewModel.getHomeData().observe(this, homeResponse -> {
            String path = String.format(websiteFormat, homeResponse.getData().getImage().getSrc());
            homeBanner.setImages(Collections.singletonList(path));
            homeBanner.start();

            adapter.setData(homeResponse.getData().getProducts());
            adapter.notifyDataSetChanged();
        });

        viewModel.status.observe(this, map -> {
            if ("user".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("context"), Toast.LENGTH_LONG).show();
                activity.logout();
            }
        });
    }

    private void initView() {
        viewModel.callHomeData();
        viewModel.callGetUser();

        homeHeaderLab.setText(String.format(member_format, sp.getUUID()));

        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        homeBanner.setImageLoader(new CustomImageLoader());

        homeBanner.isAutoPlay(true);
        homeBanner.setDelayTime(4000);
        homeBanner.setIndicatorGravity(BannerConfig.CENTER);
        homeBanner.setOnBannerListener(position -> Log.d("test", "initView: " + position));

        homeRcv.setHasFixedSize(true);
        homeRcv.setLayoutManager(new LinearLayoutManager(mActivity));
        homeRcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        adapter = new HomeProductAdapter(homeRcv);
        homeRcv.setAdapter(adapter);
        adapter.setOnItemClickListener(((view, position) -> {
            activity.startProductFragment((String) view.getTag());
            activity.changeMenuLayout(true, false);
        }));
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
