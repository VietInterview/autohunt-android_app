
package com.vietinterview.getbee.api.response.detailprocessresume;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstOfferHi implements Parcelable {

    @SerializedName("curency")
    @Expose
    private Integer curency;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("emailTemplate")
    @Expose
    private String emailTemplate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("round")
    @Expose
    private String round;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("workAddress")
    @Expose
    private String workAddress;
    @SerializedName("workTime")
    @Expose
    private String workTime;

    protected LstOfferHi(Parcel in) {
        if (in.readByte() == 0) {
            curency = null;
        } else {
            curency = in.readInt();
        }
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        emailTemplate = in.readString();
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
        position = in.readString();
        round = in.readString();
        if (in.readByte() == 0) {
            salary = null;
        } else {
            salary = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        workAddress = in.readString();
        workTime = in.readString();
    }

    public LstOfferHi(Integer curency, Integer cvId, String emailTemplate, Integer id, Integer jobId, String note, String position, String round, Integer salary, Integer status, String workAddress, String workTime) {
        this.curency = curency;
        this.cvId = cvId;
        this.emailTemplate = emailTemplate;
        this.id = id;
        this.jobId = jobId;
        this.note = note;
        this.position = position;
        this.round = round;
        this.salary = salary;
        this.status = status;
        this.workAddress = workAddress;
        this.workTime = workTime;
    }

    public static final Creator<LstOfferHi> CREATOR = new Creator<LstOfferHi>() {
        @Override
        public LstOfferHi createFromParcel(Parcel in) {
            return new LstOfferHi(in);
        }

        @Override
        public LstOfferHi[] newArray(int size) {
            return new LstOfferHi[size];
        }
    };

    public Integer getCurency() {
        return curency;
    }

    public void setCurency(Integer curency) {
        this.curency = curency;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (curency == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(curency);
        }
        if (cvId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cvId);
        }
        parcel.writeString(emailTemplate);
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
        parcel.writeString(position);
        parcel.writeString(round);
        if (salary == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(salary);
        }
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        parcel.writeString(workAddress);
        parcel.writeString(workTime);
    }
}
