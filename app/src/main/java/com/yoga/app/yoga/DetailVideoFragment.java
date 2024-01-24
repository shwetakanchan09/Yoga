package com.yoga.app.yoga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yoga.app.R;
import com.yoga.app.activity.FullScreeenVideoActivity;
import com.yoga.app.adapter.MeditationUpNextAdapter;
import com.yoga.app.adapter.UpNextAdapter;
import com.yoga.app.diet.IngredientsFragment;
import com.yoga.app.diet.InstructionsFragment;
import com.yoga.app.diet.NutritionFragment;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.MeditationModel;
import com.yoga.app.model.MustTryDietModel;
import com.yoga.app.model.NewMeditationModel;
import com.yoga.app.model.PopularRecipeModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecomendMediModel;
import com.yoga.app.model.RecommDietModel;
import com.yoga.app.model.RecommendedYouModel;
import com.yoga.app.model.SingleDietModel;
import com.yoga.app.model.SingleMeditationModel;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.UpNext;
import com.yoga.app.model.WeightLossModel;
import com.yoga.app.model.YogaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailVideoFragment extends DialogFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout llUpNext, llTabLayout;
    RelativeLayout llViewPager,rlDetail;
    TextView textHeading;
    RecyclerView rvUpNext;
    Activity context;
    String videoLink = "";
    YouTubePlayerView youTubePlayerView;
    YogaModel yogaModel;
    PopularYogaModel popularYogaModel;
    RecommendedYouModel recommendedYouModel;
    WeightLossModel weightLossModel;
    MeditationModel meditationModel;
    RecomendMediModel recomendMediModel;
    NewMeditationModel newMeditationModel;
    DietModel dietModel;
    RecommDietModel recommDietModel;
    MustTryDietModel mustTryDietModel;
    PopularRecipeModel popularRecipeModel;
    UpNext upNext;
    UpNextAdapter upNextAdapter;
    MeditationUpNextAdapter meditationUpNextAdapter;
    SingleDietModel singleDietModel;
    ImageView imgFullScreen;
    String id,title,image;


   /* IngredientsFragment ingredientsFragment = new IngredientsFragment() ;
    InstructionsFragment instructionsFragment = new InstructionsFragment();
    NutritionFragment nutritionFragment = new NutritionFragment();*/

    public DetailVideoFragment(Object object, String id,String videoLink,String title,String image) {
        if (object instanceof YogaModel)
            this.yogaModel = (YogaModel) object;
        if (object instanceof RecommendedYouModel)
            this.recommendedYouModel = (RecommendedYouModel) object;
        if (object instanceof PopularYogaModel)
            this.popularYogaModel = (PopularYogaModel) object;
        if (object instanceof WeightLossModel)
            this.weightLossModel = (WeightLossModel) object;
        if (object instanceof MeditationModel)
            this.meditationModel = (MeditationModel) object;
        if (object instanceof RecomendMediModel)
            this.recomendMediModel = (RecomendMediModel) object;
        if (object instanceof NewMeditationModel)
            this.newMeditationModel = (NewMeditationModel) object;
        if (object instanceof DietModel)
            this.dietModel = (DietModel) object;
        if (object instanceof RecommDietModel)
            this.recommDietModel = (RecommDietModel) object;
        if (object instanceof MustTryDietModel)
            this.mustTryDietModel = (MustTryDietModel) object;
        if (object instanceof PopularRecipeModel)
            this.popularRecipeModel = (PopularRecipeModel) object;
        if (object instanceof UpNext)
            this.upNext = (UpNext) object;

        this.id = id;
        this.videoLink = videoLink;
        this.title = title;
        this.image = image;
    }


    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_yoga, container, false);
        context = requireActivity();

        youTubePlayerView = view.findViewById(R.id.videourl);
        rvUpNext = view.findViewById(R.id.rvUpNext);
        textHeading = view.findViewById(R.id.textHeading);
        llUpNext = view.findViewById(R.id.llUpNext);
        tabLayout = view.findViewById(R.id.tablayoutDiet);
        viewPager = view.findViewById(R.id.viewPagerDiet);
        llViewPager = view.findViewById(R.id.llViewPager);
        imgFullScreen = view.findViewById(R.id.fullScreen);
        rlDetail = view.findViewById(R.id.rlDetail);
        //llTabLayout = view.findViewById(R.id.llTabLayout);

        rlDetail.setClipToOutline(true);

        tabLayout.addTab(tabLayout.newTab().setText("Ingredients"));
        tabLayout.addTab(tabLayout.newTab().setText("Instructions"));
        tabLayout.addTab(tabLayout.newTab().setText("Nutrition"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabLayoutAdapter adapter=new TabLayoutAdapter(requireActivity(),getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "dRadE0kjS_Q";
                Log.d("TAG", "onReady: ");
                youTubePlayer.loadVideo(videoLink, 0);

            }
        });

/*
        imgFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.openFragment(new FullScreenVideoFragment(id,videoLink,title,image));

            }
        });
*/

        if (yogaModel != null)
            getUpNextList(yogaModel.getId());
        else if (meditationModel != null)
            getUpNextMeditationList(meditationModel.getId());
        else {
            llUpNext.setVisibility(View.GONE);
        }

        if (dietModel != null) {
            llViewPager.setVisibility(View.VISIBLE);
        } else {
            llViewPager.setVisibility(View.GONE);
        }

        return view;
    }

    public void getUpNextList(String id) {

        Call<SingleYogaModel> call = ApiClient.yogaInterface().getSingleYoga(Integer.parseInt(id));
        call.enqueue(new Callback<SingleYogaModel>() {
            @Override
            public void onResponse(Call<SingleYogaModel> call, Response<SingleYogaModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                    upNext(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleYogaModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void upNext(SingleYogaModel singleYogaModel) {
        upNextAdapter = new UpNextAdapter(requireActivity(), singleYogaModel);
        rvUpNext.setLayoutManager(new LinearLayoutManager(context));
        rvUpNext.setAdapter(upNextAdapter);
    }

    public void getUpNextMeditationList(String id) {
        Call<SingleMeditationModel> call = ApiClient.yogaInterface().getSingleMeditation(Integer.parseInt(id));
        call.enqueue(new Callback<SingleMeditationModel>() {
            @Override
            public void onResponse(Call<SingleMeditationModel> call, Response<SingleMeditationModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                    upNextMeditation(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleMeditationModel> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void upNextMeditation(SingleMeditationModel singleMeditationModel) {
        meditationUpNextAdapter = new MeditationUpNextAdapter(requireActivity(), singleMeditationModel);
        rvUpNext.setLayoutManager(new LinearLayoutManager(context));
        rvUpNext.setAdapter(meditationUpNextAdapter);


    }
    public class TabLayoutAdapter extends FragmentPagerAdapter {

        Context mContext;
        int mTotalTabs;

        public TabLayoutAdapter(Context context , FragmentManager fragmentManager , int totalTabs) {
            super(fragmentManager);
            mContext = context;
            mTotalTabs = totalTabs;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d("asasas" , position + "");
            switch (position) {
                case 0:
                    return new IngredientsFragment(id);
                case 1:
                    return new InstructionsFragment(id);
                case 2:
                    return new NutritionFragment(id);
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return mTotalTabs;
        }
    }

}

