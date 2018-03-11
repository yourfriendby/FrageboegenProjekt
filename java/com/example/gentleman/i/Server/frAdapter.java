package com.example.gentleman.i.Server;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gentleman.i.R;

import java.util.List;

public class frAdapter extends ArrayAdapter<fr> {
    private int resourceid;

    public frAdapter(Context context, int textViewRsourceid, List<fr> objects){
        super(context,textViewRsourceid,objects);
        resourceid=textViewRsourceid;
    }
    @Override
    public View getView(int posision, View converView, ViewGroup parent){
        fr ft=getItem(posision);
        View view= LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        TextView frtxt2=(TextView) view.findViewById(R.id.list_type);
        TextView frtxt=(TextView)view.findViewById(R.id.lisw_item);
        frtxt.setText(ft.getQuestion());
        frtxt2.setText(ft.getType());
        return view;
    }
}