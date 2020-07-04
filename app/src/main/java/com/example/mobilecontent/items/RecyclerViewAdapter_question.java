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

public class RecyclerViewAdapter_question extends RecyclerView.Adapter<RecyclerViewAdapter_question.ViewHolder>{
    List<recyclerItem_question> items = new ArrayList<recyclerItem_question>();

    public RecyclerViewAdapter_question(ArrayList<recyclerItem_question> data){
        items=data;
        notifyDataSetChanged();
    }

    public List<recyclerItem_question> getItems() {
        return items;
    } //List의 아이템을 반환하는 함수

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_question, parent, false) ;
        RecyclerViewAdapter_question.ViewHolder vh = new RecyclerViewAdapter_question.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerItem_question item=items.get(position);

        holder.icon.setImageDrawable(item.getIcon());
        holder.title.setText(item.getTitle());
        holder.catagri1.setText(item.getCatagri1());
        holder.catagri2.setText(item.getCatagri2());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,catagri1,catagri2;
        ImageView icon;

        ViewHolder(View itemView){
            super(itemView);
            icon = itemView.findViewById(R.id.questionIcon);
            title = itemView.findViewById(R.id.questiontitle);
            catagri1 = itemView.findViewById(R.id.category_1);
            catagri2 = itemView.findViewById(R.id.category_2);
        }
    }
}
