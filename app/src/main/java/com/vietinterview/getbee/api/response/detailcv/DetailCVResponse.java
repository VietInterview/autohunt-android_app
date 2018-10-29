
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
    @SerializedName("city")
    @Expose
    private City city;
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
    @SerializedName("currencyName")
    @Expose
    private Integer currencyName;
    @SerializedName("currentLevel")
    @Expose
    private CurrentLevel currentLevel;
    @SerializedName("cvSkill")
    @Expose
    private CvSkill cvSkill;
    @SerializedName("cvTitle")
    @Expose
    private String cvTitle;
    @SerializedName("desiredLevel")
    @Expose
    private DesiredLevel desiredLevel;
    @SerializedName("desiredSalary")
    @Expose
    private Integer desiredSalary;
    @SerializedName("educationLevel")
    @Expose
    private EducationLevel educationLevel;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("experienceYear")
    @Expose
    private ExperienceYear experienceYear;
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
    @SerializedName("lstCareer")
    @Expose
    private List<LstCareer> lstCareer = null;
    @SerializedName("lstComputerSkill")
    @Expose
    private List<LstComputerSkill> lstComputerSkill = null;
    @SerializedName("lstEducationHis")
    @Expose
    private List<LstEducationHi> lstEducationHis = null;
    @SerializedName("lstEmploymentHis")
    @Expose
    private List<LstEmploymentHi> lstEmploymentHis = null;
    @SerializedName("lstJobCity")
    @Expose
    private List<LstJobCity> lstJobCity = null;
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
    @SerializedName("workingForm")
    @Expose
    private WorkingForm workingForm;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;

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
            currencyName = null;
        } else {
            currencyName = in.readInt();
        }
        cvTitle = in.readString();
        if (in.readByte() == 0) {
            desiredSalary = null;
        } else {
            desiredSalary = in.readInt();
        }
        email = in.readString();
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

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public Integer getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(Integer currencyName) {
        this.currencyName = currencyName;
    }

    public CurrentLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(CurrentLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public CvSkill getCvSkill() {
        return cvSkill;
    }

    public void setCvSkill(CvSkill cvSkill) {
        this.cvSkill = cvSkill;
    }

    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
    }

    public DesiredLevel getDesiredLevel() {
        return desiredLevel;
    }

    public void setDesiredLevel(DesiredLevel desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    public Integer getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(Integer desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExperienceYear getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(ExperienceYear experienceYear) {
        this.experienceYear = experienceYear;
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

    public List<LstCareer> getLstCareer() {
        return lstCareer;
    }

    public void setLstCareer(List<LstCareer> lstCareer) {
        this.lstCareer = lstCareer;
    }

    public List<LstComputerSkill> getLstComputerSkill() {
        return lstComputerSkill;
    }

    public void setLstComputerSkill(List<LstComputerSkill> lstComputerSkill) {
        this.lstComputerSkill = lstComputerSkill;
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

    public List<LstJobCity> getLstJobCity() {
        return lstJobCity;
    }

    public void setLstJobCity(List<LstJobCity> lstJobCity) {
        this.lstJobCity = lstJobCity;
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

    public WorkingForm getWorkingForm() {
        return workingForm;
    }

    public void setWorkingForm(WorkingForm workingForm) {
        this.workingForm = workingForm;
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
        if (currencyName == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currencyName);
        }
        parcel.writeString(cvTitle);
        if (desiredSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(desiredSalary);
        }
        parcel.writeString(email);
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
    }
}
