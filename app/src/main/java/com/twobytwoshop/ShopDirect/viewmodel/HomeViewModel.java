package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
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

    public MutableLiveData<HomeResponse> getHomeData() {
        return homeData;
    }

    public HomeViewModel(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void callHomeData() {
        String id = sp.getUUID();
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
        String id = sp.getUUID();
        disposable.add(userRepository.getUser(id)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<UserInfoResponse, SingleSource<Integer>>) userInfoResponse -> {
                    int value = 0;
                    if (userInfoResponse.getCode().equals("100")) {
                        userRepository.deleteUser(userInfoResponse.getData());
                        userRepository.insertUser(userInfoResponse.getData());
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
}
