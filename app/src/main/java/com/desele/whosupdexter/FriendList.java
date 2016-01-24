package com.desele.whosupdexter;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by DexterK on 23/01/2016.
 */
public class FriendList {

    public static ArrayList<Friend> friendList = new ArrayList<Friend>();

    public FriendList() {}

    public FriendList(ArrayList<Friend> list) {
        friendList = list;
    }

    public void addFriend(Friend f) {
        friendList.add(f);
    }

    public int getNumOfFriends() {
        if(friendList.isEmpty())
            return 0;
        return friendList.size();
    }

    public ArrayList<Friend> getList(){
        return friendList;
    }
}
