package com.spring.web.app.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total")
    private Double total;

    @Column(name = "description")
    private String description;

    @Id
    @Column(name = "created_at")
    private Date createdAt;

    public PurchaseHistory() {}

    public PurchaseHistory(
            Long userId,
            Double total,
            String description,
            Date createdAt
    ) {
        this.userId = userId;
        this.total = total;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
