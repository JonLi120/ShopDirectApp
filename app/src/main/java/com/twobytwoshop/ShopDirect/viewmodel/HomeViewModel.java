package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
}
