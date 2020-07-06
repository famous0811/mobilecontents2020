package com.example.mobilecontent;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecontent.databinding.FragmentQuestionBinding;
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
    ArrayList<recyclerItem_QuestionWrite> mList = new ArrayList<recyclerItem_QuestionWrite>();
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
        Spinner();
        binding.addCagori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editcatgori.getText().toString().length() != 0) {
                    addcatagri(binding.editcatgori.getText().toString());
                    binding.editcatgori.setText("");
                }
            }
        });
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

    private void getdb() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mList = new ArrayList<recyclerItem_QuestionWrite>();
        //.whereEqualTo("view",)
        db.collection("userwritequestion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("TAG", document.getId() + " => " + data.get("title"));
                                addItem(getResources().getDrawable(R.drawable.ic_account_circle_black_18dp), document.getData().get("title").toString(), "#질문", "#식물"
                                        , Integer.parseInt(document.get("goods").toString()), Integer.parseInt(document.get("views").toString()));
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void addData() {
        getdb();
        mAdapter = new RecyclerViewAdapter_QuestionWrite(mList);//어뎁터 클래스 명으로 생성
        binding.questionsRecyclerview.setAdapter(mAdapter);
        binding.questionsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void addcatagri(String text) {
        TextView addText = new TextView(getActivity());
        addText.setText("#" + text);
        addText.setBackground(getResources().getDrawable(R.drawable.rounded));
        addText.setTextColor(getResources().getColor(R.color.colorPrimary));

        LinearLayout.LayoutParams parambtn = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parambtn.setMargins(0, 0, 10, 0);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) addText.getLayoutParams();
////        params.setMargins(0,0,10,0);
        addText.setLayoutParams(parambtn);
        binding.catagoris.addView(addText);
    }

}