package com.neko.giangnguyen.dorashop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;
import com.neko.giangnguyen.dorashop.R;


import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Category> list;
    public ExpandAdapter(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return this.list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.list.get(groupPosition).getSubCategories().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list.get(groupPosition).getSubCategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return this.list.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return this.list.get(groupPosition).getSubCategories().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.home_menu_parent_layout,parent,false);
        TextView textView = view.findViewById(R.id.tenCa);
        textView.setText(this.list.get(groupPosition).getName());
        return  view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
