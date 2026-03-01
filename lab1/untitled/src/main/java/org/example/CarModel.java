package org.example;


import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable, Comparable<CarModel> {
    private static final long serialVersionUID = 1L;
    private String modelName;
    private int productionYear;
    private transient CarBrand brand;


    public static class Builder {
        private String modelName;
        private int productionYear;
        private CarBrand brand;

        public Builder modelName(String modelName) {
            this.modelName = modelName;
            return this;
        }

        public Builder productionYear(int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Builder brand(CarBrand brand) {
            this.brand = brand;
            return this;
        }

        public CarModel build() {
            CarModel model = new CarModel(modelName, productionYear);
            if (brand != null) {
                model.setBrand(brand);
                brand.addModel(model);
            }
            return model;
        }
    }

    public static Builder builder() {
        return new Builder();
    }



    public CarModel(String modelName, int productionYear) {
        this.modelName = modelName;
        this.productionYear = productionYear;
    }



    public String getModelName() {
        return modelName;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
        if (brand != null && !brand.getModels().contains(this)) {
            brand.getModels().add(this);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarModel)) return false;
        CarModel that = (CarModel) o;
        return productionYear == that.productionYear &&
                Objects.equals(modelName, that.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, productionYear);
    }

    @Override
    public int compareTo(CarModel o) {
        int cmp = this.modelName.compareToIgnoreCase(o.modelName);
        if (cmp != 0) return cmp;
        return Integer.compare(this.productionYear, o.productionYear);
    }



    @Override
    public String toString() {
        return "CarModel{modelName='" + modelName + "', year=" + productionYear +
                ", brand=" + (brand == null ? "null" : brand.getName()) + "}";
    }
}