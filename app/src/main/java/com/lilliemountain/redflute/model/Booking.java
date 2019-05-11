package com.lilliemountain.redflute.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Booking implements Parcelable {
    String date;
    String name;
    String totalprice;

    public Booking(String date, String name, String totalprice, String quantity) {
        this.date = date;
        this.name = name;
        this.totalprice = totalprice;
        this.quantity = quantity;
    }

    String quantity;

    public Booking() {
    }

    protected Booking(Parcel in) {
        date = in.readString();
        name = in.readString();
        totalprice = in.readString();
        quantity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(totalprice);
        dest.writeString(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return totalprice;
    }

    public void setPrice(String totalprice) {
        this.totalprice = totalprice;
    }


}

