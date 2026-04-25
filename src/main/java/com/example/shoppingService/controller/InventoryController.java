package com.example.shoppingService.controller;

import com.example.shoppingService.entity.Inventory;
import com.example.shoppingService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController("/inventory")
public class InventoryController {

    private final InventoryService service;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        if (inventory == null) {
            return ResponseEntity.badRequest().build();
        }

        Inventory created = service.save(inventory).getEntity();

        if ( created == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(created);
    }

    @PutMapping("{/id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable UUID id,
            @RequestBody Inventory inventory
    ) {
        if ( inventory == null || inventory.getId() == null ) {
            return ResponseEntity.badRequest().build();
        }

        if ( !service.existById(id) ) {
            return ResponseEntity.notFound().build();
        }

        Inventory updated = service.save(inventory).getEntity();
        if ( updated == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable UUID id) {
        boolean deleted = service.delete(id);
        if ( !deleted ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
