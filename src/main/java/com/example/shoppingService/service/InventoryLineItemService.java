package com.example.shoppingService.service;

import com.example.shoppingService.repository.InventoryLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryLineItemService {

    @Autowired
    private InventoryLineItemRepository repository;

}
