
package com.vietinterview.getbee.api.response.detailjobcustomer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailJobCustomerResponse implements Parcelable {

    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("careerId")
    @Expose
    private Integer careerId;
    @SerializedName("countColl")
    @Expose
    private Integer countColl;
    @SerializedName("countCv")
    @Expose
    private Integer countCv;
    @SerializedName("countGotoWork")
    @Expose
    private Integer countGotoWork;
    @SerializedName("countInterview")
    @Expose
    private Integer countInterview;
    @SerializedName("countOffer")
    @Expose
    private Integer countOffer;
    @SerializedName("countView")
    @Expose
    private Integer countView;
    @SerializedName("createBy")
    @Expose
    private Integer createBy;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("currency")
    @Expose
    private Integer currency;
    @SerializedName("currentLevel")
    @Expose
    private CurrentLevel currentLevel;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("customers")
    @Expose
    private Customers customers;
    @SerializedName("educationLevel")
    @Expose
    private EducationLevel educationLevel;
    @SerializedName("educationLevelId")
    @Expose
    private Integer educationLevelId;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("fromSalary")
    @Expose
    private Integer fromSalary;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isHotjob")
    @Expose
    private Integer isHotjob;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("jobLevel")
    @Expose
    private Integer jobLevel;
    @SerializedName("jobRequirements")
    @Expose
    private String jobRequirements;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("languageId")
    @Expose
    private Integer languageId;
    @SerializedName("lstCareer")
    @Expose
    private List<LstCareer> lstCareer = null;
    @SerializedName("lstCareerId")
    @Expose
    private String lstCareerId;
    @SerializedName("lstCountryId")
    @Expose
    private String lstCountryId;
    @SerializedName("lstJobCity")
    @Expose
    private List<LstJobCity> lstJobCity = null;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("specialTreatment")
    @Expose
    private String specialTreatment;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("submitDate")
    @Expose
    private String submitDate;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("toSalary")
    @Expose
    private Integer toSalary;
    @SerializedName("updateBy")
    @Expose
    private Integer updateBy;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("warranty")
    @Expose
    private Integer warranty;
    @SerializedName("workExperience")
    @Expose
    private String workExperience;
    @SerializedName("workingForm")
    @Expose
    private WorkingForm workingForm;
    @SerializedName("workingFormId")
    @Expose
    private Integer workingFormId;

    protected DetailJobCustomerResponse(Parcel in) {
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        if (in.readByte() == 0) {
            careerId = null;
        } else {
            careerId = in.readInt();
        }
        if (in.readByte() == 0) {
            countColl = null;
        } else {
            countColl = in.readInt();
        }
        if (in.readByte() == 0) {
            countCv = null;
        } else {
            countCv = in.readInt();
        }
        if (in.readByte() == 0) {
            countGotoWork = null;
        } else {
            countGotoWork = in.readInt();
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
            countView = null;
        } else {
            countView = in.readInt();
        }
        if (in.readByte() == 0) {
            createBy = null;
        } else {
            createBy = in.readInt();
        }
        createDate = in.readString();
        if (in.readByte() == 0) {
            currency = null;
        } else {
            currency = in.readInt();
        }
        if (in.readByte() == 0) {
            customerId = null;
        } else {
            customerId = in.readInt();
        }
        if (in.readByte() == 0) {
            educationLevelId = null;
        } else {
            educationLevelId = in.readInt();
        }
        expireDate = in.readString();
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
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            isHotjob = null;
        } else {
            isHotjob = in.readInt();
        }
        jobDescription = in.readString();
        if (in.readByte() == 0) {
            jobLevel = null;
        } else {
            jobLevel = in.readInt();
        }
        jobRequirements = in.readString();
        jobTitle = in.readString();
        if (in.readByte() == 0) {
            languageId = null;
        } else {
            languageId = in.readInt();
        }
        lstCareerId = in.readString();
        lstCountryId = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        if (in.readByte() == 0) {
            sex = null;
        } else {
            sex = in.readInt();
        }
        specialTreatment = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        submitDate = in.readString();
        tag = in.readString();
        if (in.readByte() == 0) {
            toSalary = null;
        } else {
            toSalary = in.readInt();
        }
        if (in.readByte() == 0) {
            updateBy = null;
        } else {
            updateBy = in.readInt();
        }
        updateDate = in.readString();
        if (in.readByte() == 0) {
            warranty = null;
        } else {
            warranty = in.readInt();
        }
        workExperience = in.readString();
        if (in.readByte() == 0) {
            workingFormId = null;
        } else {
            workingFormId = in.readInt();
        }
    }

    public static final Creator<DetailJobCustomerResponse> CREATOR = new Creator<DetailJobCustomerResponse>() {
        @Override
        public DetailJobCustomerResponse createFromParcel(Parcel in) {
            return new DetailJobCustomerResponse(in);
        }

        @Override
        public DetailJobCustomerResponse[] newArray(int size) {
            return new DetailJobCustomerResponse[size];
        }
    };

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getCountColl() {
        return countColl;
    }

    public void setCountColl(Integer countColl) {
        this.countColl = countColl;
    }

    public Integer getCountCv() {
        return countCv;
    }

    public void setCountCv(Integer countCv) {
        this.countCv = countCv;
    }

    public Integer getCountGotoWork() {
        return countGotoWork;
    }

    public void setCountGotoWork(Integer countGotoWork) {
        this.countGotoWork = countGotoWork;
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

    public Integer getCountView() {
        return countView;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public CurrentLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(CurrentLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsHotjob() {
        return isHotjob;
    }

    public void setIsHotjob(Integer isHotjob) {
        this.isHotjob = isHotjob;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public List<LstCareer> getLstCareer() {
        return lstCareer;
    }

    public void setLstCareer(List<LstCareer> lstCareer) {
        this.lstCareer = lstCareer;
    }

    public String getLstCareerId() {
        return lstCareerId;
    }

    public void setLstCareerId(String lstCareerId) {
        this.lstCareerId = lstCareerId;
    }

    public String getLstCountryId() {
        return lstCountryId;
    }

    public void setLstCountryId(String lstCountryId) {
        this.lstCountryId = lstCountryId;
    }

    public List<LstJobCity> getLstJobCity() {
        return lstJobCity;
    }

    public void setLstJobCity(List<LstJobCity> lstJobCity) {
        this.lstJobCity = lstJobCity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSpecialTreatment() {
        return specialTreatment;
    }

    public void setSpecialTreatment(String specialTreatment) {
        this.specialTreatment = specialTreatment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getToSalary() {
        return toSalary;
    }

    public void setToSalary(Integer toSalary) {
        this.toSalary = toSalary;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public WorkingForm getWorkingForm() {
        return workingForm;
    }

    public void setWorkingForm(WorkingForm workingForm) {
        this.workingForm = workingForm;
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
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
        if (careerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(careerId);
        }
        if (countColl == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countColl);
        }
        if (countCv == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countCv);
        }
        if (countGotoWork == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countGotoWork);
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
        if (countView == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countView);
        }
        if (createBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(createBy);
        }
        parcel.writeString(createDate);
        if (currency == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currency);
        }
        if (customerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(customerId);
        }
        if (educationLevelId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(educationLevelId);
        }
        parcel.writeString(expireDate);
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
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (isHotjob == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isHotjob);
        }
        parcel.writeString(jobDescription);
        if (jobLevel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobLevel);
        }
        parcel.writeString(jobRequirements);
        parcel.writeString(jobTitle);
        if (languageId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(languageId);
        }
        parcel.writeString(lstCareerId);
        parcel.writeString(lstCountryId);
        if (quantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(quantity);
        }
        if (sex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(sex);
        }
        parcel.writeString(specialTreatment);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        parcel.writeString(submitDate);
        parcel.writeString(tag);
        if (toSalary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toSalary);
        }
        if (updateBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(updateBy);
        }
        parcel.writeString(updateDate);
        if (warranty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(warranty);
        }
        parcel.writeString(workExperience);
        if (workingFormId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(workingFormId);
        }
    }
}
