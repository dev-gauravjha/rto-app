package com.rankexam.rtoinfo.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;


public class VehiclseDetailRespondModel implements Serializable, Parcelable {
    public static final Creator<VehiclseDetailRespondModel> CREATOR = new Creator<VehiclseDetailRespondModel>() {

        @Override
        public VehiclseDetailRespondModel createFromParcel(Parcel parcel) {
            return new VehiclseDetailRespondModel(parcel);
        }


        @Override
        public VehiclseDetailRespondModel[] newArray(int i) {
            return new VehiclseDetailRespondModel[i];
        }
    };
    private VehiclesDetailModel details;
    private boolean extra;
    private int statusCode;
    private String statusMessage;

    @Override
    public int describeContents() {
        return 0;
    }

    public VehiclseDetailRespondModel(int i, String str, VehiclesDetailModel m_rtoVehiclesDetail) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = m_rtoVehiclesDetail;
    }

    public VehiclseDetailRespondModel(int i, String str, VehiclesDetailModel m_rtoVehiclesDetail, boolean z) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = m_rtoVehiclesDetail;
        this.extra = z;
    }

    protected VehiclseDetailRespondModel(Parcel parcel) {
        this.details = (VehiclesDetailModel) parcel.readParcelable(VehiclesDetailModel.class.getClassLoader());
        this.extra = parcel.readByte() != 0;
        this.statusCode = parcel.readInt();
        this.statusMessage = parcel.readString();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public VehiclesDetailModel getDetails() {
        return this.details;
    }

    public boolean isExtra() {
        return this.extra;
    }

    public String toString() {
        return "statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', details=" + this.details + ", extra=" + this.extra ;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.details, i);
        parcel.writeByte(this.extra ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.statusMessage);
    }
}
