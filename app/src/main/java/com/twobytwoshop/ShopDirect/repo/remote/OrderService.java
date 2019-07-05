package com.twobytwoshop.ShopDirect.repo.remote;

import com.twobytwoshop.ShopDirect.models.api.response.CarResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderService {

    @FormUrlEncoded
    @POST("/v1/order/cart")
    Single<CarResponse> callCarInfo(@FieldMap Map<String, String> parameter);

    @FormUrlEncoded
    @POST("/v1/order/build")
    Single<OrderResponse> buildOrder(@FieldMap Map<String, String> parameter);
}
