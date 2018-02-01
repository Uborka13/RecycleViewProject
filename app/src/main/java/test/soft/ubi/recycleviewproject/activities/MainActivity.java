package test.soft.ubi.recycleviewproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.fragments.DetailsFragment;
import test.soft.ubi.recycleviewproject.fragments.ListFragment;
import test.soft.ubi.recycleviewproject.transitions.DetailsTransition;

public class MainActivity extends AppCompatActivity {
    private static final String ANIMATION = "ANIMATION";

    @BindView(R.id.list_from_top)
    Button buttonListFromTop;

    @BindView(R.id.list_from_right)
    Button buttonListFromRight;

    @BindView(R.id.list_from_bottom)
    Button buttonListFromBottom;

    @BindView(R.id.details)
    FrameLayout detailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void startListActivity(Intent intent){
        startActivity(intent);
    }

    @OnClick(R.id.list_from_top)
    public void startTopAnimation() {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ANIMATION, R.anim.item_animation_fall_down);
        startListActivity(intent);
    }

    @OnClick(R.id.list_from_right)
    public void startRightAnimation() {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ANIMATION, R.anim.item_animation_from_right);
        startListActivity(intent);
    }

    @OnClick(R.id.list_from_bottom)
    public void startBottomAnimation() {
        startNewFragment(R.anim.item_animation_from_bottom);
    }

    private void startNewFragment(int animation) {
        ListFragment fragment = ListFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        fragment.setExitTransition(new Fade());
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragmentTransaction.replace(R.id.details, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
