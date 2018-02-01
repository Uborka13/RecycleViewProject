package test.soft.ubi.recycleviewproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.soft.ubi.recycleviewproject.R;

public class ListFragment extends Fragment {


    public static final String ANIMATION = "animation";

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

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

}
