package com.twobytwoshop.ShopDirect.core;

import android.content.Context;

import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;
import com.twobytwoshop.ShopDirect.repo.local.AppDatabase;

public class Injection {
    public static ViewModelFactory provideViewModelFactory(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        UserRepository userRepository = UserRepository.getInstance(db);
        ProductRepository productRepository = ProductRepository.getInstance(db);

        return new ViewModelFactory(userRepository, productRepository);
    }
}
