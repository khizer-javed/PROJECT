package com.example.khizzipool.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.RecyclerViewHolder> {
    private Context context;
    private ArrayList<Events> eventslist;

    public EventAdapter(Context context, ArrayList<Events> eventslist) {
        this.context = context;
        this.eventslist = eventslist;
    }

    @NonNull
    @Override
    public EventAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.event,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.RecyclerViewHolder recyclerViewHolder, final int position) {
        Events events = eventslist.get(position);
        String Type = events.getEvent();
        List<String> a = events.getPhotographers();
        String Photographers = TextUtils.join("\n", a).toUpperCase(); //Converts list into a single String

        List<String> b = events.getVideographers();
        String Videographers = TextUtils.join("\n", b).toUpperCase();

        String clientname = events.getClientName();
        String Date = events.getEventdate();

        recyclerViewHolder.eventType.setText(Type);
        recyclerViewHolder.Eventdate.setText(Date);
        recyclerViewHolder.Pnames.setText(Photographers);
        recyclerViewHolder.Vnames.setText(Videographers);
        recyclerViewHolder.clientname.setText(clientname);
    }

    @Override
    public int getItemCount() {
        return eventslist.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public TextView eventType;
        public TextView Pnames;
        public TextView Vnames;
        public TextView Eventdate;
        public TextView clientname;

        public RecyclerViewHolder(@NonNull View listview) {
            super(listview);
            eventType = (TextView) listview.findViewById(R.id.eventType);
            Pnames = (TextView) listview.findViewById(R.id.Pnames);
            Vnames = (TextView) listview.findViewById(R.id.Vnames);
            Eventdate = (TextView) listview.findViewById(R.id.date);
            clientname = (TextView) listview.findViewById(R.id.client);
        }
    }
}
