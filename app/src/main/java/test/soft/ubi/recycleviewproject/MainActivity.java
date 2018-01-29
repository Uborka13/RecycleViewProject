package test.soft.ubi.recycleviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String ANIMATION = "ANIMATION";

    @BindView(R.id.list_from_top)
    Button buttonListFromTop;

    @BindView(R.id.list_from_right)
    Button buttonListFromRight;

    @BindView(R.id.list_from_bottom)
    Button buttonListFromBottom;


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
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ANIMATION, R.anim.item_animation_from_bottom);
        startListActivity(intent);
    }
}
