package com.example.mobilecontent;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.databinding.FragmentAlreamBinding;
import com.example.mobilecontent.items.RecyclerViewAdapter_alream;
import com.example.mobilecontent.items.recyclerItem_alream;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link alream#newInstance} factory method to
 * create an instance of this fragment.
 */
public class alream extends Fragment {

    private static final String TAG = "recylerror";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentAlreamBinding binding;
    private RecyclerView.Adapter mAdapter;
    ArrayList<recyclerItem_alream> mList = new ArrayList<recyclerItem_alream>();

    public alream() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentAlreamBinding.inflate(inflater, container, false);
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
     * @return A new instance of fragment alream.
     */

    public static alream newInstance(String param1, String param2) {
        alream fragment = new alream();
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
//        getActivity().supportActionBar?.title("your title");
    }
    public void addItem(Drawable icon, String title, String cycle, String time, String week){
        recyclerItem_alream item=new recyclerItem_alream();
        item.setIcon(icon);
        item.settitle(title);
        item.setcycle(cycle);
        item.setTime(time);
        item.setweek(week);
        mList.add(item);
        mAdapter.notifyDataSetChanged();

    }

    public void addData() {
        getdb();
        mAdapter = new RecyclerViewAdapter_alream(mList);//어뎁터 클래스 명으로 생성
        binding.makeprototypeRecyclerview.setAdapter(mAdapter);
        binding.makeprototypeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
//        addItem(getResources().getDrawable(R.drawable.ic_alarm_24px),"test","2주기","07:00","월요일");
    }

    private void getdb() {
        mList = new ArrayList<recyclerItem_alream>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String time = document.get("hour").toString() + ":" + document.get("minute").toString();
//                                Log.d("TAG", document.get("cycle").toString());
                                addItem(getResources().getDrawable(R.drawable.ic_alarm_24px), "test", "2주기", "07:00", "월요일");
                                addItem(getResources().getDrawable(R.drawable.ic_alarm_24px), document.get("title").toString(), document.get("Cycle").toString(), time, document.get("DayOfWeek").toString());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void addweather() {

    }
}