package com.payment;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private final String transactionReference;
    private final Merchant merchant;
    private final BigDecimal transactionAmount;
    private final Currency currency;
    private final PaymentMethod paymentMethod;
    private final Instant timeOfTransaction;
    private Outcome outcome;
    
    public Transaction(String transactionReference, Merchant merchant, BigDecimal transactionAmount, Currency currency, PaymentMethod paymentMethod){
        this.transactionReference = transactionReference;
        this.merchant = merchant;
        this.transactionAmount = transactionAmount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.timeOfTransaction = Instant.now();
        this.outcome = Outcome.PENDING;
    }

    public String getTransactionReference(){
        return transactionReference;
    }

    public Merchant getMerchant(){
        return merchant;
    }

    public BigDecimal getTransactionAmount(){
        return transactionAmount;
    }

    public Currency getCurrency(){
        return currency;
    }

    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }

    public Instant getTimeOfTransaction(){
        return timeOfTransaction;
    }

    public Outcome getOutcome(){
        return outcome;
    }
}
