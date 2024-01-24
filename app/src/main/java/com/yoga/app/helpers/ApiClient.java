package com.yoga.app.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.yoga.app.model.SingleDietModel;
import com.yoga.app.model.SingleMeditationModel;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.WeightLossModel;
import com.yoga.app.model.YogaModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {
    public static YogaInterface yogaInterface;

    public static YogaInterface yogaInterface() {
        if (yogaInterface == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(80, TimeUnit.SECONDS)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mapi.trycatchtech.com")
                    // .addConverterFactory(ScalerConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            yogaInterface = retrofit.create(YogaInterface.class);

        }
        return yogaInterface;
    }

    public interface YogaInterface {
        @GET("/v3/yoga_app/yoga_list_by_categories")
        Call<ArrayList<YogaModel>> getYogaVideos(@Query("category") String category, @Query("sub_category") String Subcategory);

        @GET("/v3/yoga_app/popular_yoga")
        Call<ArrayList<PopularYogaModel>> getPopularYoga();

        @GET("/v3/yoga_app/recommended_yoga")
        Call<ArrayList<RecommendedYouModel>> getRecommendedYoga();

        @GET("/v3/yoga_app/weight_loss_yoga")
        Call<ArrayList<WeightLossModel>> getWeightLossYoga();
        @GET("/v3/yoga_app/single_yoga")
        Call<SingleYogaModel> getSingleYoga(@Query("yoga_id") int yoga_id);

        @GET("/v3/yoga_app/meditation_list_by_categories?category=sleep")
        Call<ArrayList<MeditationModel>> getMeditation();

        @GET("/v3/yoga_app/recommended_meditation")
        Call<ArrayList<RecomendMediModel>> getRecomendMeditation();

        @GET("/v3/yoga_app/new_meditation")
        Call<ArrayList<NewMeditationModel>> getNewMeditation();

        @GET("/v3/yoga_app/diet_list_by_categories?category=breakfast")
        Call<ArrayList<DietModel>> getDiet();
        @GET("/v3/yoga_app/recommended_diet")
        Call<ArrayList<RecommDietModel>> getRecomDiet();
        @GET("/v3/yoga_app/you_must_try_diet")
        Call<ArrayList<MustTryDietModel>> getMustTryDiet();
        @GET("/v3/yoga_app/popular_diet")
        Call<ArrayList<PopularRecipeModel>> getPopularRecipe();
      @GET("/v3/yoga_app/single_meditation")
        Call<SingleMeditationModel> getSingleMeditation(@Query("meditation_id") int meditation_id);
        @GET("/v3/yoga_app/single_diet")
        Call<SingleDietModel> getSingleDiet(@Query("diet_id") int diet_id);

    }

}
