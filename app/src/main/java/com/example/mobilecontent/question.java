package com.example.mobilecontent;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.databinding.FragmentQuestionBinding;
import com.example.mobilecontent.items.RecyclerViewAdapter_question;
import com.example.mobilecontent.items.recyclerItem_question;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class question extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentQuestionBinding binding;
    private RecyclerView.Adapter mAdapter;
    ArrayList<recyclerItem_question> mList = new ArrayList<recyclerItem_question>();
    public question() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        addData();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment question.
     */
    // TODO: Rename and change types and number of parameters
    public static question newInstance(String param1, String param2) {
        question fragment = new question();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("DefaultLocale")

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void addItem(Drawable icon, String title, String catagori1, String catagori2){
        recyclerItem_question item=new recyclerItem_question();

        item.SetIcon(icon);
        item.setTitle(title);
        item.setcatagori1(catagori1);
        item.setcatagori2(catagori2);
        mList.add(item);
        mAdapter.notifyDataSetChanged();

    }
    public void addData(){
        mAdapter = new RecyclerViewAdapter_question(mList);//어뎁터 클래스 명으로 생성
        binding.questionsRecyclerview.setAdapter(mAdapter);
        binding.questionsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test1","#노잼","시벌");
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test1","#노잼","시벌");
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test1","#노잼","시벌");
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test1","#노잼","시벌");
    }
}