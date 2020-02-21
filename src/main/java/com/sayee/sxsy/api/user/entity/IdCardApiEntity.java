package com.sayee.sxsy.api.user.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class IdCardApiEntity extends DataEntity<IdCardApiEntity> {
    private static final long serialVersionUID = 1L;
    private String cardId;
    private String wechatUserId;
    private String cardA;
    private String cardB;
    private String cardHand;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public String getCardA() {
        return cardA;
    }

    public void setCardA(String cardA) {
        this.cardA = cardA;
    }

    public String getCardB() {
        return cardB;
    }

    public void setCardB(String cardB) {
        this.cardB = cardB;
    }

    public String getCardHand() {
        return cardHand;
    }

    public void setCardHand(String cardHand) {
        this.cardHand = cardHand;
    }
}
