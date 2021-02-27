package com.example.mvvmjavademo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CityRepository {
    private final CityDao cityDao;
    private final LiveData<List<City>> cities;

    CityRepository(Application application) {
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        cityDao = db.cityDao();
        cities = cityDao.getAlphabetizedCities();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<City>> getCities() {
        return cities;
    }

    // Use the ExecutorService we created in the WordRoomDatabase to perform the insert on a background thread
    void insert(City city) {
        CityRoomDatabase.databaseWriteExecutor.execute(() -> cityDao.insert(city));
    }
}

