package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twobytwoshop.ShopDirect.adapter.ProductListAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends BaseFragment {

    private static final String KEY_CAID = "KEY_CAID";

    @BindView(R.id.rcv)
    RecyclerView rcv;
    private Unbinder unbinder;
    private ProductListAdapter adapter;
    private ProductViewModel viewModel;
    private String caid ="";

    public static ProductListFragment newInstance(String caid) {
        Bundle args = new Bundle();
        args.putString(KEY_CAID, caid);

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if (bundle != null) {
            caid = bundle.getString(KEY_CAID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setViewModel();
        return view;
    }

    private void setViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(mActivity);
        viewModel = ViewModelProviders.of(this, factory).get(ProductViewModel.class);

        viewModel.getCategorySearched().observe(this, response -> {
            if (response.getCode().equals("100")) {
                adapter.replaceData(response.getData());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        viewModel.callSearchCategory(caid);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(mActivity));
        rcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        adapter = new ProductListAdapter(null);
        rcv.setAdapter(adapter);
        adapter.setOnItemClickListener(((adapter1, view, position) -> {
            ((MainActivity)mActivity).startProductFragment((String) view.getTag(), 1);
//            activity.changeMenuLayout(true, false);
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).changeMenuLayout(true, false);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
