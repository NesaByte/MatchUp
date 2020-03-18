package com.example.organizer_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_viewmatch extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Model_matched> list;

    ArrayList<Model_matched> mValue;
    ValueFilter valueFilter;

    public adapter_viewmatch(Context context, int layout, ArrayList<Model_matched> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;

        mValue = list;
    }

    @Override
    public int getCount(){return list.size();}

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView tv_name_m;
        ImageView iv_phototop, iv_photobottom;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        adapter_viewmatch.ViewHolder holder = new adapter_viewmatch.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.tv_name_m = row.findViewById(R.id.tv_name);
            holder.iv_phototop = row.findViewById(R.id.iv_phototop);
            holder.iv_photobottom = row.findViewById(R.id.iv_photobottom);
            row.setTag(holder);
        } else {
            holder = (adapter_viewmatch.ViewHolder)row.getTag();
        }

        Model_matched model_matched = list.get(position);

        holder.tv_name_m.setText(model_matched.getNameMATCHED());

        byte[] imagetop = model_matched.getImageTOP();
        Bitmap bitmaptop = BitmapFactory.decodeByteArray(imagetop, 0, imagetop.length);
        holder.iv_phototop.setImageBitmap(bitmaptop);

        byte[] imagebottom = model_matched.getImageBOTTOM();
        Bitmap bitmapbottom = BitmapFactory.decodeByteArray(imagebottom, 0, imagebottom.length);
        holder.iv_phototop.setImageBitmap(bitmapbottom);

        return row;
    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new adapter_viewmatch.ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null || constraint.length() > 0) {
                ArrayList<Model_matched> filterList = new ArrayList<>();
                for (int i = 0; i < mValue.size(); i++) {
                    if ((mValue.get(i).getNameMATCHED().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        Model_matched model_matched = new Model_matched(mValue.get(i).getIdMATCH(),
                                mValue.get(i).getNameMATCHED(),
                                mValue.get(i).getImageTOP(),
                                mValue.get(i).getImageBOTTOM());
                        filterList.add(model_matched);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (ArrayList<Model_matched>) results.values;
            notifyDataSetChanged();
        }
    }
}
