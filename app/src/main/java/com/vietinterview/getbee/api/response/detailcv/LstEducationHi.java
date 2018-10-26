
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEducationHi implements Parcelable {

    @SerializedName("career")
    @Expose
    private String career;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("graduationType")
    @Expose
    private Integer graduationType;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;

    protected LstEducationHi(Parcel in) {
        career = in.readString();
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
            graduationType = null;
        } else {
            graduationType = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        note = in.readString();
        school = in.readString();
        subject = in.readString();
        if (in.readByte() == 0) {
            toMonth = null;
        } else {
            toMonth = in.readInt();
        }
    }

    public static final Creator<LstEducationHi> CREATOR = new Creator<LstEducationHi>() {
        @Override
        public LstEducationHi createFromParcel(Parcel in) {
            return new LstEducationHi(in);
        }

        @Override
        public LstEducationHi[] newArray(int size) {
            return new LstEducationHi[size];
        }
    };

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
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

    public Integer getGraduationType() {
        return graduationType;
    }

    public void setGraduationType(Integer graduationType) {
        this.graduationType = graduationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        parcel.writeString(career);
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
        if (graduationType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(graduationType);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(note);
        parcel.writeString(school);
        parcel.writeString(subject);
        if (toMonth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toMonth);
        }
    }
}
