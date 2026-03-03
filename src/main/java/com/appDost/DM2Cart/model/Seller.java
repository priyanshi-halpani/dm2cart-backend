package com.appDost.DM2Cart.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------- BASIC INFO TAB ----------
    @Column(nullable = false, unique = true)
    private String businessName;

    private String sellerName;
    private String storeName;
    private String mobile;
    private String email;
    private String pincode;
    private String city;
    private String state;

    @Column(length = 1000)
    private String about;

    private String logoUrl;

    // ---------- KYC TAB ----------
    private String panNumber;
    private String gstNumber;
    private String aadharMasked;
    private String businessProofUrl;
    private String kycStatus = "PENDING";

    // ---------- BANK TAB ----------
    private String accountHolderName;
    private String bankName;
    private String ifscCode;
    private String paymentMethod;
    private String lastPayout;
    private String nextSettlement;

    // ---------- STORE SETTINGS TAB ----------
    private String operatingDaysTime;
    private String serviceablePincodes;

    @Column(length = 2000)
    private String returnRefundPolicy;



    // ---------- SYSTEM FIELDS ----------
    private boolean active = true;
    private Instant createdAt = Instant.now();

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAadharMasked() {
        return aadharMasked;
    }

    public void setAadharMasked(String aadharMasked) {
        this.aadharMasked = aadharMasked;
    }

    public String getBusinessProofUrl() {
        return businessProofUrl;
    }

    public void setBusinessProofUrl(String businessProofUrl) {
        this.businessProofUrl = businessProofUrl;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getLastPayout() {
        return lastPayout;
    }

    public void setLastPayout(String lastPayout) {
        this.lastPayout = lastPayout;
    }

    public String getNextSettlement() {
        return nextSettlement;
    }

    public void setNextSettlement(String nextSettlement) {
        this.nextSettlement = nextSettlement;
    }

    public String getOperatingDaysTime() {
        return operatingDaysTime;
    }

    public void setOperatingDaysTime(String operatingDaysTime) {
        this.operatingDaysTime = operatingDaysTime;
    }

    public String getServiceablePincodes() {
        return serviceablePincodes;
    }

    public void setServiceablePincodes(String serviceablePincodes) {
        this.serviceablePincodes = serviceablePincodes;
    }

    public String getReturnRefundPolicy() {
        return returnRefundPolicy;
    }

    public void setReturnRefundPolicy(String returnRefundPolicy) {
        this.returnRefundPolicy = returnRefundPolicy;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
