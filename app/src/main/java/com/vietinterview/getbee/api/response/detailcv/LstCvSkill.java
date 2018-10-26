
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstCvSkill implements Parcelable {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("hobby")
    @Expose
    private String hobby;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("otherSkill")
    @Expose
    private String otherSkill;
    @SerializedName("primarySkill")
    @Expose
    private String primarySkill;

    protected LstCvSkill(Parcel in) {
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        hobby = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        otherSkill = in.readString();
        primarySkill = in.readString();
    }

    public static final Creator<LstCvSkill> CREATOR = new Creator<LstCvSkill>() {
        @Override
        public LstCvSkill createFromParcel(Parcel in) {
            return new LstCvSkill(in);
        }

        @Override
        public LstCvSkill[] newArray(int size) {
            return new LstCvSkill[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (cvId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cvId);
        }
        parcel.writeString(hobby);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(otherSkill);
        parcel.writeString(primarySkill);
    }
}
