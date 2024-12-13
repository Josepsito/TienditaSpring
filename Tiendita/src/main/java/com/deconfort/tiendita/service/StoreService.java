package com.deconfort.tiendita.service;


import com.deconfort.tiendita.entity.Store;

public interface StoreService {

    Store verDatosDeTienda();
    double devolverDineroTotal();
    int devolverCantidadDeVentas();
    Store añadirRecaudacion(double recaudacion);
}
