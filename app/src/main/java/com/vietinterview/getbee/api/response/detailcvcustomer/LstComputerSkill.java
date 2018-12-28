
package com.vietinterview.getbee.api.response.detailcvcustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstComputerSkill {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("msWord")
    @Expose
    private Integer msWord;
    @SerializedName("msExcel")
    @Expose
    private Integer msExcel;
    @SerializedName("msPowerPoint")
    @Expose
    private Integer msPowerPoint;
    @SerializedName("msOutlook")
    @Expose
    private Integer msOutlook;
    @SerializedName("other")
    @Expose
    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getMsWord() {
        return msWord;
    }

    public void setMsWord(Integer msWord) {
        this.msWord = msWord;
    }

    public Integer getMsExcel() {
        return msExcel;
    }

    public void setMsExcel(Integer msExcel) {
        this.msExcel = msExcel;
    }

    public Integer getMsPowerPoint() {
        return msPowerPoint;
    }

    public void setMsPowerPoint(Integer msPowerPoint) {
        this.msPowerPoint = msPowerPoint;
    }

    public Integer getMsOutlook() {
        return msOutlook;
    }

    public void setMsOutlook(Integer msOutlook) {
        this.msOutlook = msOutlook;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
