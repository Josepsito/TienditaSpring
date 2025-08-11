package com.deconfort.tiendita.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductDocument {
    @Id
    private Long id;

    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private String category;
    private String imageUrl;
    private Double discount;
    private Boolean active;
    private String brand;

    public static ProductDocument fromProduct(Product product) {
        ProductDocument doc = new ProductDocument();
        doc.setId(Long.valueOf(product.getId()));
        doc.setName(product.getName());
        doc.setDescription(product.getDescription());
        doc.setStock(product.getStock());
        doc.setPrice(product.getPrice());
        doc.setCategory(product.getCategory());
        doc.setImageUrl(product.getImageUrl());
        doc.setDiscount(product.getDiscount());
        doc.setActive(product.getActive());
        doc.setBrand(product.getBrand());
        return doc;
    }


}
