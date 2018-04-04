package test.soft.ubi.recycleviewproject.mashedup.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.enums.Icons;
import test.soft.ubi.recycleviewproject.mashedup.items.SimpleItem;
import test.soft.ubi.recycleviewproject.mashedup.transitions.DetailsTransition;
import test.soft.ubi.recycleviewproject.mashedup.viewmodels.SimpleViewModel;

public class ListFragment extends Fragment implements DetailsFragment.Presenter {

    private static final String ANIMATION = "animation";
    @BindView(R.id.simple_recyclerview)
    RecyclerView recyclerView;

    private Presenter presenter;
    private SimpleViewModel simpleViewModel;

    public ListFragment() {
    }


    public static ListFragment newInstance(int animation) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ANIMATION, animation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        simpleViewModel = ViewModelProviders.of(getActivity()).get(SimpleViewModel.class);
        setUpRecycleView();
        update();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = (Presenter) context;
        simpleViewModel = presenter.getViewModel();
    }

    private void setAnimation(int animationInt) {
        final Context context = recyclerView.getContext();
        switch (animationInt) {
            case R.anim.item_animation_fall_down:
                recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
                break;
            case R.anim.item_animation_from_right:
                recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right));
                break;
            case R.anim.item_animation_from_bottom:
                recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom));
                break;
            default:
                recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
                break;
        }
    }

    public void setUpRecycleView() {
        if (getArguments() != null) {
            setAnimation(getArguments().getInt(ANIMATION));
        }
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void update() {
        ItemAdapter itemAdapter = new ItemAdapter();
        FastAdapter<SimpleItem> fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);
        itemAdapter.add(simpleViewModel.getItems().getValue());
        fastAdapter.withSelectable(true);

        fastAdapter.withOnClickListener((v, adapter, item, position) -> {
            simpleViewModel.select(item);
//            startNewFragment(item);
            return true;
        });
    }

    public void startNewFragment(SimpleItem item) {
        DetailsFragment fragment = DetailsFragment.newInstance(item);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
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

    @Override
    public void setItem(SimpleItem item) {

    }

    @Override
    public SimpleViewModel getViewModel() {
        return null;
    }

    @Override
    public void changeItemIcon(SimpleItem item, Icons icons) {

    }

    @Override
    public LiveData<SimpleItem> getViewModelSelected() {
        return null;
    }

    @Override
    public void setIsClickable(boolean clickable) {

    }

    public interface Presenter {

        void setItem(SimpleItem item);

        SimpleViewModel getViewModel();

        void changeItemIcon(SimpleItem item, Icons icons);

        LiveData<SimpleItem> getViewModelSelected();

        void setIsClickable(boolean clickable);
    }

}
