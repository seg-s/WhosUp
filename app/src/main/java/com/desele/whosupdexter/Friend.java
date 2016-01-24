package com.desele.whosupdexter;

import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by DexterK on 23/01/2016.
 */
public class Friend {

    public enum Status {AVAILABLE ("Available"), BUSY ("Busy"), ANTISOCIAL ("Anti-Social");
        private final String name;

        private Status(String s){
            name = s;
        }

        public boolean equalsName(String otherName){
            return (otherName == null ? false : name.equals(otherName));
        }

        public String toString(){
            return this.name;
        }

    };


    public final Drawable icon;
    private final String name;
    private String telephone;
    private String email;
    private String currentActivity;
    private Status availability = Status.AVAILABLE;
    private static final int MAX_FAV = 5;
    private ArrayList<Evnts> eventsList = new ArrayList<Evnts>();
    private ArrayList<Friend> favorites = new ArrayList<Friend>(MAX_FAV);

    public Friend(String name, String telephone, Drawable icon){
        this.icon = icon;
        this.name = name;
        this.telephone = telephone;
        this.currentActivity = "I'm free at the moment";
    }

    public void setAvailability(Status state){
        availability = state;
    }

    public void setPhone(String s) {email = s;}

    public void setAsFavorite(Friend f){
        favorites.add(f);
    }

    public String getAvailability(){
        return availability.toString();
    }

    public String getName() { return name; }

    public String getTelephone() { return telephone;}

    public String getCurrentActivity() {return currentActivity;}

    public ArrayList<Friend> getFavorites(){
        return favorites;
    }

    public ArrayList<Evnts> getEventsList(){
        return eventsList;
    }

}
