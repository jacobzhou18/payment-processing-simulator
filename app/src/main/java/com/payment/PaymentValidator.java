package com.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class PaymentValidator {
    private final Transaction transaction;
    private final List<String> errors;

    public PaymentValidator(Transaction transaction){
        this.transaction = transaction;
        this.errors = new ArrayList<>();
    }

    private void validateMerchant(){
        Merchant merchant = transaction.getMerchant();
        if(merchant==null){
            errors.add("Merchant is required");
        }else{
            if(merchant.getMerchantId() == null||merchant.getMerchantId().isBlank()){
                errors.add("Merchant ID is empty");
            }
            if(merchant.getMerchantName() == null||merchant.getMerchantName().isBlank()){
                errors.add("Merchant name is empty");
            }
            if(!merchant.isActive()){
                errors.add("Merchant is inactive");
            }
        }
    }

    private void validateReference(){
        if(transaction.getTransactionReference()==null){
            errors.add("Payment reference is required");
        }else{
            if(transaction.getTransactionReference().isBlank()){
                errors.add("Payment reference is empty");
            }
        }
    }

    private void validateAmount(){
        if(transaction.getTransactionAmount()==null){
            errors.add("Payment amount is required");
        }else{
            if(transaction.getTransactionAmount().compareTo(BigDecimal.ZERO)<=0){
                errors.add("Non-positive payment amount");
            }
        }
    }

    private void validateCurrency(){
        if(transaction.getCurrency()==null){
            errors.add("Payment currency is required");
        }
    }

    private void validatePaymentMethod(){
        if(transaction.getPaymentMethod()==null){
            errors.add("Payment method is required");
        }
    }

    public void validate(){
        errors.clear(); //avoid duplicate errors
        validateMerchant();
        validateReference();
        validateAmount();
        validateCurrency();
        validatePaymentMethod();
    }

    public boolean isValid(){
        return errors.isEmpty();
    }

    public List<String> getErrors(){
        return errors;
    }
}
