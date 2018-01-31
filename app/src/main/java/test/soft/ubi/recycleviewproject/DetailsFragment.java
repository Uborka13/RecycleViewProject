package test.soft.ubi.recycleviewproject;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.soft.ubi.recycleviewproject.items.SimpleItem;
import test.soft.ubi.recycleviewproject.viewmodels.SimpleViewModel;

public class DetailsFragment extends Fragment {

    public static final String LOGO = "LOGO";
    public static final String NAME = "NAME";
    private static final String TAG = "TAG";
    @BindView(R.id.simple_image)
    ImageView imageView;
    @BindView(R.id.change_icon)
    Button button;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = (Presenter) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simpleViewModel = ViewModelProviders.of(this).get(SimpleViewModel.class);

        simpleViewModel.getSelected().observe(this, item -> {
            updateIcon();
        });

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

    @OnClick(R.id.change_icon)
    public void onChangeIconPressed() {
        presenter.changeItemIcon(simpleViewModel.getSelected().getValue());
    }

    public interface Presenter {

        void setItem(SimpleItem item);

        void changeItemIcon(SimpleItem item);

        void setIsClickable(boolean clickable);
    }
}
