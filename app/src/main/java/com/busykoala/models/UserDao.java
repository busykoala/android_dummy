package com.busykoala.models;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void updateUsers(User... users);

    @Query("SELECT * FROM user")
    List<User> getAll();
}
