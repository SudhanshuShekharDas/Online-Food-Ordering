package com.food.ordering.controller;

import com.food.ordering.entity.Customer;
import com.food.ordering.repository.CustomerRepository;
import com.food.ordering.service.LoginRequest;
import com.food.ordering.service.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final CustomerRepository customerRepository;

    public AuthController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isBlank() ||
            customer.getPassword() == null || customer.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Name, email and password are required.");
        }
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }
        Customer saved = customerRepository.save(customer);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return customerRepository.findByEmail(request.email())
                .filter(customer -> customer.getPassword().equals(request.password()))
                .map(customer -> ResponseEntity.ok(
                        new LoginResponse(true, "Login successful", customer.getId(),
                                customer.getName(), customer.getEmail())))
                .orElseGet(() -> ResponseEntity.status(401).body(
                        new LoginResponse(false, "Invalid email or password", null, null, null)));
    }
}
