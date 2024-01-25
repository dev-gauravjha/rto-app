package com.rankexam.rtoinfo.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.rankexam.rtoinfo.Extra.RtoUtil;
import java.io.Serializable;


public class VehiclesDetailModel implements Serializable, Parcelable {
    public static final Creator<VehiclesDetailModel> CREATOR = new Creator<VehiclesDetailModel>() {

        @Override
        public VehiclesDetailModel createFromParcel(Parcel parcel) {
            return new VehiclesDetailModel(parcel);
        }


        @Override
        public VehiclesDetailModel[] newArray(int i) {
            return new VehiclesDetailModel[i];
        }
    };
    private String bodyTypeDesc;
    private String chassisNo;
    private String engineNo;
    private String financierName;
    private String fitnessUpto;
    private String fuelNorms;
    private String fuelType;
    private String insuranceCompany;
    private String insuranceUpto;
    private String makerModel;
    private String manufactureMonthYear;
    private String ownerName;
    private String ownership;
    private String ownershipDesc;
    private String pucUpto;
    private String rcStatus;
    private String registrationAuthority;
    private String registrationDate;
    private String registrationNo;
    private String roadTaxPaidUpto;
    private int searchCount;
    private String seatCapacity;
    private String unloadWeight;
    private String vehicleClass;
    private String vehicleColor;
    private VehicleInformationModel vehicleInfo;
    private String vehicleType;

    @Override
    public int describeContents() {
        return 0;
    }

    public VehiclesDetailModel() {
    }

