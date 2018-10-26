
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmploymentHi implements Parcelable {

    @SerializedName("achievement")
    @Expose
    private String achievement;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isCurrent")
    @Expose
    private Integer isCurrent;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("salaryCurency")
    @Expose
    private Integer salaryCurency;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;

    protected LstEmploymentHi(Parcel in) {
        achievement = in.readString();
        companyName = in.readString();
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        if (in.readByte() == 0) {
            fromMonth = null;
        } else {
            fromMonth = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            isCurrent = null;
        } else {
            isCurrent = in.readInt();
        }
        jobDescription = in.readString();
        if (in.readByte() == 0) {
            salary = null;
        } else {
            salary = in.readInt();
        }
        if (in.readByte() == 0) {
            salaryCurency = null;
        } else {
            salaryCurency = in.readInt();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            toMonth = null;
        } else {
            toMonth = in.readInt();
        }
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

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getSalaryCurency() {
        return salaryCurency;
    }

    public void setSalaryCurency(Integer salaryCurency) {
        this.salaryCurency = salaryCurency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(achievement);
        parcel.writeString(companyName);
        if (cvId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cvId);
        }
        if (fromMonth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fromMonth);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (isCurrent == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isCurrent);
        }
        parcel.writeString(jobDescription);
        if (salary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(salary);
        }
        if (salaryCurency == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(salaryCurency);
        }
        parcel.writeString(title);
        if (toMonth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toMonth);
        }
    }
}
