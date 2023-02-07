package com.spring.web.app.models.repository;


import com.spring.web.app.models.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Date> {
    @Query("select p from PurchaseHistory p where ( :a is null or p.createdAt >= :a ) and ( :b is null or p.createdAt <= :b )")
    List<PurchaseHistory> findByCreatedAtBetween(Date a, Date b);
}
