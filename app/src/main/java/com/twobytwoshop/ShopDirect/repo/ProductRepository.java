package com.twobytwoshop.ShopDirect.repo;

import com.twobytwoshop.ShopDirect.BuildConfig;
import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;
import com.twobytwoshop.ShopDirect.repo.remote.ProductService;
import com.twobytwoshop.ShopDirect.repo.remote.RetrofitClient;

import io.reactivex.Single;

public class ProductRepository {
    private static ProductRepository repository;
    private ProductService service;

    public static ProductRepository getInstance() {

        if (repository == null) {
            repository = new ProductRepository();
        }

        return repository;
    }

    public ProductRepository() {
        service = RetrofitClient.getInstance().getProductService();
        repository = this;
    }

    public Single<HomeResponse> getHomeData(String uuid) {
        return service.getHomeData(uuid);
    }

    public Single<ProductInfoResponse> getProductInfo(String pid) {
        return service.getProductInfo(pid);
    }

    public Single<CategoryResponse> getCategory() {
        return service.getCategory(BuildConfig.VERSION_NAME);
    }

    public Single<ProductListResponse> getCategorySearch(String caid) {
        return service.getCategorySearch(caid);
    }

    public Single<ProductListResponse> getGifts() {
        return service.getGifts(BuildConfig.VERSION_NAME);
    }
}
