package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductViewModel extends BaseViewModel {

    private ProductRepository repository;

    private MutableLiveData<ProductInfoResponse> product = new MutableLiveData<>();
    private MutableLiveData<CategoryResponse> category = new MutableLiveData<>();
    private MutableLiveData<ProductListResponse> categorySearched = new MutableLiveData<>();
    private MutableLiveData<ProductListResponse> gifts = new MutableLiveData<>();

    public MutableLiveData<ProductListResponse> getGifts() {
        return gifts;
    }

    public MutableLiveData<ProductListResponse> getCategorySearched() {
        return categorySearched;
    }

    public MutableLiveData<ProductInfoResponse> getProduct() {
        return product;
    }

    public MutableLiveData<CategoryResponse> getCategory() {
        return category;
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

    public void callCategory() {
        disposable.add(repository.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> category.postValue(response), NetworkError::error));
    }

    public void callSearchCategory(String caid) {
        disposable.add(repository.getCategorySearch(caid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> categorySearched.postValue(response), NetworkError::error));
    }

    public void callGifts() {
        disposable.add(repository.getGifts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> gifts.postValue(response), NetworkError::error));
    }
}
