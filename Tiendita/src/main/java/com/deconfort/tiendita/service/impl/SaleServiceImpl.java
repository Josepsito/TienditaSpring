package com.deconfort.tiendita.service.impl;


import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.entity.Sale;
import com.deconfort.tiendita.entity.SaleCompletedEvent;
import com.deconfort.tiendita.entity.SaleDetail;
import com.deconfort.tiendita.exception.InsufficientStockException;
import com.deconfort.tiendita.exception.ProductNotFoundException;
import com.deconfort.tiendita.repository.ProductRepository;
import com.deconfort.tiendita.repository.SaleRepository;
import com.deconfort.tiendita.service.SaleService;
import com.deconfort.tiendita.service.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Sale realizarVenta(int id, int cant) {

        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(() ->
                new ProductNotFoundException("El producto con ID " + id + " no fue encontrado"));

        if (product.getStock() < cant) {
            throw new InsufficientStockException(
                    "Stock insuficiente para el producto: " + product.getName() +"\n" +". Disponible: " + product.getStock() + "\n"+". solicitado: " + cant);
        }


        Sale sale = new Sale();
        sale.setDateSale(LocalDateTime.now());

        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setProduct(product);
        saleDetail.setAmount(cant);
        saleDetail.setUnitPrice(product.getPrice());
        saleDetail.setTotalPrice(cant * product.getPrice());

        sale.addDetail(saleDetail);

        product.setStock(product.getStock() - cant);

        productRepository.save(product);
        saleRepository.save(sale);
        storeService.añadirRecaudacion(cant);

        return sale;
    }

    @Override
    public Sale verVenta(int id) {
        return saleRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("El producto con ID " + id + " no fue encontrado"));
    }

    @Override
    public List<Sale> verTodasLasVentas() {
        return saleRepository.findAll();
    }
}
