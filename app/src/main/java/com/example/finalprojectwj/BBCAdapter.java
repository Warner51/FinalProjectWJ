package com.example.finalprojectwj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BBCAdapter extends BaseAdapter {

    private Context context;
    private List<String> newsItems;
    private LayoutInflater inflater;

    public BBCAdapter(Context context, List<String> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BBCItem item = (BBCItem) getItem(position);

        if (item != null) {
            viewHolder.titleTextView.setText(item.getTitle());
            viewHolder.descriptionTextView.setText(item.getDescription());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View view) {
            titleTextView = view.findViewById(R.id.titleFill);
            descriptionTextView = view.findViewById(R.id.descFill);
        }
    }
}

