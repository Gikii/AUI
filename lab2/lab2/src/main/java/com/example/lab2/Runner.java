package com.example.lab2;

import com.example.lab2.model.CarBrand;
import com.example.lab2.model.CarModel;
import com.example.lab2.service.CarBrandService;
import com.example.lab2.service.CarModelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class Runner implements CommandLineRunner {

    private final CarBrandService carBrandService;
    private final CarModelService carModelService;

    public Runner(CarBrandService carBrandService, CarModelService carModelService) {
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Car Manager App");
        System.out.println("Type 'help' to list available commands.");

        boolean running = true;
        while (running) {
            System.out.print("\n> ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "help" -> printHelp();
                case "list brands" -> listBrands();
                case "list models" -> listModels();
                case "add model" -> addModel(scanner);
                case "delete model" -> deleteModel(scanner);
                case "exit" -> {
                    System.out.println("Exiting the app.");
                    running = false;
                }
                default -> System.out.println("Unknown command. Type 'help' to see available commands.");
            }
        }

        scanner.close();
    }

    private void printHelp() {
        System.out.println("""
                Available commands:
                help           - show this list
                list brands    - list all car brands
                list models    - list all car models
                add model      - add a new model to a brand
                delete model   - delete model by ID
                exit           - exit the app
                """);
    }

    private void listBrands() {
        List<CarBrand> brands = carBrandService.findAll();
        if (brands.isEmpty()) {
            System.out.println("No brands found.");
            return;
        }
        brands.forEach(b ->
                System.out.println(b.getId() + " | " + b.getName() + " (" + b.getCountry() + ")")
        );
    }

    private void listModels() {
        List<CarModel> models = carModelService.findAll();
        if (models.isEmpty()) {
            System.out.println("No models found.");
            return;
        }
        models.forEach(m ->
                System.out.println(m.getId() + " | " + m.getModelName() + " (" + m.getProductionYear() + ") -> " + m.getBrand().getName())
        );
    }

    private void addModel(Scanner scanner) {
        System.out.println("Enter model name:");
        String name = scanner.nextLine();

        System.out.println("Enter model year:");
        int year = Integer.parseInt(scanner.nextLine());
        if (year < 1910 || year > 2026) {
            System.out.println("Invalid year! Please enter a year between 1910 and 2026.");
            addModel(scanner);
        }

        System.out.println("Select brand ID:");
        listBrands();
        String brandId = scanner.nextLine();

        CarBrand brand = carBrandService.findById(UUID.fromString(brandId));
        if (brand == null) {
            System.out.println("Brand not found!");
            return;
        }

        CarModel newModel = new CarModel(UUID.randomUUID(), name, year, brand);
        carModelService.save(newModel);
        System.out.println("Model added: " + newModel.getModelName());
    }

    private void deleteModel(Scanner scanner) {
        System.out.println("Enter model ID to delete:");
        String id = scanner.nextLine();
        try {
            carModelService.deleteById(UUID.fromString(id));
            System.out.println("🗑Model deleted.");
        } catch (Exception e) {
            System.out.println("Error deleting model: " + e.getMessage());
        }
    }
}