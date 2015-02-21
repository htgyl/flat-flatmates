package com.flatnflatmates.host;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flatnflatmates.flatflatmates.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by applect on 13/2/15.
 */
public class HostOptionsInfoAdapter extends RecyclerView.Adapter<HostOptionsInfoAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<HostOptionsInformation> data = Collections.emptyList();

    public HostOptionsInfoAdapter(Context context, List<HostOptionsInformation> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.host_option_custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        HostOptionsInformation current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.inconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super( itemView );
            title = (TextView) itemView.findViewById(R.id.hostOptionsMessage);
            icon = (ImageView) itemView.findViewById(R.id.hostOptionsIcon);
        }
    }
}
