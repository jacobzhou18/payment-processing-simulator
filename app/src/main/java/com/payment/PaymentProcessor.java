package com.payment;

public class PaymentProcessor {
    public void process(Transaction transaction){
        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();

        if(validator.isValid()){
            transaction.approve();
        }else{
            transaction.decline();
        }
    }
}

