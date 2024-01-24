package com.yoga.app.diet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoga.app.R;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.SingleDietModel;
import com.yoga.app.model.YogaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructionsFragment extends Fragment {

    TextView txtInstruction;
    String id;
    DietModel dietModel;
    public InstructionsFragment(String id) {
        this.dietModel = dietModel;
        this.id = id;
    }


/*
    public InstructionsFragment(String id) {
        this.id = id;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instructions, container, false);

        txtInstruction = view.findViewById(R.id.txtInstruction);

        getInstruction(String.valueOf(id));

       // getInstruction("1");
        return view;
    }
    public void getInstruction(String id){
        Call<SingleDietModel> call = ApiClient.yogaInterface().getSingleDiet(Integer.parseInt(id));
        call.enqueue(new Callback<SingleDietModel>() {
            @Override
            public void onResponse(Call<SingleDietModel> call, Response<SingleDietModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                    String instruction = "";
                    for (String str : response.body().getInstructions()) {
                        if(!instruction.equals(""))
                            instruction = instruction+",\n\n " + str;
                        else instruction=str;
                    }

                    txtInstruction.setText(instruction.toString());

                }

            }

            @Override
            public void onFailure(Call<SingleDietModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

}