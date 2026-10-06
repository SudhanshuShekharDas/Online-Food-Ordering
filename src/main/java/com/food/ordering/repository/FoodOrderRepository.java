package com.food.ordering.repository;
import com.food.ordering.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long> {}
