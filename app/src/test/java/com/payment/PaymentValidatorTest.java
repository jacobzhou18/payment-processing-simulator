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
    }

    @Test
    void missingMerchantProducesError() {
        Transaction transaction = new Transaction("TX001", null, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant is required", validator.getErrors().get(0));
    }

    @Test
    void nullMerchantIdProducesError() {
        Merchant merchant = new Merchant(null, "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant ID is required", validator.getErrors().get(0));
    }

    @Test
    void blankMerchantIdProducesError() {
        Merchant merchant = new Merchant(" ", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant ID is required", validator.getErrors().get(0));
    }

    @Test
    void nullMerchantNameProducesError() {
        Merchant merchant = new Merchant("M001", null, true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant name is required", validator.getErrors().get(0));
    }

    @Test
    void blankMerchantNameProducesError() {
        Merchant merchant = new Merchant("M001", " ", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant name is required", validator.getErrors().get(0));
    }

    @Test
    void inactiveMerchantProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", false);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Merchant is inactive", validator.getErrors().get(0));
    }

    @Test
    void nullReferenceProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction(null, merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment reference is required", validator.getErrors().get(0));
    }

    @Test
    void blankReferenceProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction(" ", merchant, new BigDecimal("100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment reference is required", validator.getErrors().get(0));
    }

    @Test
    void nullAmountProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, null, Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment amount is required", validator.getErrors().get(0));
    }

    @Test
    void zeroAmountProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("0"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment amount is non-positive", validator.getErrors().get(0));
    }

    @Test
    void negativeAmountProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("-100.00"), Currency.GBP, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment amount is non-positive", validator.getErrors().get(0));
    }

    @Test
    void nullCurrencyProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), null, PaymentMethod.DEBIT);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment currency is required", validator.getErrors().get(0));
    }

    @Test
    void nullPaymentMethodProducesError() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, null);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
        assertEquals("Payment method is required", validator.getErrors().get(0));
    }

    @Test
    void multipleErrors(){
        Merchant merchant = new Merchant("M001", "Test Merchant", false);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("-100.00"), Currency.GBP, null);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        assertFalse(validator.isValid());
        assertEquals(3, validator.getErrors().size());
        assertEquals("Merchant is inactive", validator.getErrors().get(0));
        assertEquals("Payment amount is non-positive", validator.getErrors().get(1));
        assertEquals("Payment method is required", validator.getErrors().get(2));
    }

    @Test
    void callingValidateTwiceDoesNotDuplicateErrors() {
        Merchant merchant = new Merchant("M001", "Test Merchant", true);
        Transaction transaction = new Transaction("TX001", merchant, new BigDecimal("100.00"), Currency.GBP, null);

        PaymentValidator validator = new PaymentValidator(transaction);
        validator.validate();
        validator.validate();
        assertEquals(1, validator.getErrors().size());
    }
}