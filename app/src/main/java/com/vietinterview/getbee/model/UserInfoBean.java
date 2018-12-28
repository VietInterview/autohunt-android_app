package com.vietinterview.getbee.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vietinterview.getbee.api.response.account.LstFunctionAuthority;
import com.vietinterview.getbee.api.response.account.LstMenuAuthority;

import java.util.List;

/**
 * Created by LinhNguyen on 9/25/2015.
 */
public class UserInfoBean implements Parcelable {

    public int id;
    public String access_token;
    public String email;
    public String name;
    public String profile_image;
    public String date_of_birth;
    public String my_address;
    public String nickname;
    public int my_job;
    @SerializedName("lstMenuAuthority")
    @Expose
    private List<LstMenuAuthority> lstMenuAuthority = null;
    @SerializedName("lstFunctionAuthority")
    @Expose
    private List<LstFunctionAuthority> lstFunctionAuthority = null;
    @SerializedName("type")
    @Expose
    private Integer type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getMy_address() {
        return my_address;
    }

    public void setMy_address(String my_address) {
        this.my_address = my_address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getMy_job() {
        return my_job;
    }

    public void setMy_job(int my_job) {
        this.my_job = my_job;
    }

    public List<LstMenuAuthority> getLstMenuAuthority() {
        return lstMenuAuthority;
    }

    public void setLstMenuAuthority(List<LstMenuAuthority> lstMenuAuthority) {
        this.lstMenuAuthority = lstMenuAuthority;
    }

    public List<LstFunctionAuthority> getLstFunctionAuthority() {
        return lstFunctionAuthority;
    }

    public void setLstFunctionAuthority(List<LstFunctionAuthority> lstFunctionAuthority) {
        this.lstFunctionAuthority = lstFunctionAuthority;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    protected UserInfoBean(Parcel in) {
        id = in.readInt();
        access_token = in.readString();
        email = in.readString();
        name = in.readString();
        profile_image = in.readString();
        date_of_birth = in.readString();
        my_address = in.readString();
        nickname = in.readString();
        my_job = in.readInt();
        if (in.readByte() == 0) {
            type = null;
        } else {
            type = in.readInt();
        }
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel in) {
            return new UserInfoBean(in);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserInfoBean() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(access_token);
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(profile_image);
        parcel.writeString(date_of_birth);
        parcel.writeString(my_address);
        parcel.writeString(nickname);
        parcel.writeInt(my_job);
        if (type == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(type);
        }
    }
}
