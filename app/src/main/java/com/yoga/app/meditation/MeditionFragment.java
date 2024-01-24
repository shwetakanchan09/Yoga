package com.yoga.app.meditation;

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
import com.yoga.app.adapter.MeditationAdapter;
import com.yoga.app.adapter.NewMeditationAdapter;
import com.yoga.app.adapter.PopularYogaAdapter;
import com.yoga.app.adapter.RecommendMediAdapter;
import com.yoga.app.adapter.YogaAdapter;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.MeditationModel;
import com.yoga.app.model.NewMeditationModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecomendMediModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeditionFragment extends Fragment {

    RecyclerView rvMeditation,rvRecomMedi,rvFavorite,rvNew;

    MeditationAdapter meditationAdapter;
    RecommendMediAdapter recommendMediAdapter;
    NewMeditationAdapter newMeditationAdapter;
    Activity context;
    ProgressDialog progressDoalog;
    LinearLayoutManager HorizontalLayout;
    TextView txtSleep,txtRelax,txtFocus;
    MeditationModel meditationModel;

    public MeditionFragment(MeditationModel meditationModel) {
        this.meditationModel = meditationModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medition, container, false);
        context = requireActivity();

        rvMeditation = view.findViewById(R.id.rvMeditation);
        rvRecomMedi = view.findViewById(R.id.rvRecomMedi);
        rvFavorite = view.findViewById(R.id.rvFavorite);
        rvNew = view.findViewById(R.id.rvNew);
        txtSleep = view.findViewById(R.id.txtSleep);
        txtRelax = view.findViewById(R.id.txtRelax);
        txtFocus = view.findViewById(R.id.txtFocus);

        txtSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSleep.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtRelax.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtFocus.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRelax.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtSleep.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtFocus.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });
        txtFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFocus.setTextColor(getResources().getColorStateList(R.color.colorAccent));
                txtRelax.setTextColor(getResources().getColorStateList(R.color.gray_dark));
                txtSleep.setTextColor(getResources().getColorStateList(R.color.gray_dark));
            }
        });

        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.show();
        //.setMessage("Loading....");
        progressDoalog.show();

        getMeditationList();
        getRecomendList();
        getNewMediList();

        return view;
    }
    public void getMeditationList() {
        Call<ArrayList<MeditationModel>> call = ApiClient.yogaInterface().getMeditation();
        call.enqueue(new Callback<ArrayList<MeditationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MeditationModel>> call, Response<ArrayList<MeditationModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getMeditation(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MeditationModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getRecomendList() {
        Call<ArrayList<RecomendMediModel>> call = ApiClient.yogaInterface().getRecomendMeditation();
        call.enqueue(new Callback<ArrayList<RecomendMediModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecomendMediModel>> call, Response<ArrayList<RecomendMediModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getRecomMeditationList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecomendMediModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getNewMediList() {
        Call<ArrayList<NewMeditationModel>> call = ApiClient.yogaInterface().getNewMeditation();
        call.enqueue(new Callback<ArrayList<NewMeditationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NewMeditationModel>> call, Response<ArrayList<NewMeditationModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body()!=null) {
                    getNewList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewMeditationModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getMeditation(List<MeditationModel> meditationModelList) {
        meditationAdapter = new MeditationAdapter(requireContext(),meditationModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvMeditation.setLayoutManager(HorizontalLayout);
        rvMeditation.setAdapter(meditationAdapter);
    }
    private void getRecomMeditationList(List<RecomendMediModel> recomendMediModelList) {
        recommendMediAdapter = new RecommendMediAdapter(requireContext(),recomendMediModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvRecomMedi.setLayoutManager(HorizontalLayout);
        rvRecomMedi.setAdapter(recommendMediAdapter);
    }
    private void getNewList(List<NewMeditationModel> newMeditationModelList) {
        newMeditationAdapter = new NewMeditationAdapter(requireContext(),newMeditationModelList);
        HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvNew.setLayoutManager(HorizontalLayout);
        rvNew.setAdapter(newMeditationAdapter);
    }

}