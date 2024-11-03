package com.omkar.microservices.inventory.service;

import com.omkar.microservices.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    public final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode, Integer quantity)
    {
        // find an inventory for an given skucode where quantity >= 0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
