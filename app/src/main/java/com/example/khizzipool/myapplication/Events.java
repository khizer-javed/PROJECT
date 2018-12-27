package com.example.khizzipool.myapplication;

import android.util.EventLog;

import java.util.List;

public class Events {
    String eventdate;
    String clientName;
    String Adpay;
    String budget;
    String Event;
    List<String> photographers;
    List<String> videographers;
    String Eventid;

    public Events()
    {

    }

    public Events(String eventdate, String clientName, String adpay, String budget, String event, List<String> photographers, List<String> videographers,String Eventid) {
        this.eventdate = eventdate;
        this.clientName = clientName;
        this.Adpay = adpay;
        this.budget = budget;
        this.Event = event;
        this.photographers = photographers;
        this.videographers = videographers;
        this.Eventid = Eventid;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAdpay() {
        return Adpay;
    }

    public void setAdpay(String adpay) {
        Adpay = adpay;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public List<String> getPhotographers() {
        return photographers;
    }

    public void setPhotographers(List<String> photographers) {
        this.photographers = photographers;
    }

    public List<String> getVideographers() {
        return videographers;
    }

    public void setVideographers(List<String> videographers) {
        this.videographers = videographers;
    }

    public String getEventid() {
        return Eventid;
    }

    public void setEventid(String eventid) {
        Eventid = eventid;
    }
}
