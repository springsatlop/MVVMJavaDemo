package com.example.mvvmjavademo;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private CityDao cityDao;
    private CityRoomDatabase db;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CityRoomDatabase.class).build();
        cityDao = db.cityDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeCityAndReadInList() throws Exception{
        AtomicInteger sum = new AtomicInteger();
        CityRoomDatabase.databaseWriteExecutor.execute(() -> {
            cityDao.deleteAll();
            for (int i = 0; i < 272128; i++){
                City city = new City("La Rioja" + " " + (i + 1),  "Argentina", (long) 999999999);
                cityDao.insert(city);
                sum.addAndGet(1);
            }
            assertThat(sum, equalTo(27128));
        });
    }
}