package com.example.saira_000.webservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> iid = new ArrayList<String>();
    ArrayList<String> firstName = new ArrayList<String>();
    ArrayList<String> lastName = new ArrayList<String>();
    ArrayList<String> email = new ArrayList<String>();
    ArrayList<String> phone = new ArrayList<String>();
    ArrayList<String> password = new ArrayList<String>();

    public CustomAdapter(Context context, ArrayList<String> id, ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> email, ArrayList<String> phone, ArrayList<String> password) {
        this.context = context;
        this.iid = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }


    @Override
    public int getCount() {
        return firstName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder
    {
        TextView id;
        TextView firstName;
        TextView lastName;
        TextView email;
        TextView phone;
        TextView password;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        LayoutInflater layoutInflater;

        if (true)
        {
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_custom_adapter,null);

            holder.id = (TextView) convertView.findViewById(R.id.ID);
            holder.firstName = (TextView) convertView.findViewById(R.id.firstname);
            holder.lastName = (TextView) convertView.findViewById(R.id.lastname);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.password = (TextView) convertView.findViewById(R.id.password);
        }

        holder.id.setText(iid.get(position));
        holder.firstName.setText("First Name : "+firstName.get(position));
        holder.lastName.setText("Last Name : "+lastName.get(position));
        holder.email.setText(   "Email          : "+email.get(position));
        holder.phone.setText(   "Phone         : "+phone.get(position));
        holder.password.setText("Password   : "+password.get(position));

        return convertView;
    }
}