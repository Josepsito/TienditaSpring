package com.deconfort.tiendita.entity;

public class SaleCompletedEvent {
    private final int saleId;
    private final double totalAmount;

    public SaleCompletedEvent(int saleId, double totalAmount) {
        this.saleId = saleId;
        this.totalAmount = totalAmount;
    }

    public int getSaleId() {
        return saleId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}