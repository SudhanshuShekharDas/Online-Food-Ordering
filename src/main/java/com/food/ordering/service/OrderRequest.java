package com.food.ordering.service;

import java.util.List;

public record OrderRequest(String customerName, String customerEmail, List<OrderLine> items) {}
