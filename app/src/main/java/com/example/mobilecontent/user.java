package com.example.mobilecontent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mobilecontent.databinding.FragmentUserBinding;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user extends Fragment {
    private static final int REQUEST_CODE = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SharedPreferences sf;

    public user() {
        // Required empty public constructor
    }

    private FragmentUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        sf=this.getActivity().getSharedPreferences("userdata",MODE_PRIVATE);

        String email = sf.getString("email","");
        String nickname=sf.getString("nickname","");
        //여기다 집어넣으면 됨
        binding.useremail.setText(email);
        binding.UserNicname.setText(nickname);

        binding.userupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("email",email);
                editor.putString("nickname",nickname);
                editor.putString("about",binding.aboutme.getText().toString());
            }
        });

        binding.ChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user.
     */
    // TODO: Rename and change types and number of parameters
    public static user newInstance(String param1, String param2) {
        user fragment = new user();
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
}