package com.example.mobilecontent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.databinding.FragmentWriteBinding;
import com.example.mobilecontent.items.RecyclerViewAdapter_QuestionWrite;
import com.example.mobilecontent.items.recyclerItem_QuestionWrite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    private ArrayList<recyclerItem_QuestionWrite> mList = new ArrayList<recyclerItem_QuestionWrite>();

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

    public void addItem(Drawable icon, String title, String catagori1, String catagori2, int goods, int views) {
        recyclerItem_QuestionWrite item = new recyclerItem_QuestionWrite();
        item.SetIcon(icon);
        item.setTitle(title);
        item.SetGoods(goods);
        item.SetViews(views);
        item.setcatagori1(catagori1);
        item.setcatagori2(catagori2);
        mList.add(item);
        mAdapter.notifyDataSetChanged();
    }

    public void DatesSet() {
        mList = new ArrayList<recyclerItem_QuestionWrite>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("userWritting")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                addItem(getResources().getDrawable(R.drawable.ic_account_circle_black_18dp), document.getData().get("title").toString(), "#질문", "#식물"
                                        , Integer.parseInt(document.get("goods").toString()), Integer.parseInt(document.get("views").toString()));
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        mAdapter = new RecyclerViewAdapter_QuestionWrite(mList);
        binding.writtingRecyclerview.setAdapter(mAdapter);
        binding.writtingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        addItem(getResources().getDrawable(R.drawable.ic_account_circle_black_18dp), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
//        addItem(getResources().getDrawable(R.drawable.ic_account_circle_black_18dp), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
//        addItem(getResources().getDrawable(R.drawable.ic_account_circle_black_18dp), "이게 뭐임", "10회", "아니이게 뭐죠??....", "10회");
    }

    private void Spinner() {
        String[] showset = new String[]{
                "질문", "일지"
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