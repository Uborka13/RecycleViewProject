package test.soft.ubi.recycleviewproject.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.fragments.DetailsFragment;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.enums.Icons;
import test.soft.ubi.recycleviewproject.items.SimpleItem;
import test.soft.ubi.recycleviewproject.transitions.DetailsTransition;
import test.soft.ubi.recycleviewproject.viewmodels.SimpleViewModel;

public class ListActivity extends AppCompatActivity implements DetailsFragment.Presenter {

    public static final String TAG = "TAG";
    private static final String ANIMATION = "ANIMATION";
    @BindView(R.id.details)
    FrameLayout detailsLayout;
    private RecyclerView recyclerView;
    private SimpleItem item;
    private SimpleViewModel simpleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        simpleViewModel = ViewModelProviders.of(this).get(SimpleViewModel.class);
        setUpRecycleView();
        update();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    public void setUpRecycleView() {
        recyclerView = findViewById(R.id.simple_recyclerview);
        if (getIntent().getExtras() != null) {
            setAnimation(recyclerView, getIntent().getExtras().getInt(ANIMATION));
        }
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void update() {

        ItemAdapter itemAdapter = new ItemAdapter();
        FastAdapter<SimpleItem> fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);
        itemAdapter.add(simpleViewModel.getItems().getValue());
        fastAdapter.withSelectable(true);

        fastAdapter.withOnClickListener((v, adapter, item, position) -> {
            simpleViewModel.select(item);
//                startNewActivity(item);
            startNewFragment(item);
            return true;
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
        transaction.commit();
        recyclerView.setVisibility(View.GONE);
    }

    public void startNewFragment(SimpleItem item) {
        DetailsFragment fragment = DetailsFragment.newInstance(item);
//        Slide slide = new Slide(Gravity.LEFT);
//        Fade fade = new Fade();
//        fade.setDuration(500);
//        slide.setDuration(500);
//        fragment.setEnterTransition(slide);
//        fragment.setSharedElementEnterTransition(fade);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        fragment.setExitTransition(new Fade());
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragmentTransaction.addSharedElement(item.getImageView(), "simple_fragment_transition");
        fragmentTransaction.replace(R.id.details, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public SimpleItem getItem() {
        return simpleViewModel.getSelected().getValue();
    }

    @Override
    public void setItem(SimpleItem item) {
        simpleViewModel.select(item);
    }

    @Override
    public SimpleViewModel getViewModel() {
        return simpleViewModel;
    }



    @Override
    public void changeItemIcon(SimpleItem item, Icons icons) {
        simpleViewModel.select(item);
        simpleViewModel.getSelected().getValue().withLogo(icons.getIcon());

    }

    @Override
    public LiveData<SimpleItem> getViewModelSelected() {
        return simpleViewModel.getSelected();
    }

    @Override
    public void setIsClickable(boolean clickable) {
        detailsLayout.setClickable(clickable);
    }
}