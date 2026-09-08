package com.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class PaymentProcessorTest {

    @Test
    void validTransactionIsApproved(){
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentProcessor processor = new PaymentProcessor();
        processor.process(transaction);
        assertEquals(Outcome.APPROVED, transaction.getOutcome());
    }

    @Test
    void invalidTransactionIsDeclined(){
        Merchant merchant = new Merchant("M001", "Test Merchant", false);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentProcessor processor = new PaymentProcessor();
        processor.process(transaction);
        assertEquals(Outcome.DECLINED, transaction.getOutcome());
    }
}
