package com.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class PaymentValidatorTest{

    @Test
    void validTransactionHasNoErrors() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertTrue(validator.isValid());
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    void missingMerchantProducesError() {
    }

    @Test
    void blankMerchantIdProducesError() {
    }

    @Test
    void inactiveMerchantProducesError() {
    }

    @Test
    void missingReferenceProducesError() {
    }

    @Test
    void blankReferenceProducesError() {
    }

    @Test
    void missingAmountProducesError() {
    }

    @Test
    void zeroAmountProducesError() {
    }

    @Test
    void negativeAmountProducesError() {
    }

    @Test
    void missingCurrencyProducesError() {
    }

    @Test
    void missingPaymentMethodProducesError() {
    }

}