
package com.vietinterview.getbee.api.response.detailprocessresume;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobCvGotoWorkDto implements Parcelable {

    @SerializedName("countUpdate")
    @Expose
    private Integer countUpdate;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("numDayWarranty")
    @Expose
    private Integer numDayWarranty;
    @SerializedName("startWorkDate")
    @Expose
    private String startWorkDate;
    @SerializedName("updateBy")
    @Expose
    private Integer updateBy;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("warrantyExpireDate")
    @Expose
    private String warrantyExpireDate;

    public JobCvGotoWorkDto(Integer countUpdate, Integer cvId, Integer id, Integer jobId, String note, Integer numDayWarranty, String startWorkDate, Integer updateBy, String updateDate, String warrantyExpireDate) {
        this.countUpdate = countUpdate;
        this.cvId = cvId;
        this.id = id;
        this.jobId = jobId;
        this.note = note;
        this.numDayWarranty = numDayWarranty;
        this.startWorkDate = startWorkDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.warrantyExpireDate = warrantyExpireDate;
    }

    protected JobCvGotoWorkDto(Parcel in) {
        if (in.readByte() == 0) {
            countUpdate = null;
        } else {
            countUpdate = in.readInt();
        }
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        note = in.readString();
        if (in.readByte() == 0) {
            numDayWarranty = null;
        } else {
            numDayWarranty = in.readInt();
        }
        startWorkDate = in.readString();
        if (in.readByte() == 0) {
            updateBy = null;
        } else {
            updateBy = in.readInt();
        }
        updateDate = in.readString();
        warrantyExpireDate = in.readString();
    }

    public static final Creator<JobCvGotoWorkDto> CREATOR = new Creator<JobCvGotoWorkDto>() {
        @Override
        public JobCvGotoWorkDto createFromParcel(Parcel in) {
            return new JobCvGotoWorkDto(in);
        }

        @Override
        public JobCvGotoWorkDto[] newArray(int size) {
            return new JobCvGotoWorkDto[size];
        }
    };

    public Integer getCountUpdate() {
        return countUpdate;
    }

    public void setCountUpdate(Integer countUpdate) {
        this.countUpdate = countUpdate;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumDayWarranty() {
        return numDayWarranty;
    }

    public void setNumDayWarranty(Integer numDayWarranty) {
        this.numDayWarranty = numDayWarranty;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
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

    public String getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(String warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (countUpdate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countUpdate);
        }
        if (cvId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cvId);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        parcel.writeString(note);
        if (numDayWarranty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numDayWarranty);
        }
        parcel.writeString(startWorkDate);
        if (updateBy == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(updateBy);
        }
        parcel.writeString(updateDate);
        parcel.writeString(warrantyExpireDate);
    }
}
