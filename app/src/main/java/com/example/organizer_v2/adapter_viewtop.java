/** This adapter is very useful to contain all the values of top or bottom into an object to be displayed in a row
 *
 *    @author Nesa Bertanico
 *    @version 1.0
 */
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

import java.util.ArrayList;

public class adapter_viewtop extends BaseAdapter implements Filterable {


    private Context context;
    private int layout;
    private ArrayList<Model> list;

    ArrayList<Model> mValue;
    ValueFilter valueFilter;

    /**
     * a constructor for this adaptor to accept:
     * @param context - accept the context
     * @param layout - number of how many objects to be created
     * @param list - put the objects into an arraylist of model to be displayed later
     */
    public adapter_viewtop(Context context, int layout, ArrayList<Model> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;

        mValue = list;
    }

    /**
     * method to count the size of the list
     * @return
     */
    @Override
    public int getCount(){return list.size();}

    /**
     * method to get the certain object of whatever position
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * method to get the certain id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * class that holds name, tag, and photo of each object
     *
     */
    private class ViewHolder{
        TextView tv_name, tv_tag;
        ImageView iv_photo;
    }

    /**
     * method that overrides the getView
     * this method displays the  object's value in each layout.
     * each row consists of name, tag, and its image
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.tv_name = row.findViewById(R.id.tv_name);
            holder.tv_tag = row.findViewById(R.id.tv_tag);
            holder.iv_photo = row.findViewById(R.id.iv_photo);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Model model = list.get(position);

        holder.tv_name.setText(model.getName());
        holder.tv_tag.setText(model.getTags());

        byte[] image = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.iv_photo.setImageBitmap(bitmap);

        return row;
    }

    /**
     * overrides the method getfilter to help my code show ONLY the filter that is searched
     * @return
     */
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    /**
     * this method enables to add multiple row according to what is being searched
     * each row contains the values of the arraylist(model) and is displayed
     */
    private class ValueFilter extends Filter {
        /**
         * Invoked in a worker thread to filter the data according to the constraint.
         * @param constraint
         * @return
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null || constraint.length() > 0) {
                ArrayList<Model> filterList = new ArrayList<>();
                for (int i = 0; i < mValue.size(); i++) {
                    if ((mValue.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        Model model = new Model(mValue.get(i).getId(),
                                mValue.get(i).getName(),
                                mValue.get(i).getTags(),
                                mValue.get(i).getImage());
                        filterList.add(model);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        /**
         * this method Invoked in the UI thread to publish the filtering results in the user interface.
         * @param constraint
         * @param results
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (ArrayList<Model>) results.values;
            notifyDataSetChanged();
        }
    };

}
