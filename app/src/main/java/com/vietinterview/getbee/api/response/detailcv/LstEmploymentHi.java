
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmploymentHi implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;
    @SerializedName("isCurrent")
    @Expose
    private Object isCurrent;
    @SerializedName("salaryCurency")
    @Expose
    private Integer salaryCurency;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("achievement")
    @Expose
    private String achievement;
    @SerializedName("humanResources")
    @Expose
    private Integer humanResources;

    protected LstEmploymentHi(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        companyName = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            fromMonth = null;
        } else {
            fromMonth = in.readInt();
        }
        if (in.readByte() == 0) {
            toMonth = null;
        } else {
            toMonth = in.readInt();
        }
        if (in.readByte() == 0) {
            salaryCurency = null;
        } else {
            salaryCurency = in.readInt();
        }
        if (in.readByte() == 0) {
            salary = null;
        } else {
            salary = in.readInt();
        }
        jobDescription = in.readString();
        achievement = in.readString();
    }

    public static final Creator<LstEmploymentHi> CREATOR = new Creator<LstEmploymentHi>() {
        @Override
        public LstEmploymentHi createFromParcel(Parcel in) {
            return new LstEmploymentHi(in);
        }

        @Override
        public LstEmploymentHi[] newArray(int size) {
            return new LstEmploymentHi[size];
        }
    };

    public Integer getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(Integer humanResources) {
        this.humanResources = humanResources;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

    public Object getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Object isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Integer getSalaryCurency() {
        return salaryCurency;
    }

    public void setSalaryCurency(Integer salaryCurency) {
        this.salaryCurency = salaryCurency;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
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
        if (cvId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cvId);
        }
        parcel.writeString(companyName);
        parcel.writeString(title);
        if (fromMonth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fromMonth);
        }
        if (toMonth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toMonth);
        }
        if (salaryCurency == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(salaryCurency);
        }
        if (salary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(salary);
        }
        parcel.writeString(jobDescription);
        parcel.writeString(achievement);
    }
}
