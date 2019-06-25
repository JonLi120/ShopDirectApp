package com.twobytwoshop.ShopDirect.repo;

import com.twobytwoshop.ShopDirect.models.api.response.LoginResponse;
import com.twobytwoshop.ShopDirect.models.api.response.RegisterResponse;
import com.twobytwoshop.ShopDirect.repo.remote.RetrofitClient;
import com.twobytwoshop.ShopDirect.repo.remote.UserService;

import java.util.Map;

import io.reactivex.Single;

public class UserRepository {
    private static UserRepository repository;
    private UserService service;


    public static UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    private UserRepository() {
        service = RetrofitClient.getInstance().getUserService();
//        userDao = db.userDao();
        repository = this;
    }

    public Single<LoginResponse> login(String acc, String pwd){
        return service.login(acc, pwd);
    }

    public Single<RegisterResponse> signUpCheck(Map<String, String> params){
        return service.signUpCheck(params);
    }

    public Single<RegisterResponse> signUp(Map<String, String> params) {
        return service.signUp(params);
    }
}
