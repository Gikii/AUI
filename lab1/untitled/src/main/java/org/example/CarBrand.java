package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarBrand implements Serializable, Comparable<CarBrand> {
    private static final long serialVersionUID = 1L; 
    private String name;
    private String country;
    private List<CarModel> models = new ArrayList<>();

    public static class Builder {
        private String name;
        private String country;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public CarBrand build() {
            return new CarBrand(name, country);
        }
    }

    public static Builder builder() {
        return new Builder();
    }



    public CarBrand(String name, String country) {
        this.name = name;
        this.country = country;
    }




    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<CarModel> getModels() {
        return models;
    }



    public void addModel(CarModel model) {
        if (model == null) return;
        if (!models.contains(model)) {
            models.add(model);
        }
        if (model.getBrand() != this) {
            model.setBrand(this);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarBrand)) return false;
        CarBrand brand = (CarBrand) o;
        return Objects.equals(name, brand.name) &&
                Objects.equals(country, brand.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public int compareTo(CarBrand other) {
        return this.name.compareToIgnoreCase(other.name);
    }


    @Override
    public String toString() {
        return "CarBrand{name='" + name + "', country='" + country + "', modelsCount=" + models.size() + "}";
    }
}