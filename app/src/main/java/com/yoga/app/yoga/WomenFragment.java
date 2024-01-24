package com.yoga.app.yoga;

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
import com.yoga.app.adapter.PopularYogaAdapter;
import com.yoga.app.adapter.RecommendedYogaAdapter;
import com.yoga.app.adapter.WeightLossAdapter;
import com.yoga.app.adapter.YogaAdapter;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecommendedYouModel;
import com.yoga.app.model.WeightLossModel;
import com.yoga.app.model.YogaModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WomenFragment extends Fragment {

    RecyclerView recyclerView, rvPopularYoga,rvRecommend,rvWeightLoss;
    YogaAdapter adapter;
    PopularYogaAdapter popularYogaAdapter;
    RecommendedYogaAdapter recommendedYogaAdapter;
    WeightLossAdapter weightLossAdapter;
    Activity context;
    ProgressDialog progressDoalog;
    LinearLayoutManager HorizontalLayout;
    TextView txtNew,txtPro,txtSkill;

    YogaModel yogaModel;

    public WomenFragment(YogaModel yogaModel) {
        this.yogaModel = yogaModel;
    }

    int changeColor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_women, container, false);
        context = requireActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        rvPopularYoga = view.findViewById(R.id.rvPopularYoga);
        rvRecommend = view.findViewById(R.id.rvRecommend);
        rvWeightLoss = view.findViewById(R.id.rvWeightlossYoga);
        txtNew = view.findViewById(R.id.txtNew);
        txtSkill = view.findViewById(R.id.txtSkill);
        txtPro = view.findViewById(R.id.txtPro);

        txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    txtNew.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                    txtSkill.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                    txtPro.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    txtSkill.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                    txtNew.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                    txtPro.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    txtPro.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                    txtSkill.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                    txtNew.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });

        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.show();
        //.setMessage("Loading....");
        progressDoalog.show();



        //Api Calling
        getYogaList("men","skilled");
        getPopularYogaList();
        getRecommendedYogaList();
        getWeightLossList();

        return view;

    }

    public void getYogaList(String category, String  subcategory){
        Call<ArrayList<YogaModel>> call = ApiClient.yogaInterface().getYogaVideos(category, subcategory);
        call.enqueue(new Callback<ArrayList<YogaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<YogaModel>> call, Response<ArrayList<YogaModel>> response) {
                progressDoalog.dismiss();
                if (response.code()==200 && response.body()!=null) {
                    generateVideoList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<YogaModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getPopularYogaList(){
        Call<ArrayList<PopularYogaModel>> call = ApiClient.yogaInterface().getPopularYoga();
        call.enqueue(new Callback<ArrayList<PopularYogaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PopularYogaModel>> call, Response<ArrayList<PopularYogaModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    popularYogaList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PopularYogaModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getRecommendedYogaList(){
        Call<ArrayList<RecommendedYouModel>> call = ApiClient.yogaInterface().getRecommendedYoga();
        call.enqueue(new Callback<ArrayList<RecommendedYouModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecommendedYouModel>> call, Response<ArrayList<RecommendedYouModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    recommendedYogaList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecommendedYouModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getWeightLossList(){
        Call<ArrayList<WeightLossModel>> call = ApiClient.yogaInterface().getWeightLossYoga();
        call.enqueue(new Callback<ArrayList<WeightLossModel>>() {
            @Override
            public void onResponse(Call<ArrayList<WeightLossModel>> call, Response<ArrayList<WeightLossModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    weightLossYogaList(response.body());

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WeightLossModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateVideoList(List<YogaModel> videoModelList) {
        adapter = new YogaAdapter(requireContext(),videoModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
    }
    private void popularYogaList(List<PopularYogaModel> popularYogaModelList) {
        popularYogaAdapter = new PopularYogaAdapter(requireContext(),popularYogaModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvPopularYoga.setLayoutManager(HorizontalLayout);
        rvPopularYoga.setAdapter(popularYogaAdapter);
    }
    private void recommendedYogaList(List<RecommendedYouModel> recommendedYouModelList) {
        recommendedYogaAdapter = new RecommendedYogaAdapter(requireContext(),recommendedYouModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvRecommend.setLayoutManager(HorizontalLayout);
        rvRecommend.setAdapter(recommendedYogaAdapter);
    }
    private void weightLossYogaList(List<WeightLossModel> weightLossModelList) {
        weightLossAdapter = new WeightLossAdapter(requireContext(),weightLossModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvWeightLoss.setLayoutManager(HorizontalLayout);
        rvWeightLoss.setAdapter(weightLossAdapter);
    }

}