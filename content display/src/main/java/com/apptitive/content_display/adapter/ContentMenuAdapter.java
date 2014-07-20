package com.apptitive.content_display.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.apptitive.content_display.R;
import com.apptitive.content_display.model.ContentMenu;
import java.util.List;

/**
 * Created by rayhan on 7/19/2014.
 */
public class ContentMenuAdapter extends ArrayAdapter<ContentMenu> {
    Context context;
    int layoutResourceId;

    public ContentMenuAdapter(Context context, int layout, List<ContentMenu> contentMenus) {
        super(context, layout, contentMenus);
        this.context = context;
        layoutResourceId = layout;
    }

    private class ViewHolder {
        TextView title;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentMenu contentMenu = getItem(position);

        View inflatedView = LayoutInflater.from(context).inflate(
                layoutResourceId, parent, false);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflatedView;
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_content_menu_title);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.title.setText(contentMenu.getTitle());

        return convertView;
    }
}
