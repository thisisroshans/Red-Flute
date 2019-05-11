package com.lilliemountain.redflute.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hariofspades.incdeclibrary.IncDecCircular;
import com.lilliemountain.redflute.R;
import com.lilliemountain.redflute.model.Booking;
import com.lilliemountain.redflute.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class EventInfoActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference bookings;
    TextView eventname,eventaddress,price,eventdate,qty,totalamount;
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        final Ticket ticket=getIntent().getParcelableExtra("ticket");
        database=FirebaseDatabase.getInstance();
        bookings=database.getReference("bookings");

        totalamount=findViewById(R.id.totalamount);
        qty=findViewById(R.id.qty);
        eventname=findViewById(R.id.name);
        eventaddress=findViewById(R.id.eventaddress);
        price=findViewById(R.id.price);
        eventdate=findViewById(R.id.eventdate);
        view=findViewById(R.id.imageView2);
        eventname.setText(ticket.getName());
        eventaddress.setText(ticket.getAddress());
        price.setText(ticket.getPrice().split(" ")[1].split(" ")[0]);
        eventdate.setText(ticket.getDate());

        view.setBackgroundResource(randomGradient());

        findViewById(R.id.book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //TODO BOOK TICKET
                final Booking booking=new Booking(ticket.getDate(),ticket.getName(),String.valueOf(Integer.parseInt(ticket.getPrice().split(" ")[1].split(" ")[0])*Integer.parseInt(qty.getText().toString())),qty.getText().toString());
                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                bookings.child(uid).push().setValue(booking).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Snackbar.make(v,"Booking for "+booking.getName()+ " Confirmed",Snackbar.LENGTH_LONG);
//                        TODO STARTACTIVITY TO MY BOOKINGS
                    }
                });
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(qty.getText().toString())>5)
                {
                    Snackbar.make(v,"Only 5 tickets can be booked",Snackbar.LENGTH_LONG);
                }
                else {
                    int qtyInt=Integer.parseInt(qty.getText().toString());
                    qtyInt++;
                    qty.setText(qtyInt+"");
                    int amount=qtyInt*Integer.parseInt(price.getText().toString());
                    totalamount.setText(amount+"");
                }
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(qty.getText().toString())<1)
                {
                    Snackbar.make(v,"At least 1 tickets is to be booked",Snackbar.LENGTH_LONG);
                    qty.setText(1+"");
                    int amount=1*Integer.parseInt(price.getText().toString());
                    totalamount.setText(amount+"");
                }
                else {
                    int qtyInt=Integer.parseInt(qty.getText().toString());
                    if(qtyInt>1){
                        qtyInt--;
                        qty.setText(qtyInt+"");
                        int amount=qtyInt*Integer.parseInt(price.getText().toString());
                        totalamount.setText(amount+"");
                    }
                }
            }
        });
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
