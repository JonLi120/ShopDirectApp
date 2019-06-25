package com.twobytwoshop.ShopDirect.repo.remote;

import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;

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
}
