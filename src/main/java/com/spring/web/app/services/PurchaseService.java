package com.spring.web.app.services;

import com.spring.web.app.dto.request.CustomerBuyRequest;
import com.spring.web.app.models.PurchaseHistory;
import com.spring.web.app.models.repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {
    public final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public void createPurchaseHistory(CustomerBuyRequest buyRequest) {
        purchaseHistoryRepository.save(
                new PurchaseHistory(
                        buyRequest.getUserId(),
                        buyRequest.getTotal(),
                        buyRequest.getDescription(),
                        stringToDate(buyRequest.getCreatedAt())
                )
        );
    }

    public List<PurchaseHistory> findByDate(String dateA, String dateB) {
        return purchaseHistoryRepository.findByCreatedAtBetween(
                this.stringToDate(dateA),
                this.stringToDate(dateB)
        );
    }

    private Date stringToDate(String date) {
        if (date == null || date.equals(""))
            return null;

        try {
            return new SimpleDateFormat(FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
