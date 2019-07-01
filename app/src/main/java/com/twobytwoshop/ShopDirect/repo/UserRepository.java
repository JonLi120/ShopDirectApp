package com.twobytwoshop.ShopDirect.repo;

import com.twobytwoshop.ShopDirect.models.UserInfo;
import com.twobytwoshop.ShopDirect.models.api.response.BaseResponse;
import com.twobytwoshop.ShopDirect.models.api.response.LoginResponse;
import com.twobytwoshop.ShopDirect.models.api.response.RegisterResponse;
import com.twobytwoshop.ShopDirect.models.api.response.UserInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.WalletResponse;
import com.twobytwoshop.ShopDirect.repo.local.AppDatabase;
import com.twobytwoshop.ShopDirect.repo.local.UserDao;
import com.twobytwoshop.ShopDirect.repo.remote.RetrofitClient;
import com.twobytwoshop.ShopDirect.repo.remote.UserService;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class UserRepository {
    private static UserRepository repository;
    private UserService service;
    private final UserDao userDao;

    public static UserRepository getInstance(AppDatabase db) {
        if (repository == null) {
            repository = new UserRepository(db);
        }
        return repository;
    }

    private UserRepository(AppDatabase db) {
        service = RetrofitClient.getInstance().getUserService();
        userDao = db.userDao();
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

    public Single<UserInfoResponse> getUser(String uuid) {
        return service.getUser(uuid);
    }

    public Single<BaseResponse> updateUser(Map<String, String> params) {
        return service.updateUser(params);
    }

    public Single<BaseResponse> changePwd(String uuid, String old, String new_) {
        return service.changePassword(uuid, old, new_);
    }

    public Single<WalletResponse> storedValue(String uuid, String price) {
        return service.storedValue(uuid, price);
    }

    public List<UserInfo> searchUser(String uuid) {
        return userDao.searchByUUID(uuid);
    }

    public void insertUser(UserInfo user) {
        userDao.insert(user);
    }

    public int deleteUser(UserInfo user) {
        return userDao.delete(user);
    }

    public void updateUser(UserInfo user) {
        userDao.update(user);
    }
}
