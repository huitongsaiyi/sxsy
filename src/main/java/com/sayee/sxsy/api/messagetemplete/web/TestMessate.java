package com.sayee.sxsy.api.messagetemplete.web;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description
 */
public class TestMessate {
    public static void main(String[] args) {
        MessageTemplete messageTemplete=new MessageTemplete("wx5c31f4c601318ad7","9629a3f3389bd0484028f668ed9bf929");
        JSONObject token=messageTemplete.send("2MATy-X0hl2DF5hLpA7a3Q2qi56nK6PGOcF6V8E8oXA");
        System.out.println("token"+token);
    }
}
