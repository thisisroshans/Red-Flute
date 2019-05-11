package com.lilliemountain.redflute.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lilliemountain.redflute.R;
import com.lilliemountain.redflute.activity.EventInfoActivity;
import com.lilliemountain.redflute.model.Booking;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingsHolder> {

    List<Booking> bookings;

    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bookings,viewGroup,false);
        return new BookingsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsHolder bookingsHolder, int i) {
        bookingsHolder.date.setText(bookings.get(i).getDate().split("-")[0]);
        bookingsHolder.month.setText(bookings.get(i).getDate().split("-")[1].split("-")[0]);
        bookingsHolder.price.setText("Rs. "+bookings.get(i).getPrice());
        bookingsHolder.name.setText(bookings.get(i).getName());
        bookingsHolder.booking=bookings.get(i);
        Bitmap myBitmap = QRCode.from("Event Name: "+bookingsHolder.booking.getName()+"\n"+
                "Price: "+bookingsHolder.booking.getPrice()+"\n"+
                "Date: "+bookingsHolder.booking.getDate()).bitmap();
        bookingsHolder.eventposter.setImageBitmap(myBitmap);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class BookingsHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,month;
        ImageView eventposter;
        Booking booking;
        public BookingsHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.eventname);
            price=itemView.findViewById(R.id.price);
            date=itemView.findViewById(R.id.date);
            month=itemView.findViewById(R.id.month);
            eventposter=itemView.findViewById(R.id.eventposter);
            eventposter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowQR(v);
                }
            });
        }
        void ShowQR(View v){
            Dialog dialog=new Dialog(v.getContext());
            dialog.setContentView(R.layout.qrcode);
            ImageView view=dialog.findViewById(R.id.qrcode);
            Bitmap myBitmap = QRCode.from("Event Name: "+booking.getName()+"\n"+
                    "Price: "+booking.getPrice()+"\n"+
                    "Date: "+booking.getDate()+"\n").bitmap();
            myBitmap.setHeight(250);
            myBitmap.setWidth(250);
            view.setImageBitmap(myBitmap);
        }
    }
}
