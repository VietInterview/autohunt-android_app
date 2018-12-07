
package com.vietinterview.getbee.api.response.customerprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerImg {

    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
