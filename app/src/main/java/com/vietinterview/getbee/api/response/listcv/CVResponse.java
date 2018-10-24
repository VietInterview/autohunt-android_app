
package com.vietinterview.getbee.api.response.listcv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CVResponse implements Parcelable {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("cvList")
    @Expose
    private List<CvList> cvList = null;

    protected CVResponse(Parcel in) {
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
    }

    public static final Creator<CVResponse> CREATOR = new Creator<CVResponse>() {
        @Override
        public CVResponse createFromParcel(Parcel in) {
            return new CVResponse(in);
        }

        @Override
        public CVResponse[] newArray(int size) {
            return new CVResponse[size];
        }
    };

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CvList> getCvList() {
        return cvList;
    }

    public void setCvList(List<CvList> cvList) {
        this.cvList = cvList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total);
        }
    }
}
