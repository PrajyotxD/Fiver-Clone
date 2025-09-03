package com.fiverr.model;

import javax.persistence.*;

@Entity
@Table(name = "orders") // "Order" is a reserved keyword in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gig_id", nullable = false)
    private Gig gig;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @Column(nullable = false)
    private String status; // e.g., PENDING, ACCEPTED, REJECTED

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
