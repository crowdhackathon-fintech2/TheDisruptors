package com.ngbfintech2.api.dto;

import com.ngbfintech2.api.dao.Order;
import org.springframework.stereotype.Controller;

@Controller
public class OrderService {
    public Order getOrder(String userId, String productId){
        Order request = new Order();
        request.setCardAddressCity("OFallon");
        request.setCardAddressState("MO");
        request.setCardCVC(123);
        request.setCardExpMonth(11);
        request.setCardExpYear(19);
        request.setCardNumber("5105105105105100");
        request.setCurrency("EUR");
        request.setAmount(1700);
        request.setDescription("Super Latte");
        return request;
    }

}
