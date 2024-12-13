package com.deconfort.tiendita.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sale_detail")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;
    private Integer amount;
    private double unitPrice;
    private double totalPrice;

    public SaleDetail(Integer id, Product product, Sale sale, Integer amount, double unitPrice) {
        this.id = id;
        this.product = product;
        this.sale = sale;
        this.unitPrice = unitPrice;
        this.setAmount(amount);
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        this.totalPrice = amount * unitPrice;
    }


}
