package com.spring.web.app.controller;

import com.spring.web.app.dto.errors.ErrorResponse;
import com.spring.web.app.dto.message.MessageResponse;
import com.spring.web.app.dto.request.CustomerBuyRequest;
import com.spring.web.app.models.PurchaseHistory;
import com.spring.web.app.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    public PurchaseService purchaseService;

    @PostMapping("/customer/buy")
    public ResponseEntity<?> customerPurchase(@Valid @RequestBody CustomerBuyRequest buyRequest) {
        try {
            this.purchaseService.createPurchaseHistory(buyRequest);

            return ResponseEntity.ok(new MessageResponse(String.format("Customer buying product with price %.2f $!", buyRequest.getTotal())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    400,
                    e.getMessage(),
                    "Contact to admin for more information!"
            ));
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getAllPurchaseHistory(
            @Valid @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo
    ) {
    try {
        List<PurchaseHistory> purchaseHistories = this.purchaseService.findByDate(dateFrom, dateTo);
        return ResponseEntity.ok(purchaseHistories);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                400,
                e.getMessage(),
                "Contact to admin for more information!"
        ));
    }
    }
}
