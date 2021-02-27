package com.example.mvvmjavademo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city")
public class City {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "population")
    private Long population;

    public City(@NonNull String name, String country, Long population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}