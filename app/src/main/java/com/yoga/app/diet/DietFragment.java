package com.yoga.app.diet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoga.app.R;
import com.yoga.app.adapter.DietAdapter;
import com.yoga.app.adapter.MeditationAdapter;
import com.yoga.app.adapter.MustTryDietAdapter;
import com.yoga.app.adapter.PopularRecipeAdapter;
import com.yoga.app.adapter.RecomendDietAdapter;
import com.yoga.app.adapter.YogaAdapter;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.MeditationModel;
import com.yoga.app.model.MustTryDietModel;
import com.yoga.app.model.PopularRecipeModel;
import com.yoga.app.model.RecommDietModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DietFragment extends Fragment {
    RecyclerView rvDiet,rvRecomDiet,rvMust,rvPopularRecipe;
    TextView txtBreakfast,txtLunch,txtDinner;
    Activity context;
    ProgressDialog progressDoalog;
    LinearLayoutManager HorizontalLayout;
    DietAdapter dietAdapter;
    RecomendDietAdapter recomendDietAdapter;
    MustTryDietAdapter mustTryDietAdapter;
    PopularRecipeAdapter popularRecipeAdapter;

    DietModel dietModel;

    public DietFragment(DietModel dietModel) {
        this.dietModel = dietModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        context = requireActivity();

        rvDiet = view.findViewById(R.id.rvDiet);
        rvRecomDiet = view.findViewById(R.id.rvRecomDiet);
        rvMust = view.findViewById(R.id.rvMust);
        rvPopularRecipe = view.findViewById(R.id.rvPopularRecipe);
        txtBreakfast = view.findViewById(R.id.txtBreakfast);
        txtLunch = view.findViewById(R.id.txtLunch);
        txtDinner = view.findViewById(R.id.txtDinner);


        txtBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBreakfast.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtLunch.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtDinner.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLunch.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtBreakfast.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtDinner.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDinner.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtLunch.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtBreakfast.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });

        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.show();
        //.setMessage("Loading....");
        progressDoalog.show();

        getDietList();
        getRecomList();
        getTryDietList();
        getPopularRecipeList();

        return view;
    }

    public void getDietList() {
        Call<ArrayList<DietModel>> call = ApiClient.yogaInterface().getDiet();
        call.enqueue(new Callback<ArrayList<DietModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DietModel>> call, Response<ArrayList<DietModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getDiet(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DietModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getRecomList() {
        Call<ArrayList<RecommDietModel>> call = ApiClient.yogaInterface().getRecomDiet();
        call.enqueue(new Callback<ArrayList<RecommDietModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecommDietModel>> call, Response<ArrayList<RecommDietModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getRecomDiet(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecommDietModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getTryDietList() {
        Call<ArrayList<MustTryDietModel>> call = ApiClient.yogaInterface().getMustTryDiet();
        call.enqueue(new Callback<ArrayList<MustTryDietModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MustTryDietModel>> call, Response<ArrayList<MustTryDietModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getTryDiet(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MustTryDietModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getPopularRecipeList() {
        Call<ArrayList<PopularRecipeModel>> call = ApiClient.yogaInterface().getPopularRecipe();
        call.enqueue(new Callback<ArrayList<PopularRecipeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PopularRecipeModel>> call, Response<ArrayList<PopularRecipeModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getPopularRecipe(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PopularRecipeModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getDiet(List<DietModel> dietModelList) {
        dietAdapter = new DietAdapter(requireContext(),dietModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvDiet.setLayoutManager(HorizontalLayout);
        rvDiet.setAdapter(dietAdapter);
    }
    private void getRecomDiet(List<RecommDietModel> recommDietModelList) {
        recomendDietAdapter = new RecomendDietAdapter(requireContext(),recommDietModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvRecomDiet.setLayoutManager(HorizontalLayout);
        rvRecomDiet.setAdapter(recomendDietAdapter);
    }
    private void getTryDiet(List<MustTryDietModel> mustTryDietModelList) {
        mustTryDietAdapter = new MustTryDietAdapter(requireContext(),mustTryDietModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvMust.setLayoutManager(HorizontalLayout);
        rvMust.setAdapter(mustTryDietAdapter);
    }
    private void getPopularRecipe(List<PopularRecipeModel> popularRecipeModelList) {
        popularRecipeAdapter = new PopularRecipeAdapter(requireContext(),popularRecipeModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvPopularRecipe.setLayoutManager(HorizontalLayout);
        rvPopularRecipe.setAdapter(popularRecipeAdapter);
    }

}