
package com.vietinterview.getbee.api.response.detailcv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstComputerSkill {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("msEexcel")
    @Expose
    private Integer msEexcel;
    @SerializedName("msOutlook")
    @Expose
    private Integer msOutlook;
    @SerializedName("msPowerPoint")
    @Expose
    private Integer msPowerPoint;
    @SerializedName("msWord")
    @Expose
    private Integer msWord;
    @SerializedName("other")
    @Expose
    private String other;

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsEexcel() {
        return msEexcel;
    }

    public void setMsEexcel(Integer msEexcel) {
        this.msEexcel = msEexcel;
    }

    public Integer getMsOutlook() {
        return msOutlook;
    }

    public void setMsOutlook(Integer msOutlook) {
        this.msOutlook = msOutlook;
    }

    public Integer getMsPowerPoint() {
        return msPowerPoint;
    }

    public void setMsPowerPoint(Integer msPowerPoint) {
        this.msPowerPoint = msPowerPoint;
    }

    public Integer getMsWord() {
        return msWord;
    }

    public void setMsWord(Integer msWord) {
        this.msWord = msWord;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
