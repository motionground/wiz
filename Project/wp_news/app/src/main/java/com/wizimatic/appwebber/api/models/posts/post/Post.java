package com.wizimatic.appwebber.api.models.posts.post;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.wizimatic.appwebber.api.models.posts.commons.Embedded;
import com.wizimatic.appwebber.api.models.posts.commons.Title;
import com.wizimatic.appwebber.utility.AppUtils;

/**
 * Created by SAIF-MCC on 8/24/2017.
 */

public class Post implements Parcelable {

    @SerializedName("id")
    private final Double mID;
    @SerializedName("title")
    private Title mTitle = new Title();
    @SerializedName("_embedded")
    private Embedded mEmbedded = new Embedded();
    @SerializedName("date")
    private final String mOldDate;

    private final String formattedDate;

    public Double getID() {
        return mID;
    }

    public Title getTitle() {
        return mTitle;
    }

    public Embedded getEmbedded() {
        return mEmbedded;
    }

    public String getOldDate() {
        return mOldDate;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mID);
        dest.writeParcelable(mTitle, flags);
        dest.writeParcelable(mEmbedded, flags);
        dest.writeString(mOldDate);
        dest.writeString(formattedDate);
    }

    protected Post(Parcel in) {
        mID = in.readDouble();
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mEmbedded = in.readParcelable(Embedded.class.getClassLoader());
        mOldDate = in.readString();
        formattedDate = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getFormattedDate() {
        return AppUtils.getFormattedDate(mOldDate);
    }
}
