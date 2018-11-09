
package com.vietinterview.getbee.api.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorLoginResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("message")
    @Expose
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
