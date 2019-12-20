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

import com.example.monitoring.model.DataLogger;
import com.example.monitoring.model.GroupAuthentication;

import java.util.List;

public class DataloggersAdapter extends RecyclerView.Adapter<DataloggersAdapter.ViewHolder>{

    List<DataLogger> ItemList;
    LayoutInflater layoutInflater;
    Context context;
    int position;
    GroupAuthentication DCgroupauthentication;


    public DataloggersAdapter(List<DataLogger> ItemList, Context context, GroupAuthentication DCgroupauthentication) {  // 11)
        this.ItemList = ItemList;
        this.context = context;
        this.DCgroupauthentication = DCgroupauthentication;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = layoutInflater.from(context); // 6)
        View newView = layoutInflater.inflate(R.layout.dataloggers_rowlist, viewGroup,false); // 7)

        RecyclerView.ViewHolder viewHolder = new ViewHolder(newView); // 8)
        return (ViewHolder) viewHolder; // 8)
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.dataLoggerName.setText(ItemList.get(i).getName());
        viewHolder.dataLoggerDescription.setText(ItemList.get(i).getDescription());
        viewHolder.linear.setTag(viewHolder);
        viewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder viewHolder2 = (ViewHolder)  v.getTag();
                position = viewHolder2.getAdapterPosition();
                Intent intent = new Intent(context, DataLoggerInfoActivity.class);
                intent.putExtra("ChosenDataLogger", ItemList.get(position));
                intent.putExtra("authorize", DCgroupauthentication);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        TextView dataLoggerName,dataLoggerDescription;
        LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataLoggerName = itemView.findViewById(R.id.data_logger_name);
            dataLoggerDescription = itemView.findViewById(R.id.data_logger_description);
            linear = itemView.findViewById(R.id.linear2);
        }


    }



}
