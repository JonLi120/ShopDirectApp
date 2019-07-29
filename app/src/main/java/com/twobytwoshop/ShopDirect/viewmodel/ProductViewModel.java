package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ProductViewModel extends BaseViewModel {

    private ProductRepository repository;

    private MutableLiveData<ProductInfoResponse> product = new MutableLiveData<>();
    private MutableLiveData<CategoryResponse> category = new MutableLiveData<>();
    private MutableLiveData<ProductListResponse> categorySearched = new MutableLiveData<>();
    private MutableLiveData<ProductListResponse> gifts = new MutableLiveData<>();
    private MutableLiveData<Integer> orderCount = new MutableLiveData<>();

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

    public MutableLiveData<Integer> getOrderCount() {
        return orderCount;
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

    public void insertOrder(Order order, int type) {
        int s = sp.getOrderStatus();
        if (s != 0 && type != s) {
            Map<String, Object> map = new HashMap<>();
            map.put("tag", "insert order");
            map.put("code", -1);
            map.put("content", "贈品不能與一般商品同購物車");
            status.postValue(map);
            return;
        } else if (order == null){
            Map<String, Object> map = new HashMap<>();
            map.put("tag", "insert order");
            map.put("code", -2);
            map.put("content", "新增購物車錯誤");
            status.postValue(map);
        }
        disposable.add(Single.fromCallable(() -> repository.insertOrder(order))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(aLong -> {
                    if (aLong >= 0) {
                        sp.setOrderStatus(type);
                        Map<String, Object> map = new HashMap<>();
                        map.put("tag", "insert order");
                        map.put("code", aLong.intValue());
                        status.postValue(map);
                    }
                }, NetworkError::error));
    }

    public void getOrderProductCount() {
        disposable.add(Single.fromCallable(() -> repository.getProductCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(count -> orderCount.postValue(count), NetworkError::error));
    }

    public void logout() {
        disposable.add(Single.fromCallable(() -> Single.just(repository.deleteAll()))
                .subscribeOn(Schedulers.io())
                .flatMap((Function<Single<Integer>, SingleSource<Integer>>) num ->{
                    sp.setUUID("");
                    sp.setName("");
                    sp.setLogin(false);
                    sp.setMDID(0);
                    sp.setCode("");
                    sp.setOrderStatus(0);
                    return num;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tag", "logout");
                    map.put("code", "100");
                    status.postValue(map);
                }));
    }
}
