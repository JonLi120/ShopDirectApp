package com.twobytwoshop.ShopDirect.repo.remote;

import com.twobytwoshop.ShopDirect.models.api.response.LoginResponse;
import com.twobytwoshop.ShopDirect.models.api.response.RegisterResponse;
import com.twobytwoshop.ShopDirect.models.api.response.UserInfoResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("/v1/user/view")
    Single<UserInfoResponse> getUser(@Field("uuid") String uuid);

    @FormUrlEncoded
    @POST("/v1/user/login")
    Single<LoginResponse> login(@Field("uuid") String account, @Field("password") String password);

    @FormUrlEncoded
    @POST("/v1/user/register/check")
    Single<RegisterResponse> signUpCheck(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/v1/user/register/insert")
    Single<RegisterResponse> signUp(@FieldMap Map<String, String> params);


}
