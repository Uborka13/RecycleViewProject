package test.soft.ubi.recycleviewproject.mashedup.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.viewholders.SimpleViewHolder;
import test.soft.ubi.recycleviewproject.mashedup.viewmodels.SimpleViewModel;

/**
 * Created by David on 2018. 01. 29..
 */

public class SimpleAdapter extends RecyclerView.Adapter {
    List<SimpleViewModel> models;
    private double mExpandedPosition = -1;
    private int previousExpandedPosition = -1;

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
        final boolean isExpanded = position == mExpandedPosition;
        holder.itemView.setActivated(isExpanded);

        if (isExpanded)
            previousExpandedPosition = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });
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
