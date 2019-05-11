package com.lilliemountain.redflute.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.redflute.R;
import com.lilliemountain.redflute.model.Booking;
import com.lilliemountain.redflute.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference bookings,tickets;
    TextView email,bookingsT,money;
    ImageView eventposter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email=findViewById(R.id.email);
        bookingsT=findViewById(R.id.bookings);
        eventposter=findViewById(R.id.eventposter);
        money=findViewById(R.id.money);
        database=FirebaseDatabase.getInstance();
        bookings=database.getReference("bookings");
        tickets=database.getReference("tickets");
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
        bookings.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int amount=0;
                for (DataSnapshot child :
                        dataSnapshot.getChildren()) {
                    Booking b=child.getValue(Booking.class);
                    amount=amount+Integer.parseInt(b.getPrice());
                }
                money.setText("Rs. "+amount+" /-");
                bookingsT.setText(dataSnapshot.getChildrenCount()+" events.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        eventposter.setBackgroundResource(randomGradient());

    }

    int randomGradient(){
        int rand = (int)(Math.random() * 5) + 1;
        switch (rand){
            case 0:
                return  R.drawable.gradient_01;
            case 1:
                return  R.drawable.gradient_02;
            case 2:
                return  R.drawable.gradient_03;
            case 3:
                return  R.drawable.gradient_04;
            case 4:
                return  R.drawable.gradient_05;
            default:
                return  R.drawable.gradient_05;
        }
}
}
