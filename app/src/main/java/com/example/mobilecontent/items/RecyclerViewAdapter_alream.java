package com.example.mobilecontent.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_alream extends RecyclerView.Adapter<RecyclerViewAdapter_alream.ViewHolder>{

    List<recyclerItem_alream> items = new ArrayList<recyclerItem_alream>();
    //item view와 연결
    public RecyclerViewAdapter_alream(ArrayList<recyclerItem_alream> data){
        items=data;
        notifyDataSetChanged();
    }

    public List<recyclerItem_alream> getItems() {
        return items;
    } //List의 아이템을 반환하는 함수

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView maintitle,cycle,weeks,time;
        ImageView icon;

        ViewHolder(View itemView){
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            maintitle=itemView.findViewById(R.id.title);
            cycle=itemView.findViewById(R.id.cycle);
            weeks=itemView.findViewById(R.id.weeks);
            time=itemView.findViewById(R.id.time);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_alream, parent, false) ;
        RecyclerViewAdapter_alream.ViewHolder vh = new RecyclerViewAdapter_alream.ViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerItem_alream item=items.get(position);
        holder.cycle.setText(item.getcycle());
        holder.icon.setImageDrawable(item.getIcon());
        holder.weeks.setText(item.getweeks());
        holder.maintitle.setText(item.getTitle());
        holder.time.setText(item.gettime());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return items.size();
    }
}
