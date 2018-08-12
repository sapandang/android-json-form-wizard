package com.vijay.jsonwizard.mvp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijay on 4/25/15.
 */
public class ViewState implements Parcelable {

    private boolean mIsSavedInstance;

    public static final Creator<ViewState> CREATOR = new Creator<ViewState>() {
        @Override
        public ViewState createFromParcel(Parcel in) {
            return new ViewState(in);
        }

        @Override
        public ViewState[] newArray(int size) {
            return new ViewState[size];
        }
    };

    public boolean isSavedInstance() {
        return mIsSavedInstance;
    }

    public void setSavedInstance(boolean isSavedInstance) {
        mIsSavedInstance = isSavedInstance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(mIsSavedInstance ? (byte) 1 : (byte) 0);
    }

    public ViewState() {
    }

    protected ViewState(Parcel in) {
        this.mIsSavedInstance = in.readByte() != 0;
    }

}
