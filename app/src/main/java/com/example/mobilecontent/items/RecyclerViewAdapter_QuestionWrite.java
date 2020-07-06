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

public class RecyclerViewAdapter_QuestionWrite extends RecyclerView.Adapter<RecyclerViewAdapter_QuestionWrite.ViewHolder> {
    List<recyclerItem_QuestionWrite> items = new ArrayList<recyclerItem_QuestionWrite>();

    public RecyclerViewAdapter_QuestionWrite(ArrayList<recyclerItem_QuestionWrite> data) {
        items = data;
        notifyDataSetChanged();
    }

    public List<recyclerItem_QuestionWrite> getItems() {
        return items;
    } //List의 아이템을 반환하는 함수

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_question, parent, false);
        RecyclerViewAdapter_QuestionWrite.ViewHolder vh = new RecyclerViewAdapter_QuestionWrite.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerItem_QuestionWrite item = items.get(position);

        holder.icon.setImageDrawable(item.getIcon());
        holder.title.setText(item.getTitle());
        holder.views.setText(item.getViews());
        holder.goods.setText(item.getGoods());
        holder.catagri1.setText(item.getCatagri1());
        holder.catagri2.setText(item.getCatagri2());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, catagri1, catagri2, views, goods;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.questionIcon);
            title = itemView.findViewById(R.id.questiontitle);
            catagri1 = itemView.findViewById(R.id.category_1);
            catagri2 = itemView.findViewById(R.id.category_2);
            goods = itemView.findViewById(R.id.question_goods);
            views = itemView.findViewById(R.id.question_views);
        }
    }
}
