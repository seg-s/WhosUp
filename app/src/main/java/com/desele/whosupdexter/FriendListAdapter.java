package com.desele.whosupdexter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DexterK on 23/01/2016.
 */
public class FriendListAdapter extends ArrayAdapter<Friend> {

        public FriendListAdapter(Context context, List<Friend> items) {
            super(context, R.layout.friendlist_layout, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if(convertView == null) {
                // inflate the GridView item layout
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.friendlist_layout, parent, false);

                // initialize the view holder
                viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
                viewHolder.name = (TextView) convertView.findViewById(R.id.Name);
                viewHolder.currentActivity = (TextView) convertView.findViewById(R.id.CurrentActivity);
                //viewHolder.phoneNumber = (TextView) convertView.findViewById(R.id.PhoneNumber);
                convertView.setTag(viewHolder);
            } else {
                // recycle the already inflated view
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // update the item view
            Friend item = getItem(position);
            viewHolder.icon.setImageDrawable(item.icon);
            viewHolder.name.setText(item.getName());
            viewHolder.currentActivity.setText(item.getCurrentActivity());
            //viewHolder.phoneNumber.setText(item.getTelephone());

            return convertView;
        }

        /**
         * The view holder design pattern prevents using findViewById()
         * repeatedly in the getView() method of the adapter.
         *
         * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
         */
        private static class ViewHolder {
            ImageView icon;
            TextView name;
            //TextView phoneNumber;
            TextView currentActivity;
        }

}
