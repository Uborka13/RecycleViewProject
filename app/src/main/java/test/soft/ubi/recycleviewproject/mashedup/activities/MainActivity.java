package test.soft.ubi.recycleviewproject.mashedup.activities;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.enums.Icons;
import test.soft.ubi.recycleviewproject.mashedup.fragments.ListFragment;
import test.soft.ubi.recycleviewproject.mashedup.items.SimpleItem;
import test.soft.ubi.recycleviewproject.mashedup.viewmodels.SimpleViewModel;
import test.soft.ubi.recycleviewproject.recycleviewsample.activity.ListHolderActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.Presenter {

    @BindView(R.id.list_from_top)
    Button buttonListFromTop;

    @BindView(R.id.list_from_right)
    Button buttonListFromRight;

    @BindView(R.id.list_from_bottom)
    Button buttonListFromBottom;

    @BindView(R.id.start_qr)
    Button startQr;

    @BindView(R.id.start_sample)
    Button startSample;

    @BindView(R.id.main_details)
    FrameLayout detailsLayout;

    @BindView(R.id.buttons_layout)
    RelativeLayout buttonsLayout;

    @BindView(R.id.main_activity_layout)
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showActivity();
    }

    private void startListActivity(Intent intent){
        startActivity(intent);
    }

    @OnClick(R.id.list_from_top)
    public void startTopAnimation() {
        startNewFragment(R.anim.item_animation_fall_down);
    }

    @OnClick(R.id.list_from_right)
    public void startRightAnimation() {
        startNewFragment(R.anim.item_animation_from_right);
    }

    @OnClick(R.id.list_from_bottom)
    public void startBottomAnimation() {
        startNewFragment(R.anim.item_animation_from_bottom);
    }

    @OnClick(R.id.start_qr)
    public void startQR() {
        Intent intent = new Intent(this, QRReaderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.start_sample)
    public void startSample() {
        Intent intent = new Intent(this, ListHolderActivity.class);
        startActivity(intent);
    }

    private void startNewFragment(int animation) {
        ListFragment fragment = ListFragment.newInstance(animation);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_details, fragment);
        fragmentTransaction.commit();
        hideActivity();
    }

    private void hideActivity() {
        buttonsLayout.setVisibility(View.GONE);
    }

    private void showActivity() {
        buttonsLayout.setVisibility(View.VISIBLE);
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
}
