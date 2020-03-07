package com.example.organizer_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_tops extends BaseAdapter {

    private Context context;
    private ArrayList<ValuesModel> valuesModelArrayList;

    public adapter_tops(Context con, ArrayList<ValuesModel> valuesModelArrayList){
        this. context = con;
        this.valuesModelArrayList = valuesModelArrayList;
    }

    @Override
    public int getCount(){return valuesModelArrayList.size();}

    @Override
    public Object getItem(int pos){
        return valuesModelArrayList.get(pos);
    }

    @Override
    public long getItemId(int position){ return 0;}

    @Override
    public View getView(int pos, View v, ViewGroup parent){
        ViewHolder viewHolder;

        if(v == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.dialog_viewtop, null, true);

            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_add_name);
            viewHolder.tv_tags = (TextView) v.findViewById(R.id.tv_add_tag);

            v.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)v.getTag();
        }
        viewHolder.tv_name.setText  ("Name:   " + valuesModelArrayList.get(pos).getName());
        viewHolder.tv_tags.setText  ("Tags:   " + valuesModelArrayList.get(pos).getTags());

        return v;
    }

    public class ViewHolder{
        protected TextView tv_name, tv_tags, tv_photo;
    }

}
