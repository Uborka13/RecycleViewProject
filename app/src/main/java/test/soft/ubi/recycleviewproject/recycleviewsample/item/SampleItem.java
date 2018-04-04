package test.soft.ubi.recycleviewproject.recycleviewsample.item;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;

import static com.mikepenz.materialize.holder.StringHolder.applyTo;
import static com.mikepenz.materialize.holder.StringHolder.applyToOrHide;

public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {
    private StringHolder id;
    private StringHolder name;
    private StringHolder cost;

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.fast_adapter_sample_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.sample_item_list_row;
    }

    public SampleItem withId(String id) {
        this.id = new StringHolder(id);
        return this;
    }

    public SampleItem withName(String name) {
        this.name = new StringHolder(name);
        return this;
    }

    public SampleItem withCost(String cost) {
        this.cost = new StringHolder(cost);
        return this;
    }

    protected static class ViewHolder extends FastAdapter.ViewHolder<SampleItem> {

        @BindView(R.id.sample_item_id)
        TextView sampleItemId;
        @BindView(R.id.sample_item_name)
        TextView sampleItemName;
        @BindView(R.id.sample_item_cost)
        TextView sampleItemCost;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(@NonNull SampleItem item, @NonNull List<Object> payloads) {
            applyTo(item.id, sampleItemId);
            applyTo(item.name, sampleItemName);
            applyToOrHide(item.cost, sampleItemCost);
        }

        @Override
        public void unbindView(@NonNull SampleItem item) {
            sampleItemId.setText(null);
            sampleItemName.setText(null);
            sampleItemCost.setText(null);
        }
    }
}
