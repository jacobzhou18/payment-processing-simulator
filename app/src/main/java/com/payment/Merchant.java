package com.payment;

public class Merchant {
    private final String merchantId;
    private final String merchantName;
    private boolean active;

    public Merchant(String merchantId, String merchantName, boolean active){
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.active = active;
    }

    public String getMerchantId(){
        return merchantId;
    }

    public String getMerchantName(){
        return merchantName;
    }

    public boolean isActive(){
        return active;
    }
}
