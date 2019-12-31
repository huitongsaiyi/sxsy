package com.sayee.sxsy.modules.applet.entity;

import com.sayee.sxsy.common.persistence.DataEntity;


public class Applet extends DataEntity<Applet> {

    private static final long serialVersionUID = 1L;

    private String appId;                   // 微信小程序appID
    private String appSecret;               // 微信小程序appSecret
    private String merchantId;              // 微信商户号
    private String apiKey;                  // 微信支付密钥
    private String apiClientCert;           // 微信支付证书
    private String apiClientKey;            // 微信支付证书密钥
    private String actionNotice;            // 工单受理通知
    private String paymentNotice;           // 支付成功通知
    private String newActionNotice;         // 新工单提醒
    private String focusPicture;            // 焦点图
    private String isOpenPop;               // 是否开启用户使用提示
    private String useTips;                 // 用户使用提示
    private String consultTips;             // 咨询提示
    private String caseTips;                // 经典案例提示

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getActionNotice() {
        return actionNotice;
    }

    public void setActionNotice(String actionNotice) {
        this.actionNotice = actionNotice;
    }

    public String getPaymentNotice() {
        return paymentNotice;
    }

    public void setPaymentNotice(String paymentNotice) {
        this.paymentNotice = paymentNotice;
    }

    public String getNewActionNotice() {
        return newActionNotice;
    }

    public void setNewActionNotice(String newActionNotice) {
        this.newActionNotice = newActionNotice;
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

    public String getUseTips() {
        return useTips;
    }

    public void setUseTips(String useTips) {
        this.useTips = useTips;
    }

    public String getConsultTips() {
        return consultTips;
    }

    public void setConsultTips(String consultTips) {
        this.consultTips = consultTips;
    }

    public String getCaseTips() {
        return caseTips;
    }

    public void setCaseTips(String caseTips) {
        this.caseTips = caseTips;
    }

}
