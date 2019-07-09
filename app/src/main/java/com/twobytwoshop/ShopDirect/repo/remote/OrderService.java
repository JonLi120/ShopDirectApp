package com.twobytwoshop.ShopDirect.repo.remote;

import com.twobytwoshop.ShopDirect.models.api.response.CarResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderResponse;
import com.twobytwoshop.ShopDirect.models.api.response.SearchOrderResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
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

    @FormUrlEncoded
    @POST("/v1/order/search")
    Single<SearchOrderResponse> searchOrder(@Field("uuid") String uuid);

    @FormUrlEncoded
    @POST("/v1/order/view")
    Single<OrderInfoResponse> getOrderInfo(@Field("OID") String oid);
}
