package com.example.shoppingService.service;

import com.example.shoppingService.entity.Inventory;
import com.example.shoppingService.repository.InventoryRepository;
import com.example.shoppingService.utilities.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public boolean existByName(String name) {
        return repository.existsByInventory_Name(name);
    }

    public boolean existById(UUID id) { return repository.existsById(id); }

    // === GENERAL METHODS ===
    public ServiceResponse<Inventory> save(Inventory inventory) {
        if ( inventory == null ) {
            log.warn( "Inventory is null" );
            return ServiceResponse.failure(ServiceResponse.ErrorType.VALIDATION_ERROR);
        }

        if ( inventory.getName() == null ) {
            log.warn( "Inventory name is null" );
            return ServiceResponse.failure(ServiceResponse.ErrorType.VALIDATION_ERROR);
        }

        if ( !inventory.getName().isBlank() && repository.existsByInventory_Name( inventory.getName() ) ) {
            log.warn( "Inventory name already exists" );
            return ServiceResponse.failure(ServiceResponse.ErrorType.VALIDATION_ERROR);
        }

        Inventory created = repository.save(inventory);
        return ServiceResponse.success(created);
    }

    public boolean delete(UUID id) {
        if (id == null ) {
            log.warn( "Inventory id is null" );
            return false;
        }

        try {
            repository.deleteById(id);
            log.info("Inventory has been deleted");
            return true;

        } catch (OptimisticLockingFailureException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
