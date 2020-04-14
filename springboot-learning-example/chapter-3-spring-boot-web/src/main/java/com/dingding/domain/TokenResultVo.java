package com.dingding.domain;

public class TokenResultVo {
    private int errcode;
    private String accessToken;
    private String errmsg;
    private int expiresIn;

    public TokenResultVo() {
    }

    public TokenResultVo(int errcode, String accessToken, String errmsg, int expiresIn) {
        this.errcode = errcode;
        this.accessToken = accessToken;
        this.errmsg = errmsg;
        this.expiresIn = expiresIn;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
