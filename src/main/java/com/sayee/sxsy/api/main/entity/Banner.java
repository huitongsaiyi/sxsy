package com.sayee.sxsy.api.main.entity;

import java.io.Serializable;

/**
 * @Description
 */
public class Banner implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Url;
    private String jumpUrl;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
