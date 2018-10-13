package com.utils;

public class RcvMsg {
    private String type;
    private RcvLoginContent content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RcvLoginContent getContent() {
        return content;
    }

    public void setContent(RcvLoginContent content) {
        this.content = content;
    }
}
