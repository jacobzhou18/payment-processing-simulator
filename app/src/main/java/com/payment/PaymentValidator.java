package com.payment;

import java.math.BigDecimal;

public class PaymentValidator {
    public boolean hasValidMerchant(Transaction transaction){
        Merchant merchant = transaction.getMerchant();
        return merchant!=null
            &&merchant.getMerchantId()!=null
            &&!merchant.getMerchantId().isBlank()
            &&merchant.getMerchantName()!=null
            &&!merchant.getMerchantName().isBlank()
            &&merchant.isActive();
    }

    public boolean hasValidReference(Transaction transaction){
        return transaction.getTransactionReference()!=null
            &&transaction.getTransactionReference().isBlank();
    }

    public boolean hasValidAmount(Transaction transaction){
        return transaction.getTransactionAmount()!=null
            &&transaction.getTransactionAmount().compareTo(BigDecimal.ZERO)>0;
    }

    public boolean hasValidCurrency(Transaction transaction){
        return transaction.getCurrency()!=null;
    }

    public boolean hasValidPaymentMethod(Transaction transaction){
        return transaction.getPaymentMethod()!=null;
    }

    public boolean isTransactionValid(Transaction transaction){
        return transaction!=null
            &&hasValidMerchant(transaction)
            &&hasValidReference(transaction)
            &&hasValidAmount(transaction)
            &&hasValidCurrency(transaction)
            &&hasValidPaymentMethod(transaction);
    }
}
