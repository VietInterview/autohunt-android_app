
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstComputerSkill implements Parcelable {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("msEexcel")
    @Expose
    private Integer msEexcel;
    @SerializedName("msOutlook")
    @Expose
    private Integer msOutlook;
    @SerializedName("msPowerPoint")
    @Expose
    private Integer msPowerPoint;
    @SerializedName("msWord")
    @Expose
    private Integer msWord;
    @SerializedName("other")
    @Expose
    private String other;

    protected LstComputerSkill(Parcel in) {
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
            msEexcel = null;
        } else {
            msEexcel = in.readInt();
        }
        if (in.readByte() == 0) {
            msOutlook = null;
        } else {
            msOutlook = in.readInt();
        }
        if (in.readByte() == 0) {
            msPowerPoint = null;
        } else {
            msPowerPoint = in.readInt();
        }
        if (in.readByte() == 0) {
            msWord = null;
        } else {
            msWord = in.readInt();
        }
        other = in.readString();
    }

    public static final Creator<LstComputerSkill> CREATOR = new Creator<LstComputerSkill>() {
        @Override
        public LstComputerSkill createFromParcel(Parcel in) {
            return new LstComputerSkill(in);
        }

        @Override
        public LstComputerSkill[] newArray(int size) {
            return new LstComputerSkill[size];
        }
    };

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

    public Integer getMsEexcel() {
        return msEexcel;
    }

    public void setMsEexcel(Integer msEexcel) {
        this.msEexcel = msEexcel;
    }

    public Integer getMsOutlook() {
        return msOutlook;
    }

    public void setMsOutlook(Integer msOutlook) {
        this.msOutlook = msOutlook;
    }

    public Integer getMsPowerPoint() {
        return msPowerPoint;
    }

    public void setMsPowerPoint(Integer msPowerPoint) {
        this.msPowerPoint = msPowerPoint;
    }

    public Integer getMsWord() {
        return msWord;
    }

    public void setMsWord(Integer msWord) {
        this.msWord = msWord;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (msEexcel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(msEexcel);
        }
        if (msOutlook == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(msOutlook);
        }
        if (msPowerPoint == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(msPowerPoint);
        }
        if (msWord == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(msWord);
        }
        parcel.writeString(other);
    }
}
