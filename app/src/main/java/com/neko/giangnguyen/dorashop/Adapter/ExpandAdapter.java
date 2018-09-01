package com.neko.giangnguyen.dorashop.Adapter;

import android.content.Context;
import android.icu.util.Measure;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
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
        if(this.list== null){
            return 0;
        }
        return this.list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this.list.get(groupPosition).getSubCategories() == null){
            return 0;
        }
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
        TextView textView = view.findViewById(R.id.category_name);
        ImageView icon = view.findViewById(R.id.icon);
        if(!this.list.get(groupPosition).getHadChild()) {
            icon.setVisibility(View.INVISIBLE);
        } else {
            if (isExpanded)
                icon.setImageResource(R.drawable.ic_remove);
            else icon.setImageResource(R.drawable.ic_add);
        }
        textView.setText(this.list.get(groupPosition).getName());
        return  view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpanListView expanListView = new ExpanListView(this.context);
        ExpandAdapter expandAdapter = new ExpandAdapter(this.context,this.list.get(groupPosition).getSubCategories());
        expanListView.setAdapter(expandAdapter);
        expanListView.setGroupIndicator(null);
        notifyDataSetChanged();

        return expanListView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    private class ExpanListView extends ExpandableListView{

        public ExpanListView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(900, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(900,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
