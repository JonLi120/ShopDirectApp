package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.UserInfo;
import com.twobytwoshop.ShopDirect.models.api.response.BaseResponse;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.models.api.response.UserInfoResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    private MutableLiveData<HomeResponse> homeData = new MutableLiveData<>();
    private MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();
    private final String id;

    public MutableLiveData<HomeResponse> getHomeData() {
        return homeData;
    }

    public MutableLiveData<UserInfo> getUserInfo() {
        return userInfo;
    }

    public HomeViewModel(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        id = sp.getUUID();
    }

    public void callHomeData() {
        disposable.add(productRepository.getHomeData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(homeResponse -> {
                    if (homeResponse.getCode().equals("100")) {
                        homeData.postValue(homeResponse);
                    }
                }, NetworkError::error)
        );
    }

    public void callGetUser() {
        disposable.add(userRepository.getUser(id)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<UserInfoResponse, SingleSource<Integer>>) userInfoResponse -> {
                    int value = 0;
                    if (userInfoResponse.getCode().equals("100")) {
                        userRepository.deleteUser(userInfoResponse.getData());
                        userRepository.insertUser(userInfoResponse.getData());
                        sp.setMDID(Integer.valueOf(userInfoResponse.getData().getMdid()));
                        sp.setCode(userInfoResponse.getData().getCODE());
                    } else if (userInfoResponse.getCode().equals("220")){
                        value = -220;
                    }
                    return Single.just(value);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(code ->{
                    if (code == -220) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tag", "user");
                        map.put("content", "會員狀態異常");
                        status.postValue(map);
                    }
                }, NetworkError::error)
        );
    }

    public void getUser() {
        disposable.add(Single.fromCallable(() -> userRepository.searchUser(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(list -> {
                    if (list.size() > 0) {
                        userInfo.postValue(list.get(0));
                    }
                }, NetworkError::error));
    }

    public void callUpdateUser(UserInfo user) {
        disposable.add(userRepository.updateUser(getUpdateUserMap(user))
                .subscribeOn(Schedulers.io())
                .flatMap((Function<BaseResponse, SingleSource<String>>) response ->{
                    if ("100".equals(response.getCode())) {
                        userRepository.updateUser(user);
                    }
                    return Single.just(response.getCode());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(code ->{
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "user update");
                    map.put("content", getUpdateUserState(code));
                    status.postValue(map);
                }, NetworkError::error));
    }

    public void callChangePwd(String old, String new_) {
        disposable.add(userRepository.changePwd(id, old, new_)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "change password");
                    map.put("code", response.getCode());
                    map.put("content", getChangeState(response.getCode()));
                    status.postValue(map);
                }, NetworkError::error));
    }

    public void callStoreValue(String price) {
        disposable.add(userRepository.storedValue(id, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "wallet");
                    map.put("code", response.getCode());
                    map.put("content", getWalletState(response.getCode()));

                    if (response.getCode().equals("100")) {
                        map.put("url", response.getUrl());
                    }
                    status.postValue(map);
                }, NetworkError::error));
    }

    private Map<String, String> getUpdateUserMap(UserInfo user) {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", id);
        map.put("gender", user.getGender());
        map.put("marital", user.getMarital());
        map.put("birthday", user.getBirthday());
        map.put("wechatID", user.getWechatID());
        map.put("email", user.getEmail());
        map.put("Home_Nos", user.getHome_Nos());
        map.put("Office_Nos", user.getOffice_Nos());
        map.put("Mobile_Nos", user.getMobile_Nos());
        map.put("state", user.getState());
        map.put("postcode", user.getPostcode());
        map.put("address", user.getAddress());
        return map;
    }

    private String getUpdateUserState(String code) {
        switch (code) {
            case "100":
                return "更新成功";
            case "200":
                return "uuid格式錯誤";
            case "210":
                return "找不到此uuid的會員";
            case "220":
                return "會員狀態異常";
            case "900":
                return "資料庫異常";
        }
        return "";
    }

    private String getChangeState(String code) {
        switch (code) {
            case "100":
                return "更新成功";
            case "200":
                return "uuid格式錯誤";
            case "210":
                return "找不到此uuid的會員";
            case "220":
                return "會員狀態異常";
            case "301":
                return "oldpass 為空";
            case "302":
                return "newpass 為空";
            case "303":
                return "oldpass 與原本設定的密碼不同";
            case "900":
                return "資料庫異常";
        }
        return "";
    }

    private String getWalletState(String code) {
        switch (code) {
            case "201":
                return "uuid格式錯誤";
            case "203":
                return "找不到此uuid的會員";
            case "202":
                return "會員狀態異常";
            case "301":
                return "儲值訂單建立失敗！";
        }
        return "";
    }
}
