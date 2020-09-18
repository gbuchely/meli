package com.meli.challenge.items.controller;

import com.meli.challenge.items.dto.HealthCheckDTO;
import com.meli.challenge.items.dto.ItemDTO;
import com.meli.challenge.items.metrics.MethodExecutionTime;
import com.meli.challenge.items.service.DataService;
import com.meli.challenge.items.service.HealthService;
import com.meli.challenge.items.service.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ItemsController {

    private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private DataService dataService;

    @Autowired
    private HealthService healthService;


    @MethodExecutionTime
    @GetMapping("/items/{id}")
    public ItemDTO getItem(@PathVariable String id) throws ExecutionException, InterruptedException {

        if (dataService.hasItem(id)) {
            return dataService.getItem(id);
        } else {
            ItemDTO newItem = itemsService.getItemWithChildren(id).get();
            dataService.setItem(newItem);
            return newItem;
        }
        // TODO Error Handling
    }

    @GetMapping("/health")
    public List<HealthCheckDTO> health() {
        return healthService.getHealthCheck();
    }

}
