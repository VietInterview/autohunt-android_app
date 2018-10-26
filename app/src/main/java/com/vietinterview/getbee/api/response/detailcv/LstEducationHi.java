
package com.vietinterview.getbee.api.response.detailcv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEducationHi {

    @SerializedName("career")
    @Expose
    private String career;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("graduationType")
    @Expose
    private Integer graduationType;
    @SerializedName("graduationTypeName")
    @Expose
    private String graduationTypeName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getGraduationType() {
        return graduationType;
    }

    public void setGraduationType(Integer graduationType) {
        this.graduationType = graduationType;
    }

    public String getGraduationTypeName() {
        return graduationTypeName;
    }

    public void setGraduationTypeName(String graduationTypeName) {
        this.graduationTypeName = graduationTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

}
