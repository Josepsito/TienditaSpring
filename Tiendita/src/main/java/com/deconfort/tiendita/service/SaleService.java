package com.deconfort.tiendita.service;

import com.deconfort.tiendita.entity.Sale;

import java.util.List;

public interface SaleService{

    Sale realizarVenta(int id,int cant);
    Sale verVenta(int id);

    List<Sale> verTodasLasVentas();
}
