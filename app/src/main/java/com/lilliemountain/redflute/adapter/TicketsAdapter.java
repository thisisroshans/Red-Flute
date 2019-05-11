package com.lilliemountain.redflute.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lilliemountain.redflute.R;
import com.lilliemountain.redflute.activity.EventInfoActivity;
import com.lilliemountain.redflute.model.Ticket;

import java.util.List;
import java.util.Random;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketsHolder> {

    List<Ticket> tickets;

    public TicketsAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tickets,viewGroup,false);
        return new TicketsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsHolder ticketsHolder, int i) {
        ticketsHolder.date.setText(tickets.get(i).getDate().split("-")[0]);
        ticketsHolder.month.setText(tickets.get(i).getDate().split("-")[1].split("-")[0]);
        ticketsHolder.price.setText(tickets.get(i).getPrice());
        ticketsHolder.address.setText(tickets.get(i).getAddress());
        ticketsHolder.name.setText(tickets.get(i).getName());
        ticketsHolder.ticket=tickets.get(i);
        ticketsHolder.eventposter.setBackgroundResource(randomGradient());

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
    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketsHolder extends RecyclerView.ViewHolder {
        TextView name,address,price,date,month;
        ImageView eventposter;
        Ticket ticket;
        public TicketsHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.eventname);
            address=itemView.findViewById(R.id.address);
            price=itemView.findViewById(R.id.price);
            date=itemView.findViewById(R.id.date);
            month=itemView.findViewById(R.id.month);
            eventposter=itemView.findViewById(R.id.eventposter);
            itemView.findViewById(R.id.book).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), EventInfoActivity.class).putExtra("ticket",ticket));
                }
            });
        }
    }
}
