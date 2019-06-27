package com.twobytwoshop.ShopDirect.repo.remote;

import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductService {

    @FormUrlEncoded
    @POST("/v1/product/home")
    Single<HomeResponse> getHomeData(@Field("uuid") String uuid);

    @FormUrlEncoded
    @POST("/v1/product/view")
    Single<ProductInfoResponse> getProductInfo(@Field("PID") String pid);

    @FormUrlEncoded
    @POST("/v1/product/category")
    Single<CategoryResponse> getCategory(@Field("VER") String ver);

    @FormUrlEncoded
    @POST("/v1/product/search")
    Single<ProductListResponse> getCategorySearch(@Field("CAID") String caid);

    @FormUrlEncoded
    @POST("/v1/product/gifts")
    Single<ProductListResponse> getGifts(@Field("VER") String ver);
}
