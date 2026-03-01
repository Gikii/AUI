package org.example;



public class CarModelDTO implements Comparable<CarModelDTO> {
    private String modelName;
    private int productionYear;
    private String brandName;

    public CarModelDTO(String modelName, int productionYear, String brandName) {
        this.modelName = modelName;
        this.productionYear = productionYear;
        this.brandName = brandName;
    }

    public static CarModelDTO from(CarModel model) {
        return new CarModelDTO(
                model.getModelName(),
                model.getProductionYear(),
                model.getBrand() == null ? null : model.getBrand().getName()
        );
    }

    @Override
    public int compareTo(CarModelDTO o) {
        int cmp = this.brandName.compareToIgnoreCase(o.brandName);
        if (cmp != 0) return cmp;
        return this.modelName.compareToIgnoreCase(o.modelName);
    }

    @Override
    public String toString() {
        return "CarModelDto{modelName='" + modelName + "', year=" + productionYear +
                ", brandName='" + brandName + "'}";
    }
}