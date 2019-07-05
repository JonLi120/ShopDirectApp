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

import com.twobytwoshop.ShopDirect.adapter.CategoryAdapter;
import com.twobytwoshop.ShopDirect.core.BaseFragment;
import com.twobytwoshop.ShopDirect.core.Injection;
import com.twobytwoshop.ShopDirect.core.ViewModelFactory;
import com.twobytwoshop.ShopDirect.viewmodel.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    private CategoryAdapter adapter;
    private ProductViewModel viewModel;
    private Unbinder unbinder;

    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
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

        viewModel.getCategory().observe(this, categoryResponse -> {
            if (categoryResponse.getCode().equals("100")) {
                adapter.replaceData(categoryResponse.getData());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv.setLayoutManager(new LinearLayoutManager(mActivity));
        rcv.setHasFixedSize(true);
        rcv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        adapter = new CategoryAdapter(null);
        rcv.setAdapter(adapter);

        viewModel.callCategory();

        adapter.setOnItemClickListener((adapter1, v, position)->{
            String caid = (String) v.getTag();
            ((MainActivity)mActivity).startCategoryListFragment(caid);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).changeMenuLayout(true, true);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}