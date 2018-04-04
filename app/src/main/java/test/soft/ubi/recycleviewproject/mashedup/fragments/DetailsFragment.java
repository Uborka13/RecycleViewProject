package test.soft.ubi.recycleviewproject.mashedup.fragments;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.enums.Icons;
import test.soft.ubi.recycleviewproject.mashedup.items.SimpleItem;
import test.soft.ubi.recycleviewproject.mashedup.viewmodels.SimpleViewModel;

public class DetailsFragment extends Fragment {

    public static final String LOGO = "LOGO";
    public static final String NAME = "NAME";
    private static final String TAG = "TAG";
    @BindView(R.id.simple_image)
    ImageView imageView;

    private Presenter presenter;
    private SimpleViewModel simpleViewModel;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(SimpleItem item) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(LOGO, item.getIcon().getIcon());
        args.putString(NAME, item.getIcon().getName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void updateViewModelSelected(SimpleItem item) {
        simpleViewModel.select(item);
        simpleViewModel.getSelected().observe(this, liveItem -> updateIcon());
    }

    public void updateIcon() {
        imageView.setImageResource(simpleViewModel.getSelected().getValue().getIcon().getIcon());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        presenter.setIsClickable(true);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.setIsClickable(false);
    }

    @OnClick(R.id.change_icon_onsite)
    public void onOnsiteIconChangePressed() {
        presenter.changeItemIcon(simpleViewModel.getSelected().getValue(), Icons.ONSITE);
        simpleViewModel.select(presenter.getViewModelSelected().getValue());
        updateViewModelSelected(simpleViewModel.getSelected().getValue());
    }

    @OnClick(R.id.change_icon_offsite)
    public void onOffsiteIconChangePressed() {
        presenter.changeItemIcon(simpleViewModel.getSelected().getValue(), Icons.OFFSITE);
        simpleViewModel.select(presenter.getViewModelSelected().getValue());
        updateViewModelSelected(simpleViewModel.getSelected().getValue());
    }

    @OnClick(R.id.change_icon_train)
    public void onTrainIconChangePressed() {
        presenter.changeItemIcon(simpleViewModel.getSelected().getValue(), Icons.TRAIN_GRAY);
        simpleViewModel.select(presenter.getViewModelSelected().getValue());
        updateViewModelSelected(simpleViewModel.getSelected().getValue());
    }

    @OnClick(R.id.change_icon_ticket)
    public void onTicketIconChangePressed() {
        presenter.changeItemIcon(simpleViewModel.getSelected().getValue(),Icons.TICKET);
        simpleViewModel.select(presenter.getViewModelSelected().getValue());
        updateViewModelSelected(simpleViewModel.getSelected().getValue());
    }

    public interface Presenter {

        void setItem(SimpleItem item);

        SimpleViewModel getViewModel();

        void changeItemIcon(SimpleItem item, Icons icons);

        LiveData<SimpleItem> getViewModelSelected();

        void setIsClickable(boolean clickable);
    }
}
