package com.EcommerceWeb.service;

import com.EcommerceWeb.payload.OrderDTO;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

public interface OrderService {
    @Transactional
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}

