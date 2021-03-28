package com.busykoala.models;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static UserDatabase userDB;

    public static UserDatabase getInstance(Context context) {
        if (null == userDB) {
            userDB = buildDatabaseInstance(context);
        }
        return userDB;
    }

    private static UserDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, Constants.DB_NAME)
            .allowMainThreadQueries().build();
    }

    public void cleanUp() {
        userDB = null;
    }
}
