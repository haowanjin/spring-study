package com.tman.javastudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    private OrderService orderService;

    public GoodsService() {
        System.out.println("GoodsService created");
    }
}
