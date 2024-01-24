package com.yoga.app.diet;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoga.app.R;
import com.yoga.app.adapter.ReciepeAdapter;
import com.yoga.app.adapter.UpNextAdapter;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.SingleDietModel;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.UpNext;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsFragment extends Fragment {
    Activity context;
    TextView txtIngridients;
    // RecyclerView rvIngridients;
    String id;
    DietModel dietModel;
    SingleDietModel singleDietModel;
    UpNext upNext;
    ReciepeAdapter reciepeAdapter;

    public IngredientsFragment(String id) {
        this.dietModel = dietModel;
        this.id = id;
    }
 /*
    public IngredientsFragment(String id){
        this.id=id;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        context = requireActivity();
        //rvIngridients = view.findViewById(R.id.rvIngridients);
        txtIngridients = view.findViewById(R.id.txtIngridients);


        getIngredients(String.valueOf(id));

        //getIngridients("1");
        return view;
    }

    public void getIngredients(String id) {
        Call<SingleDietModel> call = ApiClient.yogaInterface().getSingleDiet(Integer.parseInt(id));
        call.enqueue(new Callback<SingleDietModel>() {
            @Override
            public void onResponse(Call<SingleDietModel> call, Response<SingleDietModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                    String ingridients = "";
                    for (String str : response.body().getIngredients()) {
                        if(!ingridients.equals(""))
                        ingridients = ingridients+",\n\n " + str;
                        else ingridients=str;
                    }

                    txtIngridients.setText(ingridients.toString());

                }

            }

            @Override
            public void onFailure(Call<SingleDietModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

/*
    private void ingredients(SingleDietModel singleDietModel) {
        reciepeAdapter = new ReciepeAdapter(requireActivity(), singleDietModel);
        rvIngridients.setLayoutManager(new LinearLayoutManager(context));
        rvIngridients.setAdapter(reciepeAdapter);
    }
*/

}