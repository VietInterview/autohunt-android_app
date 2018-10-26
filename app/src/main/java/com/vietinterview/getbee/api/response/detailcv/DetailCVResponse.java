
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailCVResponse implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("careerName")
    @Expose
    private String careerName;
    @SerializedName("careerObjectives")
    @Expose
    private String careerObjectives;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("collaboratorId")
    @Expose
    private Integer collaboratorId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("createdBy")
    @Expose
    private Integer createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("currencyId")
    @Expose
    private Integer currencyId;
    @SerializedName("currentLevelId")
    @Expose
    private Integer currentLevelId;
    @SerializedName("desiredLevelId")
    @Expose
    private Integer desiredLevelId;
    @SerializedName("desiredSalary")
    @Expose
    private Integer desiredSalary;
    @SerializedName("educationLevelId")
    @Expose
    private Integer educationLevelId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("experienceYearId")
    @Expose
    private Integer experienceYearId;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("fromSalary")
    @Expose
    private Integer fromSalary;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobCareerName")
    @Expose
    private String jobCareerName;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("jobListcityName")
    @Expose
    private String jobListcityName;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("levelName")
    @Expose
    private String levelName;
    @SerializedName("lstCareerId")
    @Expose
    private String lstCareerId;
    @SerializedName("lstComputerSkill")
    @Expose
    private List<LstComputerSkill> lstComputerSkill = null;
    @SerializedName("lstCvSkill")
    @Expose
    private List<LstCvSkill> lstCvSkill = null;
    @SerializedName("lstEducationHis")
    @Expose
    private List<LstEducationHi> lstEducationHis = null;
    @SerializedName("lstEmploymentHis")
    @Expose
    private List<LstEmploymentHi> lstEmploymentHis = null;
    @SerializedName("lstJobCityId")
    @Expose
    private String lstJobCityId;
    @SerializedName("lstLanguage")
    @Expose
    private List<LstLanguage> lstLanguage = null;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("supporterId")
    @Expose
    private Integer supporterId;
    @SerializedName("toSalary")
    @Expose
    private Integer toSalary;
    @SerializedName("updatedBy")
    @Expose
    private Integer updatedBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("workingFormId")
    @Expose
    private Integer workingFormId;

    protected DetailCVResponse(Parcel in) {
        address = in.readString();
        if (in.readByte() == 0) {
            birthday = null;
        } else {
            birthday = in.readInt();
        }
        careerName = in.readString();
        careerObjectives = in.readString();
        if (in.readByte() == 0) {
            cityId = null;
        } else {
            cityId = in.readInt();
        }
        if (in.readByte() == 0) {
            collaboratorId = null;
        } else {
            collaboratorId = in.readInt();
        }
        companyName = in.readString();
        if (in.readByte() == 0) {
            createdBy = null;
        } else {
            createdBy = in.readInt();
        }
        createdDate = in.readString();
        if (in.readByte() == 0) {
            currencyId = null;
        } else {
            currencyId = in.readInt();
        }
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
        if (in.readByte() == 0) {
            desiredSalary = null;
        } else {
            desiredSalary = in.readInt();
        }
        if (in.readByte() == 0) {
            educationLevelId = null;
        } else {
            educationLevelId = in.readInt();
        }
        email = in.readString();
        if (in.readByte() == 0) {
            experienceYearId = null;
        } else {
            experienceYearId = in.readInt();
        }
        if (in.readByte() == 0) {
            fee = null;
        } else {
            fee = in.readInt();
        }
        if (in.readByte() == 0) {
            fromSalary = null;
        } else {
            fromSalary = in.readInt();
        }
        fullName = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        jobCareerName = in.readString();
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        jobListcityName = in.readString();
        jobTitle = in.readString();
        levelName = in.readString();
        lstCareerId = in.readString();
        lstJobCityId = in.readString();
        phone = in.readString();
        pictureUrl = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            supporterId = null;
        } else {
            supporterId = in.readInt();
        }
        if (in.readByte() == 0) {
            toSalary = null;
        } else {
            toSalary = in.readInt();
        }
        if (in.readByte() == 0) {
            updatedBy = null;
        } else {
            updatedBy = in.readInt();
        }
        updatedDate = in.readString();
        if (in.readByte() == 0) {
            workingFormId = null;
        } else {
            workingFormId = in.readInt();
        }
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public String getCareerObjectives() {
        return careerObjectives;
    }

    public void setCareerObjectives(String careerObjectives) {
        this.careerObjectives = careerObjectives;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCollaboratorId() {
        return collaboratorId;
    }

    public void setCollaboratorId(Integer collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
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

    public Integer getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(Integer desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExperienceYearId() {
        return experienceYearId;
    }

    public void setExperienceYearId(Integer experienceYearId) {
        this.experienceYearId = experienceYearId;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Integer fromSalary) {
        this.fromSalary = fromSalary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobCareerName() {
        return jobCareerName;
    }

    public void setJobCareerName(String jobCareerName) {
        this.jobCareerName = jobCareerName;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobListcityName() {
        return jobListcityName;
    }

    public void setJobListcityName(String jobListcityName) {
        this.jobListcityName = jobListcityName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLstCareerId() {
        return lstCareerId;
    }

    public void setLstCareerId(String lstCareerId) {
        this.lstCareerId = lstCareerId;
    }

    public List<LstComputerSkill> getLstComputerSkill() {
        return lstComputerSkill;
    }

    public void setLstComputerSkill(List<LstComputerSkill> lstComputerSkill) {
        this.lstComputerSkill = lstComputerSkill;
    }

    public List<LstCvSkill> getLstCvSkill() {
        return lstCvSkill;
    }

    public void setLstCvSkill(List<LstCvSkill> lstCvSkill) {
        this.lstCvSkill = lstCvSkill;
    }

    public List<LstEducationHi> getLstEducationHis() {
        return lstEducationHis;
    }

    public void setLstEducationHis(List<LstEducationHi> lstEducationHis) {
        this.lstEducationHis = lstEducationHis;
    }

    public List<LstEmploymentHi> getLstEmploymentHis() {
        return lstEmploymentHis;
    }

    public void setLstEmploymentHis(List<LstEmploymentHi> lstEmploymentHis) {
        this.lstEmploymentHis = lstEmploymentHis;
    }

    public String getLstJobCityId() {
        return lstJobCityId;
    }

    public void setLstJobCityId(String lstJobCityId) {
        this.lstJobCityId = lstJobCityId;
    }

    public List<LstLanguage> getLstLanguage() {
        return lstLanguage;
    }

    public void setLstLanguage(List<LstLanguage> lstLanguage) {
        this.lstLanguage = lstLanguage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(Integer supporterId) {
        this.supporterId = supporterId;
    }

    public Integer getToSalary() {
        return toSalary;
    }

    public void setToSalary(Integer toSalary) {
        this.toSalary = toSalary;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getWorkingFormId() {
        return workingFormId;
    }

    public void setWorkingFormId(Integer workingFormId) {
        this.workingFormId = workingFormId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        if (birthday == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(birthday);
        }
        parcel.writeString(careerName);
        parcel.writeString(careerObjectives);
        if (cityId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cityId);
        }
        if (collaboratorId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(collaboratorId);
        }
        parcel.writeString(companyName);
        if (createdBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(createdBy);
        }
        parcel.writeString(createdDate);
        if (currencyId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currencyId);
        }
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
        if (desiredSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(desiredSalary);
        }
        if (educationLevelId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(educationLevelId);
        }
        parcel.writeString(email);
        if (experienceYearId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(experienceYearId);
        }
        if (fee == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fee);
        }
        if (fromSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fromSalary);
        }
        parcel.writeString(fullName);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(jobCareerName);
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        parcel.writeString(jobListcityName);
        parcel.writeString(jobTitle);
        parcel.writeString(levelName);
        parcel.writeString(lstCareerId);
        parcel.writeString(lstJobCityId);
        parcel.writeString(phone);
        parcel.writeString(pictureUrl);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        if (supporterId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(supporterId);
        }
        if (toSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toSalary);
        }
        if (updatedBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(updatedBy);
        }
        parcel.writeString(updatedDate);
        if (workingFormId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(workingFormId);
        }
    }
}
