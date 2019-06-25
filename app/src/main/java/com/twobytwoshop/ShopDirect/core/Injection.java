package com.twobytwoshop.ShopDirect.core;

import android.content.Context;

import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;

public class Injection {
    public static ViewModelFactory provideViewModelFactory(Context context) {
//        AppDatabase database = AppDatabase.getInstance(context);
        UserRepository userRepository = UserRepository.getInstance();
        ProductRepository productRepository = ProductRepository.getInstance();

        return new ViewModelFactory(userRepository, productRepository);
    }
}
