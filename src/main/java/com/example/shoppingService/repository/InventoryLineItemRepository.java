package com.example.shoppingService.repository;

import com.example.shoppingService.entity.InventoryLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryLineItemRepository extends JpaRepository<InventoryLineItem, UUID> {
}
