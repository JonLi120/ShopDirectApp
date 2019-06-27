package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.repo.UserRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private UserRepository userRepository;

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void callLogin(String acc, String pwd) {
        disposable.add(userRepository.login(acc, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(loginResponse -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "login");
                    map.put("code", loginResponse.getCode());
                    map.put("content", getLoginStatusContext(loginResponse.getCode()));
                    map.put("uuid", acc);
                    status.postValue(map);
                }, NetworkError::error));
    }

    public void checkRegister(String sponsor, String name, String nric, String gender, String birthday,
                              String phone, String sp_name, String sp_ic, String state, String postcode, String address) {

        Map<String, String> params = getRegisterMap(sponsor, name, nric, gender, birthday, phone, sp_name, sp_ic, state, postcode, address);
        disposable.add(userRepository.signUpCheck(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(registerResponse -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "check_register");
                    map.put("code", registerResponse.getCode());
                    map.put("content", getRegisterStatusContext(registerResponse.getCode()));
                    status.postValue(map);
                }, NetworkError::error)
        );
    }

    public void registerUser(String sponsor, String name, String nric, String gender, String birthday,
                             String phone, String sp_name, String sp_ic, String state, String postcode, String address) {
        Map<String, String> params = getRegisterMap(sponsor, name, nric, gender, birthday, phone, sp_name, sp_ic, state, postcode, address);
        disposable.add(userRepository.signUp(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(registerResponse -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "insert_register");
                    map.put("code", registerResponse.getCode());
                    map.put("content", getRegisterStatusContext(registerResponse.getCode()));
                    map.put("uuid", registerResponse.getUuid());
                    status.postValue(map);
                }, NetworkError::error)
        );
    }

    private Map<String, String> getRegisterMap(String sponsor, String name, String nric, String gender, String birthday,
                                               String phone, String sp_name, String sp_ic, String state, String postcode, String address) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sponsor", sponsor);
        map.put("name", name);
        map.put("NRIC_Passport_No", nric);
        map.put("gender", gender);
        map.put("birthday", birthday);
        map.put("Mobile_Nos", phone);
        map.put("spouse_name", sp_name);
        map.put("spouse_ic", sp_ic);
        map.put("state", state);
        map.put("postcode", postcode);
        map.put("address", address);
        return map;
    }

    private String getLoginStatusContext(String code) {
        switch (code) {
            case "100":
                return "登入成功";
            case "200":
                return "請輸入帳號欄位";
            case "205":
                return "請輸入密碼欄位";
            case "210":
                return "找不到此會員";
            case "220":
                return "會員狀態異常";
            case "230":
                return "密碼錯誤";
        }
        return "意外錯誤";
    }

    private String getRegisterStatusContext(String code) {
        switch (code) {
            case "100":
                return "成功";
            case "200":
                return "sponsor 格式錯誤";
            case "210":
                return "找不到此 sponsor 的 User Data";
            case "220":
                return "會員 狀態異常";
            case "230":
                return "會員 name 不能為空";
            case "240":
                return "會員 性別只能填寫 M or F";
            case "310":
                return "NRIC_Passport_No 格式錯誤";
            case "320":
                return "NRIC_Passport_No 重覆";
            case "410":
                return "spouse_ic 格式錯誤";
            case "420":
                return "spouse_ic 重覆";
            case "510":
                return "postcode 格式錯誤";
        }
        return "意外錯誤";
    }
}
