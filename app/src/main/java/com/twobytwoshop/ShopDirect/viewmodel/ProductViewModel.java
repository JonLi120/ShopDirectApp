package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductViewModel extends BaseViewModel {

    private ProductRepository repository;

    private MutableLiveData<ProductInfoResponse> product = new MutableLiveData<>();

    public MutableLiveData<ProductInfoResponse> getProduct() {
        return product;
    }

    public ProductViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public void callProductInfo(String pid) {
        disposable.add(repository.getProductInfo(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(productInfoResponse -> product.postValue(productInfoResponse), NetworkError::error));
    }
}
