package com.food.ordering;

import com.food.ordering.entity.Customer;
import com.food.ordering.entity.Food;
import com.food.ordering.repository.CustomerRepository;
import com.food.ordering.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/** Inserts sample menu items and a demo user on first run, so the app works out of the box. */
@Component
public class DataSeeder implements CommandLineRunner {
    private final FoodRepository foodRepo;
    private final CustomerRepository customerRepo;

    public DataSeeder(FoodRepository foodRepo, CustomerRepository customerRepo) {
        this.foodRepo = foodRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(String... args) {
        if (foodRepo.count() == 0) {
            foodRepo.saveAll(List.of(
                    new Food("Chicken Biryani", "Biryani", 180.00, true),
                    new Food("Paneer Roll", "Rolls", 90.00, true),
                    new Food("Veg Burger", "Burgers", 110.00, true),
                    new Food("Cold Coffee", "Beverages", 80.00, true)
            ));
        }
        if (customerRepo.findByEmail("user@example.com").isEmpty()) {
            customerRepo.save(new Customer("Demo User", "user@example.com", "123456"));
        }
    }
}
