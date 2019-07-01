package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.models.api.response.CarResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OrderViewModel extends BaseViewModel {

    private ProductRepository repository;

    private MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    private MutableLiveData<CarResponse> carInfo = new MutableLiveData<>();

    public MutableLiveData<CarResponse> getCarInfo() {
        return carInfo;
    }

    public MutableLiveData<List<Order>> getOrders() {
        return orders;
    }

    public OrderViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public void getOrder() {
        disposable.add(Single.fromCallable(() -> repository.getOrderByGroup())
                .subscribeOn(Schedulers.io())
                .flatMap((Function<List<Order>, SingleSource<List<Order>>>) orders -> {
                    rebaseOrderData(orders);
                    return Single.just(orders);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(ordersList -> orders.postValue(ordersList), NetworkError::error));
    }

    private void rebaseOrderData(List<Order> list) {
        for (Order item : list) {
            repository.deleteByPID(item.getPid());
            item.setQut(item.getTotal());
            repository.insertOrder(item);
        }
    }

    public void deleteOrder(String pid) {
        disposable.add(Single.fromCallable(() -> repository.deleteByPID(pid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(num -> {
                    if (num > 0) getOrder();
                }, NetworkError::error));
    }

    public void updateOrderById(String pid, int num) {
        disposable.add(Single.fromCallable(() -> repository.updateOrderById(pid, num))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(n -> {
                    if (n > 0) getOrder();
                }, NetworkError::error));
    }

    public void callCarInfo(Map<String, String> map) {
        disposable.add(repository.callCarInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> {
                    if ("100".equals(response.getCode())) {
                        carInfo.postValue(response);
                    }
                }, NetworkError::error));
    }
}
