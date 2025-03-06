package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleReportDTO {

    private Long Id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SaleReportDTO(){
    }

    public SaleReportDTO(Long id, LocalDate date, Double amount, String name) {
        Id = id;
        this.date = date;
        this.amount = amount;
        this.name = name;
    }

    public SaleReportDTO(Sale entity) {
        Id = entity.getId();
        date = entity.getDate();
        amount = entity.getAmount();
        name = entity.getSeller().getName();
    }

    public Long getId() {
        return Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
