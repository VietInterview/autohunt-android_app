package com.vietinterview.getbee.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.vietinterview.getbee.callback.OnChangeStepExpListener;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.callback.OnRefreshHomeListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSavedListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSubmitListener;
import com.vietinterview.getbee.callback.OnSetTextGreetingListener;
import com.vietinterview.getbee.callback.OnShowLogoListener;

/**
 * Created by V4-OS01 on 14/10/2016.
 */

public class Event implements Parcelable {
    private OnFillBackgroundListener mOnEventListener;
    private OnShowLogoListener onShowLogoListener;
    private OnRefreshHomeListener onRefreshHomeListener;
    private OnChangeStepExpListener onChangeStepExpListener;
    private OnRefreshMyCVSavedListener onRefreshMyCVListener;
    private OnRefreshMyCVSubmitListener onRefreshMyCVSubmitListener;
    private OnSetTextGreetingListener onSetTextGreetingListener;

    protected Event(Parcel in) {
    }

    public Event() {

    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public void setOnSetTextGreetingListener(OnSetTextGreetingListener onSetTextGreetingListener) {
        this.onSetTextGreetingListener = onSetTextGreetingListener;
    }

    public void setOnRefreshMyCVSubmitListener(OnRefreshMyCVSubmitListener onRefreshMyCVSubmitListener) {
        this.onRefreshMyCVSubmitListener = onRefreshMyCVSubmitListener;
    }

    public void setOnRefreshMyCVListener(OnRefreshMyCVSavedListener onRefreshMyCVListener) {
        this.onRefreshMyCVListener = onRefreshMyCVListener;
    }

    public void setOnFillBackgroundListener(OnFillBackgroundListener listener) {
        mOnEventListener = listener;
    }

    public void setOnShowLogoListener(OnShowLogoListener onShowLogoListener) {
        this.onShowLogoListener = onShowLogoListener;
    }

    public void setOnRefreshHomeListener(OnRefreshHomeListener onRefreshHomeListener) {
        this.onRefreshHomeListener = onRefreshHomeListener;
    }

    public void setOnChangeStepExpListener(OnChangeStepExpListener onChangeStepExpListener) {
        this.onChangeStepExpListener = onChangeStepExpListener;
    }

    public void doFillBackground(String nameTable) {
        if (mOnEventListener != null) {
            mOnEventListener.onFillBackground(nameTable);
        }
    }

    public void showLogo(boolean isShowLogo) {
        if (this.onShowLogoListener != null) {
            this.onShowLogoListener.onShowLogo(isShowLogo);
        }
    }

    public void refreshHome() {
        if (this.onRefreshHomeListener != null) {
            this.onRefreshHomeListener.onRefresh();
        }
    }

    public void changeStepExp(int step) {
        if (this.onChangeStepExpListener != null) {
            this.onChangeStepExpListener.onChangeStepExp(step);
        }
    }

    public void refreshMyCV() {
        if (this.onRefreshMyCVListener != null) {
            this.onRefreshMyCVListener.onRefreshMyCV();
        }
    }

    public void refreshMyCVSubmit() {
        if (this.onRefreshMyCVSubmitListener != null) {
            this.onRefreshMyCVSubmitListener.onRefreshMyCVSubmit();
        }
    }

    public void setTextGreeting(String name) {
        if (this.onSetTextGreetingListener != null) {
            this.onSetTextGreetingListener.onSetTextGreeting(name);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
