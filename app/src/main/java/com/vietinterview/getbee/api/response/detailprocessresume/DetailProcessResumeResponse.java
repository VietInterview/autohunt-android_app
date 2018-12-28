
package com.vietinterview.getbee.api.response.detailprocessresume;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailProcessResumeResponse implements Parcelable {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("cvProcessInfo")
    @Expose
    private CvProcessInfo cvProcessInfo;
    @SerializedName("jobCvGotoWorkDto")
    @Expose
    private JobCvGotoWorkDto jobCvGotoWorkDto;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("lstInterviewHis")
    @Expose
    private List<LstInterviewHi> lstInterviewHis = null;
    @SerializedName("lstOfferHis")
    @Expose
    private List<LstOfferHi> lstOfferHis = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    protected DetailProcessResumeResponse(Parcel in) {
        if (in.readByte() == 0) {
            cvId = null;
        } else {
            cvId = in.readInt();
        }
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
    }

    public static final Creator<DetailProcessResumeResponse> CREATOR = new Creator<DetailProcessResumeResponse>() {
        @Override
        public DetailProcessResumeResponse createFromParcel(Parcel in) {
            return new DetailProcessResumeResponse(in);
        }

        @Override
        public DetailProcessResumeResponse[] newArray(int size) {
            return new DetailProcessResumeResponse[size];
        }
    };

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public CvProcessInfo getCvProcessInfo() {
        return cvProcessInfo;
    }

    public void setCvProcessInfo(CvProcessInfo cvProcessInfo) {
        this.cvProcessInfo = cvProcessInfo;
    }

    public JobCvGotoWorkDto getJobCvGotoWorkDto() {
        return jobCvGotoWorkDto;
    }

    public void setJobCvGotoWorkDto(JobCvGotoWorkDto jobCvGotoWorkDto) {
        this.jobCvGotoWorkDto = jobCvGotoWorkDto;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public List<LstInterviewHi> getLstInterviewHis() {
        return lstInterviewHis;
    }

    public void setLstInterviewHis(List<LstInterviewHi> lstInterviewHis) {
        this.lstInterviewHis = lstInterviewHis;
    }

    public List<LstOfferHi> getLstOfferHis() {
        return lstOfferHis;
    }

    public void setLstOfferHis(List<LstOfferHi> lstOfferHis) {
        this.lstOfferHis = lstOfferHis;
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
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
    }
}
