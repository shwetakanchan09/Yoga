package com.yoga.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yoga.app.profile.ProfileFragment;
import com.yoga.app.R;
import com.yoga.app.diet.DietFragment;
import com.yoga.app.meditation.MeditionFragment;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.MeditationModel;
import com.yoga.app.roomdb.Save;
import com.yoga.app.yoga.YogaDashboardFragment;

public class MainActivity extends AppCompatActivity {
    YogaDashboardFragment yogaDashboardFragment = new YogaDashboardFragment();
    Toolbar toolbar;
    Fragment fragment;
    String fName;
    FragmentManager fm;
    Activity context;
    BottomNavigationView bottomNavigationView;
    AppBarLayout appBarLayout;
    LinearLayout llAppbar;
    EditText etSearch;
    Save save;
    DietModel dietModel;
    MeditationModel meditationModel;
    ImageView imgUser;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        appBarLayout = findViewById(R.id.appBarLayout);
        llAppbar = findViewById(R.id.llAppbar);
        etSearch = findViewById(R.id.etSearch);
        imgUser = findViewById(R.id.user);
        txtName = findViewById(R.id.txtName);
        llAppbar.setClipToOutline(true);

        context = this;
        setSupportActionBar(toolbar);
        toolbar.setTitle("YOGA");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        replaceFragment(new YogaDashboardFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int menuItemId =  item.getItemId();
                if (menuItemId ==  R.id.yoga) {
                    toolbar.setTitle("Yoga");
                    replaceFragment(new YogaDashboardFragment());
                    appBarLayout.setBackgroundColor(Color.parseColor("#e44e84"));
                    llAppbar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e44e84")));
                    bottomNavigationView.setBackgroundColor(Color.parseColor("#e44e84"));
                    toolbar.setBackgroundColor(Color.parseColor("#e44e84"));
                    etSearch.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f173a0")));
                    etSearch.setVisibility(View.VISIBLE);
                    imgUser.setVisibility(View.GONE);
                    txtName.setVisibility(View.GONE);
                    return true;
                }
                else if (menuItemId ==  R.id.meditation) {
                    replaceFragment(new MeditionFragment(meditationModel));
                    toolbar.setTitle("Meditation");
                    appBarLayout.setBackgroundColor(Color.parseColor("#6363af"));
                    llAppbar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6363af")));
                    bottomNavigationView.setBackgroundColor(Color.parseColor("#6363af"));
                    toolbar.setBackgroundColor(Color.parseColor("#6363af"));
                    etSearch.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7575ce")));
                    etSearch.setVisibility(View.VISIBLE);
                    imgUser.setVisibility(View.GONE);
                    txtName.setVisibility(View.GONE);
                    return true;
                }
                else if (menuItemId ==  R.id.diet) {
                    replaceFragment(new DietFragment(dietModel));
                    toolbar.setTitle("Diet");
                    appBarLayout.setBackgroundColor(Color.parseColor("#71d1c6"));
                    llAppbar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#71d1c6")));
                    bottomNavigationView.setBackgroundColor(Color.parseColor("#71d1c6"));
                    toolbar.setBackgroundColor(Color.parseColor("#71d1c6"));
                    etSearch.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4be9d8")));
                    etSearch.setVisibility(View.VISIBLE);
                    imgUser.setVisibility(View.GONE);
                    txtName.setVisibility(View.GONE);
                    return true;
                }
                else if (menuItemId ==  R.id.profile) {
                    replaceFragment(new ProfileFragment(save));
                    toolbar.setTitle("Profile");
                    appBarLayout.setBackgroundColor(Color.parseColor("#fbbc05"));
                    llAppbar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fbbc05")));
                    bottomNavigationView.setBackgroundColor(Color.parseColor("#fbbc05"));
                    toolbar.setBackgroundColor(Color.parseColor("#fbbc05"));
                    etSearch.setVisibility(View.GONE);
                    imgUser.setVisibility(View.VISIBLE);
                    txtName.setVisibility(View.VISIBLE);
                    showAllUserData();
                    return true;
                }
                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {

        replaceFragment(yogaDashboardFragment);

        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        this.fragment = fragment;
        fName = fragment.getClass().getSimpleName();
        fm = getSupportFragmentManager();
        FragmentTransaction fTransaction = fm.beginTransaction();
        fTransaction.replace(R.id.frame, fragment);
        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        fTransaction.addToBackStack(null);
        fTransaction.commit();

//        if (drawer != null) {
//            drawer.closeDrawer(GravityCompat.START);
//        }

    }
    public void showAllUserData(){
        Intent intent = getIntent();
        String usernameUser = intent.getStringExtra("userName");
        txtName.setText(usernameUser);

    }

}