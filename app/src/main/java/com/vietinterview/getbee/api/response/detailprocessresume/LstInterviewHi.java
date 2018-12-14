
package com.vietinterview.getbee.api.response.detailprocessresume;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstInterviewHi implements Parcelable {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("emailTemplate")
    @Expose
    private String emailTemplate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interviewAddress")
    @Expose
    private String interviewAddress;
    @SerializedName("interviewDate")
    @Expose
    private String interviewDate;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("round")
    @Expose
    private String round;
    @SerializedName("status")
    @Expose
    private Integer status;

    protected LstInterviewHi(Parcel in) {
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
        interviewAddress = in.readString();
        interviewDate = in.readString();
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        note = in.readString();
        round = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
    }

    public static final Creator<LstInterviewHi> CREATOR = new Creator<LstInterviewHi>() {
        @Override
        public LstInterviewHi createFromParcel(Parcel in) {
            return new LstInterviewHi(in);
        }

        @Override
        public LstInterviewHi[] newArray(int size) {
            return new LstInterviewHi[size];
        }
    };

    public LstInterviewHi(Integer cvId, String emailTemplate, Integer id, String interviewAddress, String interviewDate, Integer jobId, String note, String round, Integer status) {
        this.cvId = cvId;
        this.emailTemplate = emailTemplate;
        this.id = id;
        this.interviewAddress = interviewAddress;
        this.interviewDate = interviewDate;
        this.jobId = jobId;
        this.note = note;
        this.round = round;
        this.status = status;
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

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
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

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
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
        parcel.writeString(interviewAddress);
        parcel.writeString(interviewDate);
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        parcel.writeString(note);
        parcel.writeString(round);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
    }
}
