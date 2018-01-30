package test.soft.ubi.recycleviewproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionValues;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.enums.Icons;
import test.soft.ubi.recycleviewproject.items.SimpleItem;
import test.soft.ubi.recycleviewproject.transitions.DetailsTransition;

public class ListActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    private static final String ANIMATION = "ANIMATION";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        update();
    }

    private List<SimpleItem> generateSimpleList() {
        List<SimpleItem> simpleViewModelList = new ArrayList<>();
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
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

    public void startNewActivity(SimpleItem item) {
        Picasso.with(this)
                .load(item.getIcon().getIcon())
                .into(item.getImageView());
        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
        intent.putExtra(TAG, item.getIcon().getIcon());

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(ListActivity.this,
                        item.getImageView(),
                        ViewCompat.getTransitionName(item.getImageView()));
        startActivity(intent, options.toBundle());
    }

    public void update() {
        recyclerView = findViewById(R.id.simple_recyclerview);
        if (getIntent().getExtras() != null) {
            setAnimation(recyclerView, getIntent().getExtras().getInt(ANIMATION));
        }
        ItemAdapter itemAdapter = new ItemAdapter();
        FastAdapter<SimpleItem> fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);
        recyclerView.getItemAnimator().setChangeDuration(0);
        itemAdapter.add(generateSimpleList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fastAdapter.withSelectable(true);
        fastAdapter.withOnClickListener(new OnClickListener<SimpleItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<SimpleItem> adapter, SimpleItem item, int position) {
//                startNewActivity(item);
                startNewFragment(item);
                return true;
            }
        });
    }

    public void fragmentOldColde(SimpleItem item) {
        Bundle args = new Bundle();
        args.putInt(TAG, item.getIcon().getIcon());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(ListActivity.this,
                        item.getImageView(),
                        ViewCompat.getTransitionName(item.getImageView()));

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addSharedElement(item.getImageView(), ViewCompat.getTransitionName(item.getImageView()));
//        transaction.replace(R.id.fragment_container, detailsFragment, detailsFragment.toString());
        transaction.addToBackStack(null);
        transaction.commit();
        recyclerView.setVisibility(View.GONE);
    }

    public void startNewFragment(SimpleItem item) {
        DetailsFragment fragment = DetailsFragment.newInstance(item);
        Slide slide = new Slide(Gravity.LEFT);
        Fade fade = new Fade();
        fade.setDuration(500);
        slide.setDuration(500);
        fragment.setEnterTransition(slide);
        fragment.setSharedElementEnterTransition(fade);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragment.setSharedElementEnterTransition(new DetailsTransition());
//        fragment.setEnterTransition(new Fade());
//        fragment.setExitTransition(new Fade());
//        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragmentTransaction.addSharedElement(item.getImageView(), "simple_fragment_transition");
        fragmentTransaction.replace(R.id.details, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}