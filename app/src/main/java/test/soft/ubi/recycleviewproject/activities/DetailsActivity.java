package test.soft.ubi.recycleviewproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.simple_image)
    ImageView simpleImage;
    @BindView(R.id.details_activity)
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        supportPostponeEnterTransition();
        if (getIntent().getExtras() != null) {
            int iconRes = getIntent().getExtras().getInt(TAG);
            simpleImage.setImageResource(iconRes);
            Picasso.with(this)
                    .load(iconRes)
                    .into(simpleImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }


    }

}