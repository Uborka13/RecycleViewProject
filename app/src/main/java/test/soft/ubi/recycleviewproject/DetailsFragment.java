package test.soft.ubi.recycleviewproject;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.items.SimpleItem;

public class DetailsFragment extends Fragment {

    private static final String TAG = "TAG";
    public static final String LOGO = "LOGO";
    public static final String NAME = "NAME";
    @BindView(R.id.simple_image)
    ImageView imageView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        int res = 0;
        if (getArguments() != null) {
            res = getArguments().getInt(LOGO);
            imageView.setImageResource(res);
//            Picasso.with(getContext())
//                    .load(res)
//                    .noFade()
//                    .into(imageView, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            startPostponedEnterTransition();
//                        }
//
//                        @Override
//                        public void onError() {
//                            startPostponedEnterTransition();
//                        }
//                    });
        }
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
