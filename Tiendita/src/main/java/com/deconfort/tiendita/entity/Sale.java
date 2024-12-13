package com.deconfort.tiendita.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dateSale;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleDetail> details = new ArrayList<>();

    public Sale(Integer id, List<SaleDetail> details) {
        this.id = id;
        this.details = details;
        dateSale=LocalDateTime.now();
    }

    public void addDetail(SaleDetail saleDetail) {
        saleDetail.setSale(this);
        details.add(saleDetail); 
    }

}
