
package com.vietinterview.getbee.api.response.jobcustomer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobList implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("countView")
    @Expose
    private Integer countView;
    @SerializedName("countOffer")
    @Expose
    private Integer countOffer;
    @SerializedName("countCv")
    @Expose
    private Integer countCv;
    @SerializedName("limited")
    @Expose
    private Integer limited;
    @SerializedName("submitDate")
    @Expose
    private String submitDate;

    protected JobList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        jobTitle = in.readString();
        expireDate = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            countView = null;
        } else {
            countView = in.readInt();
        }
        if (in.readByte() == 0) {
            countOffer = null;
        } else {
            countOffer = in.readInt();
        }
        if (in.readByte() == 0) {
            countCv = null;
        } else {
            countCv = in.readInt();
        }
        if (in.readByte() == 0) {
            limited = null;
        } else {
            limited = in.readInt();
        }
        submitDate = in.readString();
    }

    public static final Creator<JobList> CREATOR = new Creator<JobList>() {
        @Override
        public JobList createFromParcel(Parcel in) {
            return new JobList(in);
        }

        @Override
        public JobList[] newArray(int size) {
            return new JobList[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public Integer getCountView() {
        return countView;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public Integer getCountOffer() {
        return countOffer;
    }

    public void setCountOffer(Integer countOffer) {
        this.countOffer = countOffer;
    }

    public Integer getCountCv() {
        return countCv;
    }

    public void setCountCv(Integer countCv) {
        this.countCv = countCv;
    }

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
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
        parcel.writeString(jobTitle);
        parcel.writeString(expireDate);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        if (countView == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countView);
        }
        if (countOffer == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countOffer);
        }
        if (countCv == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countCv);
        }
        if (limited == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(limited);
        }
        parcel.writeString(submitDate);
    }
}
