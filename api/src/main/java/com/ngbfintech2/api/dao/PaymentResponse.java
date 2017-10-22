package com.ngbfintech2.api.dao;

public class PaymentResponse {
    private String Status;

    public PaymentResponse(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
