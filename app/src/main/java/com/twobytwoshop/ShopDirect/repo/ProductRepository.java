package com.twobytwoshop.ShopDirect.repo;

import com.twobytwoshop.ShopDirect.BuildConfig;
import com.twobytwoshop.ShopDirect.models.Order;
import com.twobytwoshop.ShopDirect.models.api.response.CarResponse;
import com.twobytwoshop.ShopDirect.models.api.response.CategoryResponse;
import com.twobytwoshop.ShopDirect.models.api.response.HomeResponse;
import com.twobytwoshop.ShopDirect.models.api.response.OrderResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductInfoResponse;
import com.twobytwoshop.ShopDirect.models.api.response.ProductListResponse;
import com.twobytwoshop.ShopDirect.repo.local.AppDatabase;
import com.twobytwoshop.ShopDirect.repo.local.OrderDao;
import com.twobytwoshop.ShopDirect.repo.remote.OrderService;
import com.twobytwoshop.ShopDirect.repo.remote.ProductService;
import com.twobytwoshop.ShopDirect.repo.remote.RetrofitClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class ProductRepository {
    private static ProductRepository repository;
    private ProductService service;
    private OrderService orderService;
    private OrderDao orderDao;

    public static ProductRepository getInstance(AppDatabase db) {

        if (repository == null) {
            repository = new ProductRepository(db);
        }

        return repository;
    }

    public ProductRepository(AppDatabase db) {
        orderDao = db.orderDao();
        service = RetrofitClient.getInstance().getProductService();
        orderService = RetrofitClient.getInstance().getOrderService();
        repository = this;
    }

    public Single<HomeResponse> getHomeData(String uuid) {
        return service.getHomeData(uuid);
    }

    public Single<ProductInfoResponse> getProductInfo(String pid) {
        return service.getProductInfo(pid);
    }

    public Single<CategoryResponse> getCategory() {
        return service.getCategory(BuildConfig.VERSION_NAME);
    }

    public Single<ProductListResponse> getCategorySearch(String caid) {
        return service.getCategorySearch(caid);
    }

    public Single<ProductListResponse> getGifts() {
        return service.getGifts(BuildConfig.VERSION_NAME);
    }

    public int getProductCount() {
        return orderDao.getProductCount();
    }

    public long insertOrder(Order order) {
        return orderDao.insert(order);
    }

    public int deleteByPID(String pid) {
        return orderDao.deleteByPID(pid);
    }

    public List<Order> getOrderByGroup() {
        return orderDao.getOrderByGroup();
    }

    public int updateOrderById(String pid, int num) {
        return orderDao.updateOrderById(pid, num);
    }

    public int deleteAll() {
        return orderDao.deleteAll();
    }

    public Single<CarResponse> callCarInfo(Map<String, String> parameter) {
        return orderService.callCarInfo(parameter);
    }

    public Single<OrderResponse> buildOrder(Map<String, String> parameter) {
        return orderService.buildOrder(parameter);
    }
}
