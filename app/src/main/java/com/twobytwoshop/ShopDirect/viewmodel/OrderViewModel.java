package com.twobytwoshop.ShopDirect.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.twobytwoshop.ShopDirect.core.BaseViewModel;
import com.twobytwoshop.ShopDirect.core.NetworkError;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.models.UserInfo;
import com.twobytwoshop.ShopDirect.models.api.response.CarResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderResponse;
import com.twobytwoshop.ShopDirect.models.api.response.SearchOrderResponse;
import com.twobytwoshop.ShopDirect.repo.ProductRepository;
import com.twobytwoshop.ShopDirect.repo.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OrderViewModel extends BaseViewModel {

    private ProductRepository repository;
    private UserRepository userRepository;

    private MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    private MutableLiveData<CarResponse> carInfo = new MutableLiveData<>();
    private MutableLiveData<OrderResponse> orderInfo = new MutableLiveData<>();
    private MutableLiveData<SearchOrderResponse> searchOrders = new MutableLiveData<>();
    private MutableLiveData<OrderInfoResponse> orderInfoResponse = new MutableLiveData<>();
    private MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();

    public MutableLiveData<CarResponse> getCarInfo() {
        return carInfo;
    }

    public MutableLiveData<List<Order>> getOrders() {
        return orders;
    }

    public MutableLiveData<OrderResponse> getOrderInfo() {
        return orderInfo;
    }

    public MutableLiveData<SearchOrderResponse> getSearchOrders() {
        return searchOrders;
    }

    public MutableLiveData<OrderInfoResponse> getOrderInfoResponse() {
        return orderInfoResponse;
    }

    public MutableLiveData<UserInfo> getUserInfo() {
        return userInfo;
    }

    public OrderViewModel(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public void getUser() {
        String id = sp.getUUID();
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
                    } else {
                        HashMap<String, Object> statusMap = new HashMap<>();
                        statusMap.put("tag", "car_info");
                        statusMap.put("code", response.getCode());
                        statusMap.put("content", getCarInfoStatus(response.getCode()));
                        status.postValue(statusMap);
                    }
                }, NetworkError::error));
    }

    public void buildOrder(Map<String, String> map) {
        disposable.add(repository.buildOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> {
                    if ("100".equals(response.getCode())) {
                        deleteOrderAll();
                        orderInfo.postValue(response);
                    } else {
                        HashMap<String, Object> statusMap = new HashMap<>();
                        statusMap.put("tag", "order_info");
                        statusMap.put("code", response.getCode());
                        statusMap.put("content", getOrderInfoStatus(response.getCode()));
                        status.postValue(statusMap);
                    }
                }, NetworkError::error));
    }

    private void deleteOrderAll() {
        sp.setOrderStatus(0);
        disposable.add(Single.fromCallable(() -> Single.just(repository.deleteAll())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    public void searchOrder() {
        String id = sp.getUUID();
        disposable.add(repository.searchOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response ->{
                    if ("100".equals(response.getCode())) {
                        searchOrders.postValue(response);
                    } else {
                        HashMap<String, Object> statusMap = new HashMap<>();
                        statusMap.put("tag", "order_search");
                        statusMap.put("code", response.getCode());
                        statusMap.put("content", getOrderSearchStatus(response.getCode()));
                        status.postValue(statusMap);
                    }
                }, NetworkError::error));
    }

    public void searchOrderInfo(String oid) {
        disposable.add(repository.getOrderInfo(oid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> setShowLoading(true, ""))
                .doFinally(() -> setShowLoading(false, ""))
                .subscribe(response -> {
                    if ("100".equals(response.getCode())) {
                        orderInfoResponse.postValue(response);
                    }
                }, NetworkError::error));
    }

    private String getCarInfoStatus(String code) {
        switch (code) {
            case "200":
                return "uuid 格式錯誤";
            case "210":
                return "uuid 找不到會員";
            case "220":
                return "uuid 狀態異常";
            case "300":
                return "PID 格式錯誤";
            case "310":
                return "isMD 格式錯誤";
            case "320":
                return "tran_way 格式錯誤";
            case "501":
                return "贈品與商品不能再同一訂單中";
        }
        return "";
    }

    private String getOrderInfoStatus(String code) {
        switch (code) {
            case "200":
                return "uuid 格式錯誤";
            case "210":
                return "uuid 找不到會員";
            case "220":
                return "uuid 狀態異常";
            case "300":
                return "PID 格式錯誤";
            case "310":
                return "isMD 格式錯誤";
            case "320":
                return "tran_way 格式錯誤";
            case "401":
                return "訂購人 資料格式錯誤";
            case "402":
                return "收件人 資料格式錯誤";
            case "501":
                return "贈品與商品不能再同一訂單中";
            case "502":
                return "非 MD身分 不能只用電子錢包";
            case "503":
                return "pay_way 只能輸入 10 or 20";
            case "504":
                return "使用電子錢包付款,電子錢包餘額不足";
            case "505":
                return "兌換贈品點數不足";
        }
        return "";
    }

    private String getOrderSearchStatus(String code) {
        switch (code) {
            case "200":
                return "uuid 格式錯誤";
            case "201":
                return "找不到此uuid的會員";
            case "202":
                return "會員狀態異常常";
        }
        return "";
    }
}
