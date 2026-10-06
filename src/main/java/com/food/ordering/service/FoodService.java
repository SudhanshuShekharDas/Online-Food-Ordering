package com.food.ordering.service;

import com.food.ordering.entity.Food;
import com.food.ordering.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository repo;
    public FoodService(FoodRepository repo){this.repo=repo;}
    public List<Food> all(){return repo.findAll();}
    public Food one(Long id){return repo.findById(id).orElseThrow();}
    public Food save(Food food){return repo.save(food);}
}
