
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
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("careerColl")
    @Expose
    private String careerColl;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("contractDate")
    @Expose
    private Integer contractDate;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;
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
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("phoneColl")
    @Expose
    private String phoneColl;

    protected MyProfileResponse(Parcel in) {
        addressColl = in.readString();
        if (in.readByte() == 0) {
            birthday = null;
        } else {
            birthday = in.readInt();
        }
        careerColl = in.readString();
        code = in.readString();
        companyName = in.readString();
        if (in.readByte() == 0) {
            contractDate = null;
        } else {
            contractDate = in.readInt();
        }
        emailColl = in.readString();
        fullNameColl = in.readString();
        if (in.readByte() == 0) {
            idColl = null;
        } else {
            idColl = in.readInt();
        }
        imageUrl = in.readString();
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

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getCareerColl() {
        return careerColl;
    }

    public void setCareerColl(String careerColl) {
        this.careerColl = careerColl;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getContractDate() {
        return contractDate;
    }

    public void setContractDate(Integer contractDate) {
        this.contractDate = contractDate;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        if (birthday == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(birthday);
        }
        parcel.writeString(careerColl);
        parcel.writeString(code);
        parcel.writeString(companyName);
        if (contractDate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(contractDate);
        }
        parcel.writeString(emailColl);
        parcel.writeString(fullNameColl);
        if (idColl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idColl);
        }
        parcel.writeString(imageUrl);
        parcel.writeString(phoneColl);
    }
}
