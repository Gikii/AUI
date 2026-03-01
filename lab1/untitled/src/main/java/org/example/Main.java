package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CarBrand Volvo = CarBrand.builder()
                .name("Volvo")
                .country("Sweden")
                .build();

        CarBrand Porsche = CarBrand.builder()
                .name("Porsche")
                .country("Germany")
                .build();

        CarBrand Honda = CarBrand.builder()
                .name("Honda")
                .country("Japan")
                .build();

        CarModel XC60 = CarModel.builder()
                .modelName("XC60")
                .productionYear(2011)
                .brand(Volvo)
                .build();

        CarModel C70 = CarModel.builder()
                .modelName("C70")
                .productionYear(2003)
                .brand(Volvo)
                .build();

        CarModel GT3RS = CarModel.builder()
                .modelName("GT3RS")
                .productionYear(2023)
                .brand(Porsche)
                .build();

        CarModel Civic = CarModel.builder()
                .modelName("Civic")
                .productionYear(2015)
                .brand(Honda)
                .build();

        CarModel NSX = CarModel.builder()
                .modelName("NSX")
                .productionYear(1997)
                .brand(Honda)
                .build();


        //ZAD2
        List<CarBrand> brands = Arrays.asList(Volvo, Porsche, Honda);
        System.out.println("=== Lista marek i ich modeli ===");
        brands.forEach(brand -> {
            System.out.println(brand);
            brand.getModels().forEach(model -> System.out.println("   " + model));
        });

        //ZAD3
        Set<CarModel> allModels = brands.stream()
                .flatMap(brand -> brand.getModels().stream())
                .collect(Collectors.toSet());


        System.out.println("\n=== Wszystkie unikalne modele ===");
        allModels.stream()
                .forEach(System.out::println);


        //ZAD4
        System.out.println("\n=== Modele wyprodukowane po 2010 roku, posortowane wg marki ===");

        allModels.stream()
                .filter(model -> model.getProductionYear() > 2010)
                .sorted(Comparator.comparing(model -> model.getBrand().getName()))
                .forEach(System.out::println);

        //ZAD5
        System.out.println("\n=== DTO: Wszystkie modele posortowane wg naturalnego porządku ===");

        List<CarModelDTO> DTOList = allModels.stream()
                .map(CarModelDTO::from)
                .sorted()
                .toList();


        DTOList.stream()
                .forEach(System.out::println);

        //ZAD6
        System.out.println("\n=== Serializacja kolekcji marek ===");

        String fileName = "brands.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(brands);
            System.out.println("Zapisano do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CarBrand> loadedBrands = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedBrands = (List<CarBrand>) ois.readObject();
            System.out.println("Odczytano z pliku: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (loadedBrands != null) {
            System.out.println("\n=== Odczytane marki i ich modele ===");
            loadedBrands.forEach(brand -> {
                System.out.println(brand);
                brand.getModels().forEach(model -> System.out.println("   " + model));
            });
        }

        //ZAD7
        System.out.println("\n=== Równoległe przetwarzanie marek z własną pulą wątków ===");

        int poolSize = 2;

        ForkJoinPool customPool = new ForkJoinPool(poolSize);

        try {
            customPool.submit(() -> {
                brands.parallelStream().forEach(brand -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] Start: " + brand.getName());
                    brand.getModels().forEach(model -> {
                        try {
                            Thread.sleep(500);
                            System.out.println("   " + model);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    System.out.println("[" + Thread.currentThread().getName() + "] Koniec: " + brand.getName());
                });
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
            try {
                customPool.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
