package com.deconfort.tiendita.service.impl;

import com.deconfort.tiendita.entity.Store;
import com.deconfort.tiendita.repository.StoreRepository;
import com.deconfort.tiendita.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store verDatosDeTienda() {
        return storeRepository.findById(1).get();
    }

    @Override
    public double devolverDineroTotal() {
        return storeRepository.findById(1).get().getTotal_money();
    }

    @Override
    public int devolverCantidadDeVentas() {
        return storeRepository.findById(1).get().getTotal_sale();
    }

    @Override
    public Store añadirRecaudacion(double recaudacion) {
        Store store = storeRepository.findById(1).get();
        store.setTotal_money(store.getTotal_money()+recaudacion);
        store.setTotal_sale(store.getTotal_sale()+1);
        return storeRepository.save(store);
    }
}
