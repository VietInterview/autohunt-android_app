package com.vietinterview.getbee.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.vietinterview.getbee.callback.OnChangeStepExpListener;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.callback.OnRefreshHomeListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSavedListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSubmitListener;
import com.vietinterview.getbee.callback.OnSetHeightViewListener;
import com.vietinterview.getbee.callback.OnSetTextGreetingListener;
import com.vietinterview.getbee.callback.OnShowLogoListener;
import com.vietinterview.getbee.callback.OnSwitchToFiveListener;
import com.vietinterview.getbee.callback.OnSwitchToFourListener;
import com.vietinterview.getbee.callback.OnSwitchToOneListener;
import com.vietinterview.getbee.callback.OnSwitchToSixListener;
import com.vietinterview.getbee.callback.OnSwitchToThreeListener;
import com.vietinterview.getbee.callback.OnSwitchToTwoListener;

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
    private OnSetHeightViewListener onSetHeightViewListener;
    private OnSwitchToOneListener onSwitchToOneListener;
    private OnSwitchToTwoListener onSwitchToTwoListener;
    private OnSwitchToThreeListener onSwitchToThreeListener;
    private OnSwitchToFourListener onSwitchToFourListener;
    private OnSwitchToFiveListener onSwitchToFiveListener;
    private OnSwitchToSixListener onSwitchToSixListener;

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

    public void setOnSwitchToOneListener(OnSwitchToOneListener onSwitchToOneListener) {
        this.onSwitchToOneListener = onSwitchToOneListener;
    }

    public void setOnSwitchToTwoListener(OnSwitchToTwoListener onSwitchToTwoListener) {
        this.onSwitchToTwoListener = onSwitchToTwoListener;
    }

    public void setOnSwitchToThreeListener(OnSwitchToThreeListener onSwitchToThreeListener) {
        this.onSwitchToThreeListener = onSwitchToThreeListener;
    }

    public void setOnSwitchToFourListener(OnSwitchToFourListener onSwitchToFourListener) {
        this.onSwitchToFourListener = onSwitchToFourListener;
    }

    public void setOnSwitchToFiveListener(OnSwitchToFiveListener onSwitchToFiveListener) {
        this.onSwitchToFiveListener = onSwitchToFiveListener;
    }

    public void setOnSwitchToSixListener(OnSwitchToSixListener onSwitchToSixListener) {
        this.onSwitchToSixListener = onSwitchToSixListener;
    }

    public void setOnSetHeightViewListener(OnSetHeightViewListener onSetHeightViewListener) {
        this.onSetHeightViewListener = onSetHeightViewListener;
    }

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

    public void setHeightView(int height) {
        if (this.onSetHeightViewListener != null) {
            this.onSetHeightViewListener.onSetHeightView(height);
        }
    }

    public void setSwitchToOne() {
        if (this.onSwitchToOneListener != null) {
            this.onSwitchToOneListener.onSwitchToOne();
        }
    }

    public void setSwitchToTwo() {
        if (this.onSwitchToTwoListener != null) {
            this.onSwitchToTwoListener.onSwitchToTwo();
        }
    }

    public void setSwitchToThree() {
        if (this.onSwitchToThreeListener != null) {
            this.onSwitchToThreeListener.onSwitchToThree();
        }
    }

    public void setSwitchToFour() {
        if (this.onSwitchToFourListener != null) {
            this.onSwitchToFourListener.onSwitchToFour();
        }
    }

    public void setSwitchToFive() {
        if (this.onSwitchToFiveListener != null) {
            this.onSwitchToFiveListener.onSwitchToFive();
        }
    }

    public void setSwitchToSix() {
        if (this.onSwitchToSixListener != null) {
            this.onSwitchToSixListener.onSwitchToSix();
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