    public VehiclesDetailModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24) {
        this.registrationAuthority = str;
        this.registrationNo = str2;
        this.registrationDate = str3;
        this.chassisNo = str4;
        this.engineNo = str5;
        this.ownerName = str6;
        this.vehicleClass = str7;
        this.fuelType = str8;
        this.makerModel = str9;
        this.fitnessUpto = str10;
        this.insuranceUpto = str11;
        this.fuelNorms = str12;
        this.roadTaxPaidUpto = str13;
        this.pucUpto = str14;
        this.vehicleColor = str15;
        this.seatCapacity = str16;
        this.ownership = str17;
        this.ownershipDesc = str18;
        this.financierName = str19;
        this.insuranceCompany = str20;
        this.unloadWeight = str21;
        this.bodyTypeDesc = str22;
        this.manufactureMonthYear = str23;
        this.rcStatus = str24;
    }

    protected VehiclesDetailModel(Parcel parcel) {
        this.bodyTypeDesc = parcel.readString();
        this.chassisNo = parcel.readString();
        this.engineNo = parcel.readString();
        this.financierName = parcel.readString();
        this.fitnessUpto = parcel.readString();
        this.fuelNorms = parcel.readString();
        this.fuelType = parcel.readString();
        this.insuranceCompany = parcel.readString();
        this.insuranceUpto = parcel.readString();
        this.makerModel = parcel.readString();
        this.manufactureMonthYear = parcel.readString();
        this.ownerName = parcel.readString();
        this.ownership = parcel.readString();
        this.ownershipDesc = parcel.readString();
        this.pucUpto = parcel.readString();
        this.rcStatus = parcel.readString();
        this.registrationAuthority = parcel.readString();
        this.registrationDate = parcel.readString();
        this.registrationNo = parcel.readString();
        this.roadTaxPaidUpto = parcel.readString();
        this.searchCount = parcel.readInt();
        this.seatCapacity = parcel.readString();
        this.unloadWeight = parcel.readString();
        this.vehicleClass = parcel.readString();
        this.vehicleColor = parcel.readString();
        this.vehicleType = parcel.readString();
    }

    public boolean isEmptyResponse() {
        return RtoUtil.isNullOrEmpty(this.ownerName) || RtoUtil.isNullOrEmpty(this.registrationNo);
    }

    public String getRegistrationAuthority() {
        return this.registrationAuthority;
    }

    public void setRegistrationAuthority(String str) {
        this.registrationAuthority = str;
    }

    public String getRegistrationNo() {
        return this.registrationNo;
    }

    public void setRegistrationNo(String str) {
        this.registrationNo = str;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(String str) {
        this.registrationDate = str;
    }

    public String getChassisNo() {
        return this.chassisNo;
    }

    public void setChassisNo(String str) {
        this.chassisNo = str;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String str) {
        this.engineNo = str;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String str) {
        this.ownerName = str;
    }

    public String getVehicleClass() {
        return this.vehicleClass;
    }

    public void setVehicleClass(String str) {
        this.vehicleClass = str;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String str) {
        this.fuelType = str;
    }

    public String getMakerModel() {
        return this.makerModel;
    }

    public void setMakerModel(String str) {
        this.makerModel = str;
    }

    public String getFitnessUpto() {
        return this.fitnessUpto;
    }

    public void setFitnessUpto(String str) {
        this.fitnessUpto = str;
    }

    public String getInsuranceUpto() {
        return this.insuranceUpto;
    }

    public void setInsuranceUpto(String str) {
        this.insuranceUpto = str;
    }

    public String getFuelNorms() {
        return this.fuelNorms;
    }

    public void setFuelNorms(String str) {
        this.fuelNorms = str;
    }

    public String getVehicleColor() {
        return this.vehicleColor;
    }

    public void setVehicleColor(String str) {
        this.vehicleColor = str;
    }

    public String getSeatCapacity() {
        return this.seatCapacity;
    }

    public void setSeatCapacity(String str) {
        this.seatCapacity = str;
    }

    public String getOwnership() {
        return this.ownership;
    }

    public void setOwnership(String str) {
        this.ownership = str;
    }

    public String getOwnershipDesc() {
        return this.ownershipDesc;
    }

    public String getFinancierName() {
        return this.financierName;
    }

    public String getInsuranceCompany() {
        return this.insuranceCompany;
    }

    public int getSearchCount() {
        return this.searchCount;
    }

    public VehicleInformationModel getVehicleInfo() {
        return this.vehicleInfo;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public String getRoadTaxPaidUpto() {
        return this.roadTaxPaidUpto;
    }

    public void setRoadTaxPaidUpto(String str) {
        this.roadTaxPaidUpto = str;
    }

    public String getPucUpto() {
        return this.pucUpto;
    }

    public void setPucUpto(String str) {
        this.pucUpto = str;
    }

    public String getUnloadWeight() {
        return this.unloadWeight;
    }

    public String getBodyTypeDesc() {
        return this.bodyTypeDesc;
    }

    public String getManufactureMonthYear() {
        return this.manufactureMonthYear;
    }

    public String getRcStatus() {
        return this.rcStatus;
    }

    public String toString() {
        return "R_VehicleDetails{registrationAuthority='" + this.registrationAuthority + "', registrationNo='" + this.registrationNo + "', registrationDate='" + this.registrationDate + "', chassisNo='" + this.chassisNo + "', engineNo='" + this.engineNo + "', ownerName='" + this.ownerName + "', vehicleClass='" + this.vehicleClass + "', fuelType='" + this.fuelType + "', makerModel='" + this.makerModel + "', fitnessUpto='" + this.fitnessUpto + "', insuranceUpto='" + this.insuranceUpto + "', fuelNorms='" + this.fuelNorms + "', roadTaxPaidUpto='" + this.roadTaxPaidUpto + "', pucUpto='" + this.pucUpto + "', vehicleColor='" + this.vehicleColor + "', seatCapacity='" + this.seatCapacity + "', ownership='" + this.ownership + "', ownershipDesc='" + this.ownershipDesc + "', financierName='" + this.financierName + "', insuranceCompany='" + this.insuranceCompany + "', searchCount=" + this.searchCount + ", vehicleInfo=" + this.vehicleInfo + ", vehicleType='" + this.vehicleType + "', unloadWeight='" + this.unloadWeight + "', bodyTypeDesc='" + this.bodyTypeDesc + "', manufactureMonthYear='" + this.manufactureMonthYear + "', rcStatus='" + this.rcStatus + "'}";
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bodyTypeDesc);
        parcel.writeString(this.chassisNo);
        parcel.writeString(this.engineNo);
        parcel.writeString(this.financierName);
        parcel.writeString(this.fitnessUpto);
        parcel.writeString(this.fuelNorms);
        parcel.writeString(this.fuelType);
        parcel.writeString(this.insuranceCompany);
        parcel.writeString(this.insuranceUpto);
        parcel.writeString(this.makerModel);
        parcel.writeString(this.manufactureMonthYear);
        parcel.writeString(this.ownerName);
        parcel.writeString(this.ownership);
        parcel.writeString(this.ownershipDesc);
        parcel.writeString(this.pucUpto);
        parcel.writeString(this.rcStatus);
        parcel.writeString(this.registrationAuthority);
        parcel.writeString(this.registrationDate);
        parcel.writeString(this.registrationNo);
        parcel.writeString(this.roadTaxPaidUpto);
        parcel.writeInt(this.searchCount);
        parcel.writeString(this.seatCapacity);
        parcel.writeString(this.unloadWeight);
        parcel.writeString(this.vehicleClass);
        parcel.writeString(this.vehicleColor);
        parcel.writeString(this.vehicleType);
    }
}
