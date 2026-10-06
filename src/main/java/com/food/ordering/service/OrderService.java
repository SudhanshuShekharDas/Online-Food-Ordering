package com.food.ordering.service;

import com.food.ordering.entity.*;
import com.food.ordering.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final FoodOrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final FoodRepository foodRepo;

    public OrderService(FoodOrderRepository orderRepo, OrderItemRepository itemRepo, FoodRepository foodRepo){
        this.orderRepo=orderRepo; this.itemRepo=itemRepo; this.foodRepo=foodRepo;
    }

    @Transactional
    public FoodOrder create(OrderRequest request){
        if(request.items()==null || request.items().isEmpty()) throw new IllegalArgumentException("Order must contain at least one item");
        FoodOrder order = new FoodOrder();
        order.setCustomerName(request.customerName());
        order.setCustomerEmail(request.customerEmail());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        double total=0;
        FoodOrder saved=orderRepo.save(order);

        for(OrderLine line: request.items()){
            Food food=foodRepo.findById(line.foodId()).orElseThrow(() -> new IllegalArgumentException("Food not found: "+line.foodId()));
            if(!food.isAvailable()) throw new IllegalArgumentException("Food is unavailable: "+food.getName());
            if(line.quantity()<=0) throw new IllegalArgumentException("Quantity must be positive");

            total += food.getPrice()*line.quantity();

            OrderItem item=new OrderItem();
            item.setOrderId(saved.getId());
            item.setFoodId(food.getId());
            item.setFoodName(food.getName());
            item.setQuantity(line.quantity());
            item.setUnitPrice(food.getPrice());
            itemRepo.save(item);
        }

        saved.setTotalAmount(total);
        return orderRepo.save(saved);
    }

    public List<FoodOrder> all(){return orderRepo.findAll();}
    public FoodOrder one(Long id){return orderRepo.findById(id).orElseThrow();}
    public List<OrderItem> items(Long id){return itemRepo.findByOrderId(id);}
}
