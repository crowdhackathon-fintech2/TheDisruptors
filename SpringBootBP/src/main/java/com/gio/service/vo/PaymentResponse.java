package com.gio.service.vo;

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
