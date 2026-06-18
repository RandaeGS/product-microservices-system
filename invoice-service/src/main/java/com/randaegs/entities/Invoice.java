package com.randaegs.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Table(name = "invoices")
public class Invoice extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "product_id")
    public String productId;

    @Column(name = "product_amount")
    public Integer productAmount;

    @CreationTimestamp
    @Column(name = "creation_date")
    public Instant creationDate;
}
