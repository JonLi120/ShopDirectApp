package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twobytwoshop.ShopDirect.adapter.SearchOrderAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.none_data_lab)
    TextView noneDataLab;
    private Unbinder unbinder;
    private SearchOrderAdapter adapter;
    private OrderViewModel viewModel;

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) mActivity).changeMenuLayout(true, false);

        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);

        viewModel.showLoading.observe(this, bean -> {
            if (bean.isShow()) {
                ((MainActivity) mActivity).showLoadingDialog(bean.getContent());
            } else {
                ((MainActivity) mActivity).dismissLoadingDialog();
            }
        });

        viewModel.getSearchOrders().observe(this, orders -> {
            if (orders != null) {
                if (orders.getData() != null && orders.getData().size() > 0) {
                    adapter.replaceData(orders.getData());
                    noneDataLab.setVisibility(View.GONE);
                } else {
                    noneDataLab.setVisibility(View.VISIBLE);
                    noneDataLab.setText("目前無相關訂單資訊");
                }
            }
        });

        viewModel.status.observe(this, map -> {
            if ("order_search".equals(map.get("tag"))) {
                Toast.makeText(mActivity, (String) map.get("content"), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.searchOrder();

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(mActivity));
        rcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        adapter = new SearchOrderAdapter(null);
        rcv.setAdapter(adapter);

        adapter.setOnItemClickListener(((adapter1, view1, position) -> ((MainActivity) mActivity).startOrderInfoFragment((String) view1.getTag())));
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
