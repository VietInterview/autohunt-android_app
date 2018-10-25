
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailCVResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("currentLevelId")
    @Expose
    private Integer currentLevelId;
    @SerializedName("desiredLevelId")
    @Expose
    private Integer desiredLevelId;
    @SerializedName("lstCareerId")
    @Expose
    private String lstCareerId;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("educationLevelId")
    @Expose
    private Integer educationLevelId;
    @SerializedName("experienceYearId")
    @Expose
    private Integer experienceYearId;
    @SerializedName("workingFormId")
    @Expose
    private Integer workingFormId;
    @SerializedName("desiredSalary")
    @Expose
    private Integer desiredSalary;
    @SerializedName("careerObjectives")
    @Expose
    private String careerObjectives;
    @SerializedName("collaboratorId")
    @Expose
    private Integer collaboratorId;
    @SerializedName("supporterId")
    @Expose
    private Integer supporterId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("createdBy")
    @Expose
    private Integer createdBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("updatedBy")
    @Expose
    private Integer updatedBy;
    @SerializedName("careerName")
    @Expose
    private String careerName;
    @SerializedName("currencyId")
    @Expose
    private Integer currencyId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("lstJobCityId")
    @Expose
    private String lstJobCityId;
    @SerializedName("jobListcityName")
    @Expose
    private String jobListcityName;
    @SerializedName("jobCareerName")
    @Expose
    private String jobCareerName;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("toSalary")
    @Expose
    private Integer toSalary;
    @SerializedName("fromSalary")
    @Expose
    private Integer fromSalary;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("levelName")
    @Expose
    private String levelName;
    @SerializedName("lstEmploymentHis")
    @Expose
    private List<LstEmploymentHi> lstEmploymentHis = null;
    @SerializedName("lstEducationHis")
    @Expose
    private List<Object> lstEducationHis = null;
    @SerializedName("lstLanguage")
    @Expose
    private List<Object> lstLanguage = null;
    @SerializedName("lstComputerSkill")
    @Expose
    private List<Object> lstComputerSkill = null;
    @SerializedName("lstCvSkill")
    @Expose
    private List<Object> lstCvSkill = null;

    protected DetailCVResponse(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        fullName = in.readString();
        email = in.readString();
        phone = in.readString();
        if (in.readByte() == 0) {
            birthday = null;
        } else {
            birthday = in.readInt();
        }
        address = in.readString();
        pictureUrl = in.readString();
        if (in.readByte() == 0) {
            currentLevelId = null;
        } else {
            currentLevelId = in.readInt();
        }
        if (in.readByte() == 0) {
            desiredLevelId = null;
        } else {
            desiredLevelId = in.readInt();
        }
        lstCareerId = in.readString();
        if (in.readByte() == 0) {
            cityId = null;
        } else {
            cityId = in.readInt();
        }
        if (in.readByte() == 0) {
            educationLevelId = null;
        } else {
            educationLevelId = in.readInt();
        }
        if (in.readByte() == 0) {
            experienceYearId = null;
        } else {
            experienceYearId = in.readInt();
        }
        if (in.readByte() == 0) {
            workingFormId = null;
        } else {
            workingFormId = in.readInt();
        }
        if (in.readByte() == 0) {
            desiredSalary = null;
        } else {
            desiredSalary = in.readInt();
        }
        careerObjectives = in.readString();
        if (in.readByte() == 0) {
            collaboratorId = null;
        } else {
            collaboratorId = in.readInt();
        }
        if (in.readByte() == 0) {
            supporterId = null;
        } else {
            supporterId = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        createdDate = in.readString();
        if (in.readByte() == 0) {
            createdBy = null;
        } else {
            createdBy = in.readInt();
        }
        updatedDate = in.readString();
        if (in.readByte() == 0) {
            updatedBy = null;
        } else {
            updatedBy = in.readInt();
        }
        careerName = in.readString();
        if (in.readByte() == 0) {
            currencyId = null;
        } else {
            currencyId = in.readInt();
        }
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        companyName = in.readString();
        lstJobCityId = in.readString();
        jobListcityName = in.readString();
        jobCareerName = in.readString();
        jobTitle = in.readString();
        if (in.readByte() == 0) {
            toSalary = null;
        } else {
            toSalary = in.readInt();
        }
        if (in.readByte() == 0) {
            fromSalary = null;
        } else {
            fromSalary = in.readInt();
        }
        if (in.readByte() == 0) {
            fee = null;
        } else {
            fee = in.readInt();
        }
        levelName = in.readString();
    }

    public static final Creator<DetailCVResponse> CREATOR = new Creator<DetailCVResponse>() {
        @Override
        public DetailCVResponse createFromParcel(Parcel in) {
            return new DetailCVResponse(in);
        }

        @Override
        public DetailCVResponse[] newArray(int size) {
            return new DetailCVResponse[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getCurrentLevelId() {
        return currentLevelId;
    }

    public void setCurrentLevelId(Integer currentLevelId) {
        this.currentLevelId = currentLevelId;
    }

    public Integer getDesiredLevelId() {
        return desiredLevelId;
    }

    public void setDesiredLevelId(Integer desiredLevelId) {
        this.desiredLevelId = desiredLevelId;
    }

    public String getLstCareerId() {
        return lstCareerId;
    }

    public void setLstCareerId(String lstCareerId) {
        this.lstCareerId = lstCareerId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public Integer getExperienceYearId() {
        return experienceYearId;
    }

    public void setExperienceYearId(Integer experienceYearId) {
        this.experienceYearId = experienceYearId;
    }

    public Integer getWorkingFormId() {
        return workingFormId;
    }

    public void setWorkingFormId(Integer workingFormId) {
        this.workingFormId = workingFormId;
    }

    public Integer getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(Integer desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getCareerObjectives() {
        return careerObjectives;
    }

    public void setCareerObjectives(String careerObjectives) {
        this.careerObjectives = careerObjectives;
    }

    public Integer getCollaboratorId() {
        return collaboratorId;
    }

    public void setCollaboratorId(Integer collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    public Integer getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(Integer supporterId) {
        this.supporterId = supporterId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLstJobCityId() {
        return lstJobCityId;
    }

    public void setLstJobCityId(String lstJobCityId) {
        this.lstJobCityId = lstJobCityId;
    }

    public String getJobListcityName() {
        return jobListcityName;
    }

    public void setJobListcityName(String jobListcityName) {
        this.jobListcityName = jobListcityName;
    }

    public String getJobCareerName() {
        return jobCareerName;
    }

    public void setJobCareerName(String jobCareerName) {
        this.jobCareerName = jobCareerName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getToSalary() {
        return toSalary;
    }

    public void setToSalary(Integer toSalary) {
        this.toSalary = toSalary;
    }

    public Integer getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Integer fromSalary) {
        this.fromSalary = fromSalary;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<LstEmploymentHi> getLstEmploymentHis() {
        return lstEmploymentHis;
    }

    public void setLstEmploymentHis(List<LstEmploymentHi> lstEmploymentHis) {
        this.lstEmploymentHis = lstEmploymentHis;
    }

    public List<Object> getLstEducationHis() {
        return lstEducationHis;
    }

    public void setLstEducationHis(List<Object> lstEducationHis) {
        this.lstEducationHis = lstEducationHis;
    }

    public List<Object> getLstLanguage() {
        return lstLanguage;
    }

    public void setLstLanguage(List<Object> lstLanguage) {
        this.lstLanguage = lstLanguage;
    }

    public List<Object> getLstComputerSkill() {
        return lstComputerSkill;
    }

    public void setLstComputerSkill(List<Object> lstComputerSkill) {
        this.lstComputerSkill = lstComputerSkill;
    }

    public List<Object> getLstCvSkill() {
        return lstCvSkill;
    }

    public void setLstCvSkill(List<Object> lstCvSkill) {
        this.lstCvSkill = lstCvSkill;
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
        parcel.writeString(fullName);
        parcel.writeString(email);
        parcel.writeString(phone);
        if (birthday == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(birthday);
        }
        parcel.writeString(address);
        parcel.writeString(pictureUrl);
        if (currentLevelId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currentLevelId);
        }
        if (desiredLevelId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(desiredLevelId);
        }
        parcel.writeString(lstCareerId);
        if (cityId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cityId);
        }
        if (educationLevelId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(educationLevelId);
        }
        if (experienceYearId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(experienceYearId);
        }
        if (workingFormId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(workingFormId);
        }
        if (desiredSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(desiredSalary);
        }
        parcel.writeString(careerObjectives);
        if (collaboratorId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(collaboratorId);
        }
        if (supporterId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(supporterId);
        }
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        parcel.writeString(createdDate);
        if (createdBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(createdBy);
        }
        parcel.writeString(updatedDate);
        if (updatedBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(updatedBy);
        }
        parcel.writeString(careerName);
        if (currencyId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currencyId);
        }
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        parcel.writeString(companyName);
        parcel.writeString(lstJobCityId);
        parcel.writeString(jobListcityName);
        parcel.writeString(jobCareerName);
        parcel.writeString(jobTitle);
        if (toSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toSalary);
        }
        if (fromSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fromSalary);
        }
        if (fee == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fee);
        }
        parcel.writeString(levelName);
    }
}
