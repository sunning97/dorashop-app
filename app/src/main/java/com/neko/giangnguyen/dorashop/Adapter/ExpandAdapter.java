package com.neko.giangnguyen.dorashop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.home_menu_parent_layout,parent,false);
            viewHolder.name = convertView.findViewById(R.id.category_name);
            viewHolder.icon = convertView.findViewById(R.id.icon);

            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        if(!this.list.get(groupPosition).getHadChild()) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            if (isExpanded) {
//                convertView.setBackgroundColor(R.color.gray_2);
                viewHolder.icon.setImageResource(R.drawable.ic_remove);
            }
            else {
//                convertView.setBackgroundColor(R.color.white);
                viewHolder.icon.setImageResource(R.drawable.ic_add);
            }
        }
        viewHolder.name.setText(this.list.get(groupPosition).getName());

        convertView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("CCC",list.get(groupPosition).getName() + " - " + list.get(groupPosition).getId());
                return false;
            }
        });
        return  convertView;
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

    private class ViewHolder{
        TextView name;
        ImageView icon;
    }

}
