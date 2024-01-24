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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionFragment extends Fragment {

    TextView txtNutrition;
    String id;
    DietModel dietModel;
    public NutritionFragment(String id) {
        this.dietModel = dietModel;
        this.id = id;
    }


/*
    public NutritionFragment(String id) {
        this.id = id;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);

        txtNutrition = view.findViewById(R.id.txtNutri);

        getNutrition(String.valueOf(id));
       // getNutrition("1");
        return view;
    }
    public void getNutrition(String id){
        Call<SingleDietModel> call = ApiClient.yogaInterface().getSingleDiet(Integer.parseInt(id));
        call.enqueue(new Callback<SingleDietModel>() {
            @Override
            public void onResponse(Call<SingleDietModel> call, Response<SingleDietModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();

                    String nutrition = "";
                    for (String str : response.body().getNutritions()) {
                        if(!nutrition.equals(""))
                            nutrition = nutrition+",\n\n " + str;
                        else nutrition=str;
                    }

                    txtNutrition.setText(nutrition.toString());

                }

            }

            @Override
            public void onFailure(Call<SingleDietModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

}