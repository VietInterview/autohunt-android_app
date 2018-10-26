
package com.vietinterview.getbee.api.response.detailcv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstLanguage implements Parcelable {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("languageId")
    @Expose
    private Integer languageId;
    @SerializedName("listen")
    @Expose
    private Integer listen;
    @SerializedName("read")
    @Expose
    private Integer read;
    @SerializedName("speak")
    @Expose
    private Integer speak;
    @SerializedName("write")
    @Expose
    private Integer write;

    protected LstLanguage(Parcel in) {
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
            languageId = null;
        } else {
            languageId = in.readInt();
        }
        if (in.readByte() == 0) {
            listen = null;
        } else {
            listen = in.readInt();
        }
        if (in.readByte() == 0) {
            read = null;
        } else {
            read = in.readInt();
        }
        if (in.readByte() == 0) {
            speak = null;
        } else {
            speak = in.readInt();
        }
        if (in.readByte() == 0) {
            write = null;
        } else {
            write = in.readInt();
        }
    }

    public static final Creator<LstLanguage> CREATOR = new Creator<LstLanguage>() {
        @Override
        public LstLanguage createFromParcel(Parcel in) {
            return new LstLanguage(in);
        }

        @Override
        public LstLanguage[] newArray(int size) {
            return new LstLanguage[size];
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

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getListen() {
        return listen;
    }

    public void setListen(Integer listen) {
        this.listen = listen;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Integer getSpeak() {
        return speak;
    }

    public void setSpeak(Integer speak) {
        this.speak = speak;
    }

    public Integer getWrite() {
        return write;
    }

    public void setWrite(Integer write) {
        this.write = write;
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
        if (languageId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(languageId);
        }
        if (listen == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(listen);
        }
        if (read == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(read);
        }
        if (speak == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(speak);
        }
        if (write == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(write);
        }
    }
}
