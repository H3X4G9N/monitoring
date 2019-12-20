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

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{

    List<Group> ItemList;
    LayoutInflater layoutInflater;
    Context context;
    int position;


    public GroupAdapter(List<Group> ItemList, Context context) {  // 11)
        this.ItemList = ItemList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = layoutInflater.from(context); // 6)
        View newView = layoutInflater.inflate(R.layout.rootgrouprow_list, viewGroup,false); // 7)

        RecyclerView.ViewHolder viewHolder = new ViewHolder(newView); // 8)
        return (ViewHolder) viewHolder; // 8)
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.rootGroupName.setText(ItemList.get(i).getRootName());
        viewHolder.rootGroupDescription.setText(ItemList.get(i).getDescription());
        viewHolder.linear.setTag(viewHolder);
        viewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder viewHolder2 = (ViewHolder)  v.getTag();
                position = viewHolder2.getAdapterPosition();
                System.out.println(ItemList.get(position).getName());
                Intent intent = new Intent(context, AllVisibleGroupActivity.class);
                intent.putExtra("GroupforAuthorization", ItemList.get(position));
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
