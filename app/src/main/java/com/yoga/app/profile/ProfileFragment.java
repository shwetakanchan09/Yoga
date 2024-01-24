package com.yoga.app.profile;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yoga.app.R;
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

import java.util.List;

public class ProfileFragment extends Fragment implements AdapterListener {
    RecyclerView rvRecentView, rvSave;
    SaveDatabase saveDatabase;
    SaveDao saveDao;
    SaveAdapter saveAdapter;
    Activity context;
    Save save;

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
    public ProfileFragment(Save save) {
        this.save = save;
    }

    public ProfileFragment(Object object, String id,String videoLink,String title,String image) {
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
        if (object instanceof Save)
            this.save = (Save) object;

        this.id = id;
        this.videoLink = videoLink;
        this.title = title;
        this.image = image;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = requireActivity();
        rvRecentView = view.findViewById(R.id.rvRecentView);
        rvSave = view.findViewById(R.id.rvSave);

        saveDatabase = SaveDatabase.getInstance(requireActivity());
        saveDao = saveDatabase.getDao();



        saveAdapter = new SaveAdapter(requireActivity(),this);
        rvSave.setAdapter(saveAdapter);
        rvSave.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return  view;
    }
    private void fetchData() {
        saveAdapter.clearData();
        List<Save> userList = saveDao.getAllVideo();
        for (int i = 0; i < userList.size(); i++) {
             save = userList.get(i);
            saveAdapter.bookmarkSave(save);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public void onUpdate(Save save) {

    }

    @Override
    public void onDelete(double id, int pos) {

    }
}