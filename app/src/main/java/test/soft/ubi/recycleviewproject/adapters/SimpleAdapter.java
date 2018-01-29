package test.soft.ubi.recycleviewproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.viewholders.SimpleViewHolder;
import test.soft.ubi.recycleviewproject.viewmodels.SimpleViewModel;

/**
 * Created by David on 2018. 01. 29..
 */

public class SimpleAdapter extends RecyclerView.Adapter {
    List<SimpleViewModel> models;

    public SimpleAdapter(List<SimpleViewModel> models) {
        this.models = models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((SimpleViewHolder) holder).bindData(models.get(position));
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_simple_itemview;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
