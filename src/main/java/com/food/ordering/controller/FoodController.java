package com.food.ordering.controller;

import com.food.ordering.entity.Food;
import com.food.ordering.service.FoodService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin
public class FoodController {
    private final FoodService service;
    public FoodController(FoodService service){this.service=service;}

    @GetMapping public List<Food> all(){return service.all();}
    @GetMapping("/{id}") public Food one(@PathVariable Long id){return service.one(id);}
    @PostMapping public Food save(@RequestBody Food food){return service.save(food);}
}
