package com.food.ordering.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class FoodOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerEmail;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;

    public FoodOrder() {}
    public Long getId(){return id;}
    public String getCustomerName(){return customerName;}
    public void setCustomerName(String customerName){this.customerName=customerName;}
    public String getCustomerEmail(){return customerEmail;}
    public void setCustomerEmail(String customerEmail){this.customerEmail=customerEmail;}
    public double getTotalAmount(){return totalAmount;}
    public void setTotalAmount(double totalAmount){this.totalAmount=totalAmount;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
