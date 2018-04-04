package test.soft.ubi.recycleviewproject.recycleviewsample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.recycleviewsample.item.SampleItem;

public class ListHolderActivity extends AppCompatActivity {

    public static final String SAMPLE = "Sample";

    @BindView(R.id.sample_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_holder);
        ButterKnife.bind(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        ItemAdapter itemAdapter = new ItemAdapter();
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);
        itemAdapter.add(generateSampleItems());
        fastAdapter.withSelectable(true);
        fastAdapter.withOnClickListener(new OnClickListener() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter adapter, IItem item, int position) {
                // TODO Majd csináljon valamit
                return false;
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<SampleItem> generateSampleItems() {
        List<SampleItem> generatedList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            generatedList.add(new SampleItem().withId("1").withName(SAMPLE).withCost("1000"));
            generatedList.add(new SampleItem().withId("2").withName(SAMPLE).withCost("2000"));
            generatedList.add(new SampleItem().withId("3").withName(SAMPLE));
            generatedList.add(new SampleItem().withId("4").withName(SAMPLE).withCost("3000"));
            generatedList.add(new SampleItem().withId("5").withName(SAMPLE).withCost("1000"));
            generatedList.add(new SampleItem().withId("6").withName(SAMPLE));
            generatedList.add(new SampleItem().withId("7").withName(SAMPLE).withCost("1500"));
            generatedList.add(new SampleItem().withId("8").withName(SAMPLE).withCost("100"));
            generatedList.add(new SampleItem().withId("9").withName(SAMPLE));
            generatedList.add(new SampleItem().withId("10").withName(SAMPLE).withCost("1430"));
            generatedList.add(new SampleItem().withId("11").withName(SAMPLE).withCost("15600"));
            generatedList.add(new SampleItem().withId("12").withName(SAMPLE));
        }
        return generatedList;
    }


}
