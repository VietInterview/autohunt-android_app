
package com.vietinterview.getbee.api.response.ctvprofile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProfileResponse implements Parcelable {

    @SerializedName("addressColl")
    @Expose
    private String addressColl;
    @SerializedName("careerColl")
    @Expose
    private String careerColl;
    @SerializedName("desideratedCareer")
    @Expose
    private List<DesideratedCareer> desideratedCareer = null;
    @SerializedName("emailColl")
    @Expose
    private String emailColl;
    @SerializedName("fullNameColl")
    @Expose
    private String fullNameColl;
    @SerializedName("idColl")
    @Expose
    private Integer idColl;
    @SerializedName("phoneColl")
    @Expose
    private String phoneColl;

    protected MyProfileResponse(Parcel in) {
        addressColl = in.readString();
        careerColl = in.readString();
        emailColl = in.readString();
        fullNameColl = in.readString();
        if (in.readByte() == 0) {
            idColl = null;
        } else {
            idColl = in.readInt();
        }
        phoneColl = in.readString();
    }

    public static final Creator<MyProfileResponse> CREATOR = new Creator<MyProfileResponse>() {
        @Override
        public MyProfileResponse createFromParcel(Parcel in) {
            return new MyProfileResponse(in);
        }

        @Override
        public MyProfileResponse[] newArray(int size) {
            return new MyProfileResponse[size];
        }
    };

    public String getAddressColl() {
        return addressColl;
    }

    public void setAddressColl(String addressColl) {
        this.addressColl = addressColl;
    }

    public String getCareerColl() {
        return careerColl;
    }

    public void setCareerColl(String careerColl) {
        this.careerColl = careerColl;
    }

    public List<DesideratedCareer> getDesideratedCareer() {
        return desideratedCareer;
    }

    public void setDesideratedCareer(List<DesideratedCareer> desideratedCareer) {
        this.desideratedCareer = desideratedCareer;
    }

    public String getEmailColl() {
        return emailColl;
    }

    public void setEmailColl(String emailColl) {
        this.emailColl = emailColl;
    }

    public String getFullNameColl() {
        return fullNameColl;
    }

    public void setFullNameColl(String fullNameColl) {
        this.fullNameColl = fullNameColl;
    }

    public Integer getIdColl() {
        return idColl;
    }

    public void setIdColl(Integer idColl) {
        this.idColl = idColl;
    }

    public String getPhoneColl() {
        return phoneColl;
    }

    public void setPhoneColl(String phoneColl) {
        this.phoneColl = phoneColl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(addressColl);
        parcel.writeString(careerColl);
        parcel.writeString(emailColl);
        parcel.writeString(fullNameColl);
        if (idColl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idColl);
        }
        parcel.writeString(phoneColl);
    }
}
