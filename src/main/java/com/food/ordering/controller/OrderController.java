package com.food.ordering.controller;

import com.food.ordering.entity.FoodOrder;
import com.food.ordering.entity.OrderItem;
import com.food.ordering.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){this.service=service;}

    @GetMapping public List<FoodOrder> all(){return service.all();}
    @GetMapping("/{id}") public FoodOrder one(@PathVariable Long id){return service.one(id);}
    @GetMapping("/{id}/items") public List<OrderItem> items(@PathVariable Long id){return service.items(id);}
    @PostMapping public FoodOrder create(@RequestBody OrderRequest request){return service.create(request);}
}
