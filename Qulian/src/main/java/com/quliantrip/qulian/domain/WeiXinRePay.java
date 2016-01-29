package com.quliantrip.qulian.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Qulian5 on 2016/1/29.
 */
public class WeiXinRePay extends BaseJson {

    /**
     * appid : wxfc835c6ebff9d032
     * noncestr : 5212628812a2ba6
     * package : Sign=WXPay
     * partnerid : 1288903801
     * prepayid : wx201601291700176a877d76840852113533
     * timestamp : 1454058017
     * sign : F7709CF8F6C3F50214D07F40FF48A077
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    private String sign;

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }
}
