package com.yoga.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yoga.app.R;
import com.yoga.app.helpers.ApiClient;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.MeditationModel;
import com.yoga.app.model.MustTryDietModel;
import com.yoga.app.model.NewMeditationModel;
import com.yoga.app.model.PopularRecipeModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecomendMediModel;
import com.yoga.app.model.RecommDietModel;
import com.yoga.app.model.RecommendedYouModel;
import com.yoga.app.model.SingleMeditationModel;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.UpNext;
import com.yoga.app.model.WeightLossModel;
import com.yoga.app.model.YogaModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreeenVideoActivity extends AppCompatActivity {
    Activity context;
    SingleYogaModel singleYogaModel;
    YouTubePlayerView youTubePlayerView;
    SingleMeditationModel singleMeditationModel;
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
    String videoLink = "";

    public FullScreeenVideoActivity(Object object, String videoLink) {
        if (object instanceof YogaModel)
            this.yogaModel = (YogaModel) object;
        if (object instanceof MeditationModel)
            this.meditationModel = (MeditationModel) object;
        if (object instanceof DietModel)
            this.dietModel = (DietModel) object;
        this.videoLink = videoLink;
    }
/*
    public FullScreeenVideoActivity(YogaModel yogaModel, MeditationModel meditationModel, DietModel dietModel, String videoLink) {
        this.yogaModel = yogaModel;
        this.meditationModel = meditationModel;
        this.dietModel = dietModel;
        this.videoLink = videoLink;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screeen_video);
        context = this;
       // getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "dRadE0kjS_Q";
                Log.d("TAG", "onReady: ");
                youTubePlayer.loadVideo(videoLink, 0);

            }
        });
//        Intent intent=getIntent();
//        videoLink = intent.getStringExtra("video");
    }

}