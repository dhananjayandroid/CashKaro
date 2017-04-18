package com.dhananjay.cashkaro_poc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.TopCategory;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 03-03-2017.
 */

public class TopCategoriesGridAdapter extends BaseAdapter {

    private ArrayList<TopCategory> topCategories = new ArrayList<>();

    public TopCategoriesGridAdapter(ArrayList<TopCategory> topCategories) {
        this.topCategories = topCategories;
    }

    @Override
    public int getCount() {
        return topCategories.size();
    }

    @Override
    public TopCategory getItem(int i) {
        return topCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class Holder {
        TextView tvCategory;
        ImageView ivCategory;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_top_categories, parent, false);
            holder = new Holder();
            holder.ivCategory = (ImageView) row.findViewById(R.id.iv_category);
            holder.tvCategory = (TextView) row.findViewById(R.id.tv_category);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        setDataToView(i, holder);
        return row;
    }

    private void setDataToView(int i, Holder holder) {
        TopCategory topCategory = topCategories.get(i);
        holder.tvCategory.setText(topCategory.getCategoryName());
        holder.ivCategory.setImageResource(topCategory.getDrawableId());
    }

}