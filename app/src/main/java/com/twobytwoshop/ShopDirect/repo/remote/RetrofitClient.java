package com.twobytwoshop.ShopDirect.repo.remote;

import retrofit2.Retrofit;

public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private UserService userService;
    private ProductService productService;

    private RetrofitClient() {
        retrofit = RetrofitBuilder.buildRetrofit();

        userService = create(UserService.class);
        productService = create(ProductService.class);
    }

    public synchronized static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    private <T> T create(Class<T> clz) {
        return retrofit.create(clz);
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }
}
