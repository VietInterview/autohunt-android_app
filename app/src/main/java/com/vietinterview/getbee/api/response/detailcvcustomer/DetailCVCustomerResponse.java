
package com.vietinterview.getbee.api.response.detailcvcustomer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailCVCustomerResponse implements Parcelable {

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
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("cvTitle")
    @Expose
    private String cvTitle;
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
    private String createdBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("updatedBy")
    @Expose
    private Integer updatedBy;
    @SerializedName("careerName")
    @Expose
    private String careerName;
    @SerializedName("parentId")
    @Expose
    private Integer parentId;
    @SerializedName("currentLevel")
    @Expose
    private CurrentLevel currentLevel;
    @SerializedName("desiredLevel")
    @Expose
    private DesiredLevel desiredLevel;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("educationLevel")
    @Expose
    private EducationLevel educationLevel;
    @SerializedName("experienceYear")
    @Expose
    private ExperienceYear experienceYear;
    @SerializedName("workingForm")
    @Expose
    private WorkingForm workingForm;
    @SerializedName("lstJobCity")
    @Expose
    private List<LstJobCity> lstJobCity = null;
    @SerializedName("lstCareer")
    @Expose
    private List<LstCareer> lstCareer = null;
    @SerializedName("currencyId")
    @Expose
    private Integer currencyId;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("desiredPosition")
    @Expose
    private String desiredPosition;
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
    private List<LstEducationHi> lstEducationHis = null;
    @SerializedName("lstLanguage")
    @Expose
    private List<LstLanguage> lstLanguage = null;
    @SerializedName("lstComputerSkill")
    @Expose
    private List<LstComputerSkill> lstComputerSkill = null;
    @SerializedName("cvSkill")
    @Expose
    private CvSkill cvSkill;

    protected DetailCVCustomerResponse(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        fullName = in.readString();
        email = in.readString();
        phone = in.readString();
        if (in.readByte() == 0) {
            sex = null;
        } else {
            sex = in.readInt();
        }
        if (in.readByte() == 0) {
            maritalStatus = null;
        } else {
            maritalStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            birthday = null;
        } else {
            birthday = in.readInt();
        }
        address = in.readString();
        pictureUrl = in.readString();
        cvTitle = in.readString();
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
        createdBy = in.readString();
        updatedDate = in.readString();
        if (in.readByte() == 0) {
            updatedBy = null;
        } else {
            updatedBy = in.readInt();
        }
        careerName = in.readString();
        if (in.readByte() == 0) {
            parentId = null;
        } else {
            parentId = in.readInt();
        }
        if (in.readByte() == 0) {
            currencyId = null;
        } else {
            currencyId = in.readInt();
        }
        currencyName = in.readString();
        desiredPosition = in.readString();
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

    public static final Creator<DetailCVCustomerResponse> CREATOR = new Creator<DetailCVCustomerResponse>() {
        @Override
        public DetailCVCustomerResponse createFromParcel(Parcel in) {
            return new DetailCVCustomerResponse(in);
        }

        @Override
        public DetailCVCustomerResponse[] newArray(int size) {
            return new DetailCVCustomerResponse[size];
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public CurrentLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(CurrentLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public DesiredLevel getDesiredLevel() {
        return desiredLevel;
    }

    public void setDesiredLevel(DesiredLevel desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public ExperienceYear getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(ExperienceYear experienceYear) {
        this.experienceYear = experienceYear;
    }

    public WorkingForm getWorkingForm() {
        return workingForm;
    }

    public void setWorkingForm(WorkingForm workingForm) {
        this.workingForm = workingForm;
    }

    public List<LstJobCity> getLstJobCity() {
        return lstJobCity;
    }

    public void setLstJobCity(List<LstJobCity> lstJobCity) {
        this.lstJobCity = lstJobCity;
    }

    public List<LstCareer> getLstCareer() {
        return lstCareer;
    }

    public void setLstCareer(List<LstCareer> lstCareer) {
        this.lstCareer = lstCareer;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
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

    public List<LstEducationHi> getLstEducationHis() {
        return lstEducationHis;
    }

    public void setLstEducationHis(List<LstEducationHi> lstEducationHis) {
        this.lstEducationHis = lstEducationHis;
    }

    public List<LstLanguage> getLstLanguage() {
        return lstLanguage;
    }

    public void setLstLanguage(List<LstLanguage> lstLanguage) {
        this.lstLanguage = lstLanguage;
    }

    public List<LstComputerSkill> getLstComputerSkill() {
        return lstComputerSkill;
    }

    public void setLstComputerSkill(List<LstComputerSkill> lstComputerSkill) {
        this.lstComputerSkill = lstComputerSkill;
    }

    public CvSkill getCvSkill() {
        return cvSkill;
    }

    public void setCvSkill(CvSkill cvSkill) {
        this.cvSkill = cvSkill;
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
        if (sex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(sex);
        }
        if (maritalStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maritalStatus);
        }
        if (birthday == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(birthday);
        }
        parcel.writeString(address);
        parcel.writeString(pictureUrl);
        parcel.writeString(cvTitle);
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
        parcel.writeString(createdBy);
        parcel.writeString(updatedDate);
        if (updatedBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(updatedBy);
        }
        parcel.writeString(careerName);
        if (parentId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(parentId);
        }
        if (currencyId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currencyId);
        }
        parcel.writeString(currencyName);
        parcel.writeString(desiredPosition);
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
