package com.food.ordering.service;

public record LoginResponse(boolean success, String message, Long customerId, String name, String email) {}
