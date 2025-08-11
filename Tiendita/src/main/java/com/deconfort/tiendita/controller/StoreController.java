package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Sale;
import com.deconfort.tiendita.entity.Store;
import com.deconfort.tiendita.service.StoreService;
import com.deconfort.tiendita.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/tiendita")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/store")
    public ResponseEntity<Store> verTienda() {
        Store store = storeService.verDatosDeTienda();
        return (store != null)
                ? ResponseEntity.ok(store)
                : ResponseEntity.notFound().build();
    }
}