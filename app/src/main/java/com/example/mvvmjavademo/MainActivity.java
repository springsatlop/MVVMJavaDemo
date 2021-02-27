package com.example.mvvmjavademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityViewModel cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        rvCities = findViewById(R.id.rv_cities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCities.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCities.getContext(),
                linearLayoutManager.getOrientation());
        rvCities.addItemDecoration(dividerItemDecoration);
        cityViewModel.getAllCities().observe(this, cities -> {
            CityAdapter cityAdapter = new CityAdapter(cities);
            rvCities.setAdapter(cityAdapter);
        });
    }
}