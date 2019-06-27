package com.twobytwoshop.ShopDirect.repo.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.twobytwoshop.ShopDirect.models.UserInfo;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserInfo user);

    @Query("select * from user_table where uuid = :uuid")
    List<UserInfo> searchByUUID(String uuid);

    @Delete
    int delete(UserInfo user);
}
