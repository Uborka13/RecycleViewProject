package test.soft.ubi.recycleviewproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import test.soft.ubi.recycleviewproject.adapters.SimpleAdapter;
import test.soft.ubi.recycleviewproject.enums.Icons;
import test.soft.ubi.recycleviewproject.listeners.RecyclerViewClickListener;
import test.soft.ubi.recycleviewproject.listeners.RecyclerViewTouchListener;
import test.soft.ubi.recycleviewproject.viewmodels.SimpleViewModel;

public class ListActivity extends AppCompatActivity {

    private static final String ANIMATION = "ANIMATION";
    private List<SimpleViewModel> simpleViewModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        SimpleAdapter adapter = new SimpleAdapter(generateSimpleList());
        RecyclerView recyclerView = findViewById(R.id.simple_recyclerview);
        if (getIntent().getExtras() != null) {
            setAnimation(recyclerView, getIntent().getExtras().getInt(ANIMATION));
        }
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private List<SimpleViewModel> generateSimpleList() {
        simpleViewModelList = new ArrayList<>();
        simpleViewModelList.add(new SimpleViewModel(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleViewModel(Icons.ONSITE));
        simpleViewModelList.add(new SimpleViewModel(Icons.TICKET));
        simpleViewModelList.add(new SimpleViewModel(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleViewModel(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleViewModel(Icons.VIGNETTE));
        return simpleViewModelList;
    }

    private void setAnimation(RecyclerView recView, int animationInt) {
        final Context context = recView.getContext();
        switch (animationInt) {
            case R.anim.item_animation_fall_down:
                recView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
                break;
            case R.anim.item_animation_from_right:
                recView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right));
                break;
            case R.anim.item_animation_from_bottom:
                recView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom));
                break;
            default:
                recView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
                break;

        }
    }

    public void onItemClicked(int position) {
        
        Intent intent = new Intent(this, DetailsActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        imageView,
                        ViewCompat.getTransitionName(imageView));
        startActivity(intent, options.toBundle());
    }
}
