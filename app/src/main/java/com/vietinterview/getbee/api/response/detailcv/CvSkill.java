
package com.vietinterview.getbee.api.response.detailcv;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvSkill {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("hobby")
    @Expose
    private String hobby;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lstOtherSkillName")
    @Expose
    private List<String> lstOtherSkillName = null;
    @SerializedName("otherSkill")
    @Expose
    private String otherSkill;
    @SerializedName("primarySkill")
    @Expose
    private String primarySkill;

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getLstOtherSkillName() {
        return lstOtherSkillName;
    }

    public void setLstOtherSkillName(List<String> lstOtherSkillName) {
        this.lstOtherSkillName = lstOtherSkillName;
    }

    public String getOtherSkill() {
        return otherSkill;
    }

    public void setOtherSkill(String otherSkill) {
        this.otherSkill = otherSkill;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

}
