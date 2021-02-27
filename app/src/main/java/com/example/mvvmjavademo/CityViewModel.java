package com.example.mvvmjavademo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private final LiveData<List<City>> cities;
    private final CityRepository mRepository;

    public CityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CityRepository(application);
        cities = mRepository.getCities();
    }

    LiveData<List<City>> getAllCities() {
        return cities;
    }

    public void insert(City city) {
        mRepository.insert(city);
    }
}
