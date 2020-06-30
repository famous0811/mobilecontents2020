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

public class RecyclerViewAdapter_write extends RecyclerView.Adapter<RecyclerViewAdapter_write.ViewHolder> {

    List<recyclerItem_write> items = new ArrayList<recyclerItem_write>();

    public RecyclerViewAdapter_write(ArrayList<recyclerItem_write> data){
        items=data;
        notifyDataSetChanged();
    }
    public List<recyclerItem_write> getItems() {
        return items;
    } //List의 아이템을 반환하는 함수

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView views,goods,some_contents,title;
        ImageView icon;

        ViewHolder(View itemView){
            super(itemView);
            icon = itemView.findViewById(R.id.writenicon);
            views=itemView.findViewById(R.id.writenviews);
            goods=itemView.findViewById(R.id.writengoods);
            some_contents=itemView.findViewById(R.id.writencontents);
            title=itemView.findViewById(R.id.writentitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_written, parent, false) ;
        RecyclerViewAdapter_write.ViewHolder vh = new RecyclerViewAdapter_write.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerItem_write item= items.get(position);
        holder.icon.setImageDrawable(item.getIcon());
        holder.goods.setText(item.getGoods());
        holder.some_contents.setText(item.getContents());
        holder.title.setText(item.getTitle());
        holder.views.setText(item.getViews());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
