package com.desele.whosupdexter;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by DexterK on 24/01/2016.
 */
public class Evnts {

    private String location;
    private String time;
    private ArrayList<Friend> listOfPeople = new ArrayList<Friend>();

    public Evnts(String location, String time){
        this.location = location;
        this.time = time;
    }

    public ArrayList<Friend> getListOfPeople(){
        return listOfPeople;
    }

    public void addFriendToEvent(Friend f){
        listOfPeople.add(f);
    }

    public String getTime(){
        return time;
    }

    public String getLocation(){
        return location;
    }

}
