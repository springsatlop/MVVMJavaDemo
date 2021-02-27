package com.example.mvvmjavademo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final List<City> cities;

    public CityAdapter(List<City> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        // Return a new holder instance
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCountry, tvPopulation;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvPopulation = itemView.findViewById(R.id.tv_population);
        }

        void bind(City city) {
            tvName.setText(city.getName());
            tvCountry.setText(city.getCountry());
            tvPopulation.setText(formatDisplayPopulation(city.getPopulation(), "###,###,###"));
        }
    }

    public String formatDisplayPopulation(long population, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(population);
    }
}
