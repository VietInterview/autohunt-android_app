
package com.vietinterview.getbee.api.response.customerprofile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerWelfare {

    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lstWelfare")
    @Expose
    private List<LstWelfare> lstWelfare = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("welfareId")
    @Expose
    private Integer welfareId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LstWelfare> getLstWelfare() {
        return lstWelfare;
    }

    public void setLstWelfare(List<LstWelfare> lstWelfare) {
        this.lstWelfare = lstWelfare;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

}
