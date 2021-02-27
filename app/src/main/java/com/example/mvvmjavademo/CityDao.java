package com.example.mvvmjavademo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(City city);

    @Query("DELETE FROM city")
    void deleteAll();

    @Query("SELECT * FROM city ORDER BY name ASC")
    LiveData<List<City>> getAlphabetizedCities();
}

