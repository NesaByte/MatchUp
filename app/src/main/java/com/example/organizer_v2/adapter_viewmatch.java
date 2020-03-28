package com.example.organizer_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
    private Context mcontext;
    private int mlayout;
    private ArrayList<Model_matched> mlist;

    ArrayList<Model_matched> mmValue;
    ValueFilterM mvalueFilter;

    public adapter_viewmatch(Context context, int layout, ArrayList<Model_matched> list) {
        this.mcontext = context;
        this.mlayout = layout;
        this.mlist = list;

        mmValue = list;
    }

    @Override
    public int getCount(){return mlist.size();}

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolderM{
        TextView tv_name_m;
        ImageView iv_phototop, iv_photobottom;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        //adapter_viewmatch.ViewHolder holder = new adapter_viewmatch.ViewHolder();
        ViewHolderM holder = new ViewHolderM();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(mlayout, null);
            holder.tv_name_m = row.findViewById(R.id.tv_name_m);
            holder.iv_phototop = row.findViewById(R.id.iv_phototop);
            holder.iv_photobottom = row.findViewById(R.id.iv_photobottom);
            row.setTag(holder);
        } else {
            holder = (ViewHolderM)row.getTag();
        }

        try{
            Model_matched mm= mlist.get(position);

            holder.tv_name_m.setText(mm.getName_m());

            byte[] imagetop = mm.getImage_t();
            Bitmap bitmaptop = BitmapFactory.decodeByteArray(imagetop, 0, imagetop.length);
            holder.iv_phototop.setImageBitmap(bitmaptop);

            byte[] imagebottom = mm.getImage_b();
            Bitmap bitmapbottom = BitmapFactory.decodeByteArray(imagebottom, 0, imagebottom.length);
            holder.iv_photobottom.setImageBitmap(bitmapbottom);
        }catch (Exception e) {
            Log.e("GetView error: ", e.getMessage());
        }


        return row;
    }
    @Override
    public Filter getFilter() {
        if (mvalueFilter == null) {
            mvalueFilter = new ValueFilterM();
        }
        return mvalueFilter;
    }

    private class ValueFilterM extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null || constraint.length() > 0) {
                ArrayList<Model_matched> filterList = new ArrayList<>();
                for (int i = 0; i < mmValue.size(); i++) {
                    if ((mmValue.get(i).getName_m().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        Model_matched model_matched = new Model_matched(mmValue.get(i).getId_m(),
                                mmValue.get(i).getName_m(),
                                mmValue.get(i).getImage_t(),
                                mmValue.get(i).getImage_b());
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
            mlist = (ArrayList<Model_matched>) results.values;
            notifyDataSetChanged();
        }
    }
}
