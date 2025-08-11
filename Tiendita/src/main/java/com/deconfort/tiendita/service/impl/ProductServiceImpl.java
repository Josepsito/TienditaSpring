package com.deconfort.tiendita.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.entity.ProductDocument;
import com.deconfort.tiendita.repository.ProductRepository;
import com.deconfort.tiendita.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchClient client;

    @Override
    public Product verProducto(int id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> verTodosLosProductos() {
        return productRepository.findAll();
    }

    @Override
    public Product buscarProductoPorNombre(String nombre){
        return productRepository.findByName(nombre).orElseThrow(()->new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> buscarProductosPorIniciales(String iniciales) throws IOException {
        SearchRequest request = SearchRequest.of(s -> s
                .index("products")
                .query(q -> q
                        .matchPhrasePrefix(m -> m
                                .field("name")
                                .query(iniciales.toLowerCase())
                        )
                )
                .sort(sort -> sort
                        .score(score -> score.order(SortOrder.Desc))
                )
        );

        SearchResponse<Product> response = client.search(request, Product.class);

        List<Product> productos = new ArrayList<>();
        for (Hit<Product> hit : response.hits().hits()) {
            productos.add(hit.source());
        }

        return productos;
    }


    @Override
    public List<Product> verProductosPorCategoria(String categoria) {
        return productRepository.findByCategory(categoria);
    }


    @Override
    @Transactional
    public List<Product> añadirListaDeProductos(List<Product> products) throws IOException {
        List<Product> savedProducts = productRepository.saveAll(products);

        // Indexar todos los productos en Elasticsearch
        for (Product p : savedProducts) {
            ProductDocument doc = ProductDocument.fromProduct(p);
            client.index(i -> i
                    .index("products")
                    .id(String.valueOf(p.getId()))
                    .document(doc)
            );
        }

        return savedProducts;
    }

    @Override
    @Transactional
    public Product crearProducto(Product product) throws IOException {
        // Guardar en la base de datos relacional
        Product savedProduct = productRepository.save(product);

        // Convertir a ProductDocument para indexar en Elasticsearch sin LocalDateTime
        ProductDocument doc = ProductDocument.fromProduct(savedProduct);

        // Indexar en Elasticsearch
        client.index(i -> i
                .index("products")
                .id(String.valueOf(savedProduct.getId()))
                .document(doc)
        );

        return savedProduct;
    }

    @Override
    @Transactional
    public Product retirarProducto(int id, int cant) throws IOException {
        if (cant <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        Product updatedProduct = productRepository.findById(id)
                .map(product -> {
                    if (cant > product.getStock()) {
                        throw new RuntimeException("No hay suficiente stock");
                    }
                    product.setStock(product.getStock() - cant);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar Elasticsearch
        ProductDocument doc = ProductDocument.fromProduct(updatedProduct);
        client.index(i -> i
                .index("products")
                .id(String.valueOf(updatedProduct.getId()))
                .document(doc)
        );

        return updatedProduct;
    }

    @Override
    @Transactional
    public boolean eliminarProducto(int id) throws IOException {
        try {
            // Eliminar en base de datos relacional
            productRepository.deleteById(id);

            // Eliminar de Elasticsearch
            client.delete(d -> d
                    .index("products")
                    .id(String.valueOf(id))
            );

            return true;
        } catch (Exception ex) {
            // Aquí puedes hacer logging para saber qué salió mal
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean eliminarTodosLosProductos() {
        try {
            // Elimina todo en la base de datos
            productRepository.deleteAll();

            // Borra todo el índice en Elasticsearch
            client.deleteByQuery(d -> d
                    .index("products")
                    .query(q -> q.matchAll(m -> m))
            );

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
