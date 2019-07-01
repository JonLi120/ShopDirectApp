package com.twobytwoshop.ShopDirect.repo.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.twobytwoshop.ShopDirect.models.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("select SUM(qut) from order_table")
    int getProductCount();

    @Insert
    long insert(Order order);

    @Query("delete from order_table where pid = :pid")
    int deleteByPID(String pid);

    @Query("select *, sum(qut) as total from order_table group by pid")
    List<Order> getOrderByGroup();

    @Query("update order_table set qut = qut + :num where pid = :pid")
    int updateOrderById(String pid, int num);
}
