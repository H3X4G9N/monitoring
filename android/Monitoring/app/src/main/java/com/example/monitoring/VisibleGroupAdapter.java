package com.example.monitoring;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring.model.Group;

import java.util.List;

public class VisibleGroupAdapter extends RecyclerView.Adapter<VisibleGroupAdapter.ViewHolder>{

    List<Group> ItemList;
    LayoutInflater layoutInflater;
    Context context;
    int position;


    public VisibleGroupAdapter(List<Group> ItemList, Context context) {
        this.ItemList = ItemList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = layoutInflater.from(context);
        View newView = layoutInflater.inflate(R.layout.rootgrouprow_list, viewGroup,false);

        RecyclerView.ViewHolder viewHolder = new ViewHolder(newView);
        return (ViewHolder) viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.rootGroupName.setText(ItemList.get(i).getName());
        viewHolder.rootGroupDescription.setText(ItemList.get(i).getDescription());
        viewHolder.linear.setTag(viewHolder);
        viewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder viewHolder2 = (ViewHolder)  v.getTag();
                position = viewHolder2.getAdapterPosition();
                Intent intent = new Intent(context, GroupDataCollectorsActivity.class);
                intent.putExtra("VisibleGroupForDC", ItemList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        TextView rootGroupName,rootGroupDescription;
        LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootGroupName = itemView.findViewById(R.id.group_name);
            rootGroupDescription = itemView.findViewById(R.id.group_description);
            linear = itemView.findViewById(R.id.linear);
        }


    }



}
