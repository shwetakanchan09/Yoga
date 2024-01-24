package com.yoga.app.yoga;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.yoga.app.R;
import com.yoga.app.model.YogaModel;

public class YogaDashboardFragment extends Fragment {
    TabLayout tabLayout;
    Activity context;
    private YogaModel yogaModel;
    WomenFragment womenFragment = new WomenFragment(yogaModel);
    MenFragment menFragment = new MenFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_yoga, container, false);
        context = getActivity();

        tabLayout = view.findViewById(R.id.tabLayout);
        setFragment(womenFragment);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==0)
                    setFragment(womenFragment);
                else if (tab.getPosition()==1) {
                    setFragment(menFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();

    }

}