package com.example.organizer_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FilterReader;
import java.util.ArrayList;

public class adapter_viewtop extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Model> modelArrayList;

    ArrayList<Model> modelArrayList_value;
    ValueFilter valueFilter;

    public adapter_viewtop(Context context, int layout, ArrayList<Model> list){
        this.context = context;
        this.layout = layout;
        this.modelArrayList = list;
        modelArrayList_value = list;
    }

    @Override
    public int getCount(){return modelArrayList.size();}

    @Override
    public Object getItem(int pos){return  modelArrayList.get(pos);}

    @Override
    public long getItemId(int pos){return pos;}

    private class ViewHolder{
        TextView tv_name, tv_tag;
        ImageView iv_photo;
    }

    @Override
    public View getView(int pos, View v, ViewGroup vg){
        View row = v;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.tv_name = row.findViewById(R.id.tv_name);
            holder.tv_tag = row.findViewById(R.id.tv_tag);
            holder.iv_photo = row.findViewById(R.id.iv_photo);
        }else{
            holder = (ViewHolder)row.getTag();
        }
        Model model = modelArrayList.get(pos);

        holder.tv_name.setText(model.getName());
        holder.tv_tag.setText(model.getTags());

        byte[] image = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.iv_photo.setImageBitmap(bitmap);

        return  row;
    }

    @Override
    public  Filter getFilter(){
        if(valueFilter == null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results = new FilterResults();

            if(constraint != null || constraint.length() > 0){
                ArrayList<Model> filterList = new ArrayList<>();
                for(int i = 0; i < modelArrayList_value.size(); i++){
                    if((modelArrayList_value.get(i).getName().toUpperCase())
                        .contains(constraint.toString().toUpperCase())){
                        Model model = new Model(modelArrayList_value.get(i).getId(),
                                modelArrayList_value.get(i).getName(),
                                modelArrayList_value.get(i).getTags(),
                                modelArrayList_value.get(i).getImage());
                        filterList.add(model);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            modelArrayList = (ArrayList<Model>) results.values;
            notifyDataSetChanged();
        }
    };
}
