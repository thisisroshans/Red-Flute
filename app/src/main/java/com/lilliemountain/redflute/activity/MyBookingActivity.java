package com.lilliemountain.redflute.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.redflute.R;
import com.lilliemountain.redflute.adapter.BookingAdapter;
import com.lilliemountain.redflute.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    FirebaseDatabase database;
    DatabaseReference bookings;
    List<Booking> bookingList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        database=FirebaseDatabase.getInstance();
        bookings=database.getReference("bookings");
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        bookings.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child :
                        dataSnapshot.getChildren()) {
                    Booking b=child.getValue(Booking.class);
                    bookingList.add(b);
                }
                recyclerview.setAdapter(new BookingAdapter(bookingList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
