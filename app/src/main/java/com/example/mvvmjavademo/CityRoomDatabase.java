package com.example.mvvmjavademo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CityRoomDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile CityRoomDatabase INSTANCE;

    static CityRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (CityRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityRoomDatabase.class, "city_database")
                            .addCallback(cityRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CityDao cityDao();

    private static final RoomDatabase.Callback cityRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                CityDao cityDao = INSTANCE.cityDao();
                cityDao.deleteAll();
                for (int i = 0; i < 100; i++) {
                    City city = new City("La Rioja" + " " + (i + 1), "Argentina", (long) 999999999);
                    cityDao.insert(city);
                }
            });
        }
    };
}

