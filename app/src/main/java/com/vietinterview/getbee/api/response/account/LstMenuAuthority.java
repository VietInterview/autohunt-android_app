
package com.vietinterview.getbee.api.response.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMenuAuthority {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;

    public LstMenuAuthority(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof LstMenuAuthority) {
            LstMenuAuthority temp = (LstMenuAuthority) obj;
            if (this.name == temp.name && this.code == temp.code && this.id == temp.id)
                return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.name.hashCode() + this.code.hashCode() + this.id.hashCode());
    }
}
