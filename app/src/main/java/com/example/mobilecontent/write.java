package com.example.mobilecontent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.databinding.FragmentWriteBinding;
import com.example.mobilecontent.items.RecyclerViewAdapter_write;
import com.example.mobilecontent.items.recyclerItem_write;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link write#newInstance} factory method to
 * create an instance of this fragment.
 */
public class write extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentWriteBinding binding;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<recyclerItem_write> mList = new ArrayList<recyclerItem_write>();

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        DatesSet();
        Spinner();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public write() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment write.
     */
    // TODO: Rename and change types and number of parameters
    public static write newInstance(String param1, String param2) {
        write fragment = new write();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void addItem(Drawable icon, String title, String goods, String contexts, String views){
        recyclerItem_write item=new recyclerItem_write();
        item.setIcon(icon);
        item.settitle(title);
        item.setGoods(goods);
        item.setcontents(contexts);
        item.setViews(views);
        mList.add(item);
        mAdapter.notifyDataSetChanged();
    }

    public void DatesSet() {
        mAdapter = new RecyclerViewAdapter_write(mList);
        binding.writtingRecyclerview.setAdapter(mAdapter);
        binding.writtingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
    }

    private void Spinner() {
        String[] showset = new String[]{
                "질문", "일지", "기타"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, showset);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.select.setAdapter(adapter1);
        binding.select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT); //본인이 원하는 작업.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}