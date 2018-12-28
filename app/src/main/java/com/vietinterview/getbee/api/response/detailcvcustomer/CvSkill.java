
package com.vietinterview.getbee.api.response.detailcvcustomer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvSkill {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("primarySkill")
    @Expose
    private String primarySkill;
    @SerializedName("otherSkill")
    @Expose
    private String otherSkill;
    @SerializedName("lstOtherSkillName")
    @Expose
    private List<String> lstOtherSkillName = null;
    @SerializedName("hobby")
    @Expose
    private String hobby;

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

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getOtherSkill() {
        return otherSkill;
    }

    public void setOtherSkill(String otherSkill) {
        this.otherSkill = otherSkill;
    }

    public List<String> getLstOtherSkillName() {
        return lstOtherSkillName;
    }

    public void setLstOtherSkillName(List<String> lstOtherSkillName) {
        this.lstOtherSkillName = lstOtherSkillName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
