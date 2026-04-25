package com.example.shoppingService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double price = 0.0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Inventory inventory;
}
