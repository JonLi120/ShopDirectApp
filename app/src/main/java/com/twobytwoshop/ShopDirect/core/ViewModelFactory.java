package com.twobytwoshop.ShopDirect.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;
import com.twobytwoshop.ShopDirect.viewmodel.HomeViewModel;
import com.twobytwoshop.ShopDirect.viewmodel.LoginViewModel;
import com.twobytwoshop.ShopDirect.viewmodel.OrderViewModel;
import com.twobytwoshop.ShopDirect.viewmodel.ProductViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    ViewModelFactory(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return modelClass.cast(new LoginViewModel(userRepository));
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return modelClass.cast(new HomeViewModel(userRepository, productRepository));
        } else if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return modelClass.cast(new ProductViewModel(productRepository));
        } else if (modelClass.isAssignableFrom(OrderViewModel.class)) {
            return modelClass.cast(new OrderViewModel(productRepository));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
