package test.soft.ubi.recycleviewproject.recycleviewsample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {
        public SampleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
