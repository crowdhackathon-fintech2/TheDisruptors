package com.gio.service.vo;

public class Order {

    private long amount;
    private String currency;
    private String description;
    private String cardAddressCity;
    private String cardAddressState;
    private int cardCVC;
    private int cardExpMonth;
    private int cardExpYear;
    private String cardNumber;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCardAddressCity() {
        return cardAddressCity;
    }

    public void setCardAddressCity(String cardAddressCity) {
        this.cardAddressCity = cardAddressCity;
    }

    public String getCardAddressState() {
        return cardAddressState;
    }

    public void setCardAddressState(String cardAddressState) {
        this.cardAddressState = cardAddressState;
    }

    public int getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(int cardCVC) {
        this.cardCVC = cardCVC;
    }

    public int getCardExpMonth() {
        return cardExpMonth;
    }

    public void setCardExpMonth(int cardExpMonth) {
        this.cardExpMonth = cardExpMonth;
    }

    public int getCardExpYear() {
        return cardExpYear;
    }

    public void setCardExpYear(int cardExpYear) {
        this.cardExpYear = cardExpYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
