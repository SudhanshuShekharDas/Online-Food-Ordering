package com.food.ordering.entity;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long foodId;
    private String foodName;
    private int quantity;
    private double unitPrice;

    public OrderItem() {}
    public Long getId(){return id;}
    public Long getOrderId(){return orderId;}
    public void setOrderId(Long orderId){this.orderId=orderId;}
    public Long getFoodId(){return foodId;}
    public void setFoodId(Long foodId){this.foodId=foodId;}
    public String getFoodName(){return foodName;}
    public void setFoodName(String foodName){this.foodName=foodName;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public double getUnitPrice(){return unitPrice;}
    public void setUnitPrice(double unitPrice){this.unitPrice=unitPrice;}
}
