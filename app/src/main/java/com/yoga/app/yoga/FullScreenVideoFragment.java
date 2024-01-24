package com.yoga.app.yoga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yoga.app.R;
import com.yoga.app.adapter.MeditationUpNextAdapter;
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
import com.yoga.app.roomdb.AdapterListener;
import com.yoga.app.roomdb.Save;
import com.yoga.app.roomdb.SaveAdapter;
import com.yoga.app.roomdb.SaveDao;
import com.yoga.app.roomdb.SaveDatabase;

public class FullScreenVideoFragment extends DialogFragment implements AdapterListener {
    VideoView videoView;
    ImageView back, bookmark, share;
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
    String videoLink = "",image,title,id;
    SaveDatabase saveDatabase;
    SaveDao saveDao;
    SaveAdapter saveAdapter;
    Save save;

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    public FullScreenVideoFragment(Object object, String id,String videoLink,String title,String image) {
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
        if (object instanceof SingleYogaModel)
            this.singleYogaModel = (SingleYogaModel) object;
        if (object instanceof SingleMeditationModel)
            this.singleMeditationModel = (SingleMeditationModel) object;

        this.id = id;
        this.videoLink = videoLink;
        this.title = title;
        this.image = image;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_full_screen_video, container, false);

        context = requireActivity();
        //videoView = view.findViewById(R.id.videoView);
        youTubePlayerView = view.findViewById(R.id.videoPlayer);
        back = view.findViewById(R.id.back);
        bookmark = view.findViewById(R.id.bookmark);
        share = view.findViewById(R.id.share);

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "dRadE0kjS_Q";
                Log.d("TAG", "onReady: ");
                youTubePlayer.loadVideo(videoLink, 0);

            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookmark.setBackgroundColor(Color.GRAY);
                if (!image.equals("") && !title.equals("")) {
                    save = new Save(id,videoLink,title,image);
                    //saveAdapter.bookmarkSave(save);
                    saveDao.Insert(save);

                    Toast.makeText(requireActivity(), "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onUpdate(Save save) {

    }

    @Override
    public void onDelete(double id, int pos) {

    }
}