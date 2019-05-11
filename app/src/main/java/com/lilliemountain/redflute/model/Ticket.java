package com.lilliemountain.redflute.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {
    String date,name,address,price;

    public Ticket() {
    }

    protected Ticket(Parcel in) {
        date = in.readString();
        name = in.readString();
        address = in.readString();
        price = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Ticket(String date, String name, String address, String price) {
        this.date = date;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(price);
    }
}

