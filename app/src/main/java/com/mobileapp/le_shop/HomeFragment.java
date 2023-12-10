package com.mobileapp.le_shop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;
import com.mobileapp.le_shop.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        @NonNull FragmentHomeBinding binding =
                FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        LinearLayout featured = binding.linearLayoutHome1;

        // Initialize Ad scroller
        ImageSlider adSlider = binding.adSlider;
        ArrayList<SlideModel> adList = new ArrayList<SlideModel>();


        adList.add(new SlideModel(R.drawable.ad_c, ScaleTypes.FIT));
        adList.add(new SlideModel(R.drawable.ad_b,ScaleTypes.FIT));
        adList.add(new SlideModel(R.drawable.ad1,ScaleTypes.FIT));
        adSlider.setImageList(adList);

        DatabaseAdapter adapter = new DatabaseAdapter(view.getContext());

        try{
            adapter.createDatabase();
            //db.createDataBase();
        } catch(IOException mIOException) {
            Log.d("Error", mIOException.getMessage());
        }

        adapter.openDatabase();

        try {
            Populate.populateViewWithFeaturedItems(featured, binding.getRoot().getContext(),
                    R.id.action_homeFragment_to_itemFragment);
        } catch (IOException e){
            Log.d("HOME_DEBUG", "Failed to feature populate");
        }


        return view;
    }
}