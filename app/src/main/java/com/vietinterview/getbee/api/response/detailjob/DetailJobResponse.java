
package com.vietinterview.getbee.api.response.detailjob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailJobResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("careerId")
    @Expose
    private Integer careerId;
    @SerializedName("jobLevel")
    @Expose
    private Integer jobLevel;
    @SerializedName("listcityName")
    @Expose
    private String listcityName;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("fromSalary")
    @Expose
    private Long fromSalary;
    @SerializedName("toSalary")
    @Expose
    private Long toSalary;
    @SerializedName("currency")
    @Expose
    private Integer currency;
    @SerializedName("fee")
    @Expose
    private Long fee;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("submitDate")
    @Expose
    private String submitDate;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("isHotjob")
    @Expose
    private Integer isHotjob;
    @SerializedName("countCv")
    @Expose
    private Integer countCv;
    @SerializedName("countColl")
    @Expose
    private Integer countColl;
    @SerializedName("countInterview")
    @Expose
    private Integer countInterview;
    @SerializedName("countOffer")
    @Expose
    private Integer countOffer;
    @SerializedName("countGotoWork")
    @Expose
    private Integer countGotoWork;
    @SerializedName("collStatus")
    @Expose
    private Integer collStatus;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("careerName")
    @Expose
    private String careerName;
    @SerializedName("myCountCv")
    @Expose
    private Integer myCountCv;
    @SerializedName("myCountInviteInterview")
    @Expose
    private Integer myCountInviteInterview;
    @SerializedName("myCountInterview")
    @Expose
    private Integer myCountInterview;
    @SerializedName("myCountOffer")
    @Expose
    private Integer myCountOffer;
    @SerializedName("myCountGotoWork")
    @Expose
    private Integer myCountGotoWork;
    @SerializedName("companyImg")
    @Expose
    private String companyImg;
    @SerializedName("lstJobApply")
    @Expose
    private List<LstJobApply> lstJobApply = null;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("jobLevelName")
    @Expose
    private String jobLevelName;
    @SerializedName("jobRequirements")
    @Expose
    private String jobRequirements;
    protected DetailJobResponse(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            customerId = null;
        } else {
            customerId = in.readInt();
        }
        jobTitle = in.readString();
        if (in.readByte() == 0) {
            careerId = null;
        } else {
            careerId = in.readInt();
        }
        if (in.readByte() == 0) {
            jobLevel = null;
        } else {
            jobLevel = in.readInt();
        }
        listcityName = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        if (in.readByte() == 0) {
            fromSalary = null;
        } else {
            fromSalary = in.readLong();
        }
        if (in.readByte() == 0) {
            toSalary = null;
        } else {
            toSalary = in.readLong();
        }
        if (in.readByte() == 0) {
            currency = null;
        } else {
            currency = in.readInt();
        }
        if (in.readByte() == 0) {
            fee = null;
        } else {
            fee = in.readLong();
        }
        jobDescription = in.readString();
        submitDate = in.readString();
        expireDate = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            isHotjob = null;
        } else {
            isHotjob = in.readInt();
        }
        if (in.readByte() == 0) {
            countCv = null;
        } else {
            countCv = in.readInt();
        }
        if (in.readByte() == 0) {
            countColl = null;
        } else {
            countColl = in.readInt();
        }
        if (in.readByte() == 0) {
            countInterview = null;
        } else {
            countInterview = in.readInt();
        }
        if (in.readByte() == 0) {
            countOffer = null;
        } else {
            countOffer = in.readInt();
        }
        if (in.readByte() == 0) {
            countGotoWork = null;
        } else {
            countGotoWork = in.readInt();
        }
        if (in.readByte() == 0) {
            collStatus = null;
        } else {
            collStatus = in.readInt();
        }
        companyName = in.readString();
        careerName = in.readString();
        if (in.readByte() == 0) {
            myCountCv = null;
        } else {
            myCountCv = in.readInt();
        }
        if (in.readByte() == 0) {
            myCountInviteInterview = null;
        } else {
            myCountInviteInterview = in.readInt();
        }
        if (in.readByte() == 0) {
            myCountInterview = null;
        } else {
            myCountInterview = in.readInt();
        }
        if (in.readByte() == 0) {
            myCountOffer = null;
        } else {
            myCountOffer = in.readInt();
        }
        if (in.readByte() == 0) {
            myCountGotoWork = null;
        } else {
            myCountGotoWork = in.readInt();
        }
    }

    public static final Creator<DetailJobResponse> CREATOR = new Creator<DetailJobResponse>() {
        @Override
        public DetailJobResponse createFromParcel(Parcel in) {
            return new DetailJobResponse(in);
        }

        @Override
        public DetailJobResponse[] newArray(int size) {
            return new DetailJobResponse[size];
        }
    };

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getListcityName() {
        return listcityName;
    }

    public void setListcityName(String listcityName) {
        this.listcityName = listcityName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Long fromSalary) {
        this.fromSalary = fromSalary;
    }

    public Long getToSalary() {
        return toSalary;
    }

    public void setToSalary(Long toSalary) {
        this.toSalary = toSalary;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHotjob() {
        return isHotjob;
    }

    public void setIsHotjob(Integer isHotjob) {
        this.isHotjob = isHotjob;
    }

    public Integer getCountCv() {
        return countCv;
    }

    public void setCountCv(Integer countCv) {
        this.countCv = countCv;
    }

    public Integer getCountColl() {
        return countColl;
    }

    public void setCountColl(Integer countColl) {
        this.countColl = countColl;
    }

    public Integer getCountInterview() {
        return countInterview;
    }

    public void setCountInterview(Integer countInterview) {
        this.countInterview = countInterview;
    }

    public Integer getCountOffer() {
        return countOffer;
    }

    public void setCountOffer(Integer countOffer) {
        this.countOffer = countOffer;
    }

    public Integer getCountGotoWork() {
        return countGotoWork;
    }

    public void setCountGotoWork(Integer countGotoWork) {
        this.countGotoWork = countGotoWork;
    }

    public Integer getCollStatus() {
        return collStatus;
    }

    public void setCollStatus(Integer collStatus) {
        this.collStatus = collStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Integer getMyCountCv() {
        return myCountCv;
    }

    public void setMyCountCv(Integer myCountCv) {
        this.myCountCv = myCountCv;
    }

    public Integer getMyCountInviteInterview() {
        return myCountInviteInterview;
    }

    public void setMyCountInviteInterview(Integer myCountInviteInterview) {
        this.myCountInviteInterview = myCountInviteInterview;
    }

    public Integer getMyCountInterview() {
        return myCountInterview;
    }

    public void setMyCountInterview(Integer myCountInterview) {
        this.myCountInterview = myCountInterview;
    }

    public Integer getMyCountOffer() {
        return myCountOffer;
    }

    public void setMyCountOffer(Integer myCountOffer) {
        this.myCountOffer = myCountOffer;
    }

    public Integer getMyCountGotoWork() {
        return myCountGotoWork;
    }

    public void setMyCountGotoWork(Integer myCountGotoWork) {
        this.myCountGotoWork = myCountGotoWork;
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }

    public List<LstJobApply> getLstJobApply() {
        return lstJobApply;
    }

    public void setLstJobApply(List<LstJobApply> lstJobApply) {
        this.lstJobApply = lstJobApply;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (customerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(customerId);
        }
        parcel.writeString(jobTitle);
        if (careerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(careerId);
        }
        if (jobLevel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobLevel);
        }
        parcel.writeString(listcityName);
        if (quantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(quantity);
        }
        if (fromSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(fromSalary);
        }
        if (toSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(toSalary);
        }
        if (currency == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currency);
        }
        if (fee == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(fee);
        }
        parcel.writeString(jobDescription);
        parcel.writeString(submitDate);
        parcel.writeString(expireDate);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        if (isHotjob == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isHotjob);
        }
        if (countCv == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countCv);
        }
        if (countColl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countColl);
        }
        if (countInterview == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countInterview);
        }
        if (countOffer == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countOffer);
        }
        if (countGotoWork == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countGotoWork);
        }
        if (collStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(collStatus);
        }
        parcel.writeString(companyName);
        parcel.writeString(careerName);
        if (myCountCv == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(myCountCv);
        }
        if (myCountInviteInterview == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(myCountInviteInterview);
        }
        if (myCountInterview == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(myCountInterview);
        }
        if (myCountOffer == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(myCountOffer);
        }
        if (myCountGotoWork == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(myCountGotoWork);
        }
    }
}
