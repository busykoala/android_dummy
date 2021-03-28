package com.busykoala.dummy_app;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import com.busykoala.models.User;
import com.busykoala.models.AppDatabase;
import com.busykoala.models.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class UserDatabaseTest {
    AppDatabase appDB;

    @Before
    public void initDb() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        appDB = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDb() throws Exception {
        appDB.close();
    }

    @Test
    public void insertAndGetUser() {
        User elisabeth = new User("Elisabeth", "Meier");
        appDB.getUserDao().insertAll(elisabeth);

        List<User> users = appDB.getUserDao().getAll();
        assertThat(users.size(), is(1));

        User dbUser = users.get(0);
        assertEquals("Elisabeth", dbUser.firstName);
    }

    @Test
    public void insertAndUpdateUser() {
        User elisabeth = new User("Elisabeth", "Meier");
        appDB.getUserDao().insertAll(elisabeth);

        User dbUser = appDB.getUserDao().getAll().get(0);
        assertEquals("Elisabeth", dbUser.firstName);

        // update user info
        dbUser.setFirstName("Albert");
        dbUser.setLastName("Einstein");
        appDB.getUserDao().updateUsers(dbUser);

        // assert that one value and correct info
        User albert = appDB.getUserDao().getAll().get(0);
        assertEquals("Albert", albert.firstName);
    }

    @Test
    public void insertAndDeleteUser() {
        User elisabeth = new User("Elisabeth", "Meier");
        appDB.getUserDao().insertAll(elisabeth);

        List<User> users = appDB.getUserDao().getAll();
        assertThat(users.size(), is(1));

        User dbUser = users.get(0);
        appDB.getUserDao().delete(dbUser);
        List<User> usersAfterDeletion = appDB.getUserDao().getAll();
        assertThat(usersAfterDeletion.size(), is(0));
    }
}
