
package com.ibm.mobileappbuilder.businessdirectory20150916145512.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class JsonDataDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("text1") public String text1;
    @SerializedName("text2") public String text2;
    @SerializedName("picture") public String picture;
    @SerializedName("text3") public String text3;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text1);
        dest.writeString(text2);
        dest.writeString(picture);
        dest.writeString(text3);
        dest.writeString(id);
    }

    public static final Creator<JsonDataDSItem> CREATOR = new Creator<JsonDataDSItem>() {
        @Override
        public JsonDataDSItem createFromParcel(Parcel in) {
            JsonDataDSItem item = new JsonDataDSItem();

            item.text1 = in.readString();
            item.text2 = in.readString();
            item.picture = in.readString();
            item.text3 = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public JsonDataDSItem[] newArray(int size) {
            return new JsonDataDSItem[size];
        }
    };

}


