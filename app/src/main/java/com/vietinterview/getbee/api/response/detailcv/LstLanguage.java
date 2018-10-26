
package com.vietinterview.getbee.api.response.detailcv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstLanguage {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("languageId")
    @Expose
    private Integer languageId;
    @SerializedName("languageName")
    @Expose
    private String languageName;
    @SerializedName("listen")
    @Expose
    private Integer listen;
    @SerializedName("read")
    @Expose
    private Integer read;
    @SerializedName("speak")
    @Expose
    private Integer speak;
    @SerializedName("write")
    @Expose
    private Integer write;

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

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getListen() {
        return listen;
    }

    public void setListen(Integer listen) {
        this.listen = listen;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Integer getSpeak() {
        return speak;
    }

    public void setSpeak(Integer speak) {
        this.speak = speak;
    }

    public Integer getWrite() {
        return write;
    }

    public void setWrite(Integer write) {
        this.write = write;
    }

}
