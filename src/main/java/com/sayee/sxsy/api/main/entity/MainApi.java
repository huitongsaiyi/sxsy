package com.sayee.sxsy.api.main.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description 首页
 */
public class MainApi extends DataEntity<MainApi> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String appId;
    private String consultTips;
    private String paymentNotice;
    private String actionNotice;
    private String newActionNotice;
    private String appSecret;
    private String merchantId;
    private String apiKey;
    private String apiClientCert;
    private String apiClientKey;
    private String focusPicture;
    private String isOpenPop;
    private String caseTips;
    private String useTips;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getConsultTips() {
        return consultTips;
    }

    public void setConsultTips(String consultTips) {
        this.consultTips = consultTips;
    }

    public String getPaymentNotice() {
        return paymentNotice;
    }

    public void setPaymentNotice(String paymentNotice) {
        this.paymentNotice = paymentNotice;
    }

    public String getActionNotice() {
        return actionNotice;
    }

    public void setActionNotice(String actionNotice) {
        this.actionNotice = actionNotice;
    }

    public String getNewActionNotice() {
        return newActionNotice;
    }

    public void setNewActionNotice(String newActionNotice) {
        this.newActionNotice = newActionNotice;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiClientCert() {
        return apiClientCert;
    }

    public void setApiClientCert(String apiClientCert) {
        this.apiClientCert = apiClientCert;
    }

    public String getApiClientKey() {
        return apiClientKey;
    }

    public void setApiClientKey(String apiClientKey) {
        this.apiClientKey = apiClientKey;
    }

    public String getFocusPicture() {
        return focusPicture;
    }

    public void setFocusPicture(String focusPicture) {
        this.focusPicture = focusPicture;
    }

    public String getIsOpenPop() {
        return isOpenPop;
    }

    public void setIsOpenPop(String isOpenPop) {
        this.isOpenPop = isOpenPop;
    }

    public String getCaseTips() {
        return caseTips;
    }

    public void setCaseTips(String caseTips) {
        this.caseTips = caseTips;
    }

    public String getUseTips() {
        return useTips;
    }

    public void setUseTips(String useTips) {
        this.useTips = useTips;
    }
}
