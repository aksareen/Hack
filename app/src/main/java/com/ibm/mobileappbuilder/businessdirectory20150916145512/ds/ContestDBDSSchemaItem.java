
package com.ibm.mobileappbuilder.businessdirectory20150916145512.ds;
import java.util.Date;
import java.util.Date;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ContestDBDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;

    @SerializedName("Duration") public String duration;
    @SerializedName("EndTime") public Date endTime;
    @SerializedName("Name") public String name;
    @SerializedName("Platform") public String platform;
    @SerializedName("StartTime") public Date startTime;
    @SerializedName("url") public String url;

    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
        dest.writeString(duration);
        dest.writeValue(endTime != null ? endTime.getTime() : null);
        dest.writeString(name);
        dest.writeString(platform);
        dest.writeValue(startTime != null ? startTime.getTime() : null);
        dest.writeString(url);
    }

    public static final Creator<ContestDBDSSchemaItem> CREATOR = new Creator<ContestDBDSSchemaItem>() {
        @Override
        public ContestDBDSSchemaItem createFromParcel(Parcel in) {
            ContestDBDSSchemaItem item = new ContestDBDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            item.duration = in.readString();
            Long endTimeAux = (Long) in.readValue(null);
            item.endTime = endTimeAux != null ? new Date(endTimeAux) : null;
            item.name = in.readString();
            item.platform = in.readString();
            Long startTimeAux = (Long) in.readValue(null);
            item.startTime = startTimeAux != null ? new Date(startTimeAux) : null;
            item.url = in.readString();
            return item;
        }

        @Override
        public ContestDBDSSchemaItem[] newArray(int size) {
            return new ContestDBDSSchemaItem[size];
        }
    };
}


