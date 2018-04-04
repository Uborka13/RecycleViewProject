package test.soft.ubi.recycleviewproject.mashedup.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.viewmodels.SimpleViewModel;

/**
 * Created by David on 2018. 01. 29..
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.simple_text)
    public TextView simpleTextView;
    @BindView(R.id.simple_image)
    public ImageView simpleImageView;

    private SimpleViewModel viewModel;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final SimpleViewModel viewModel) {
        this.viewModel = viewModel;
        simpleTextView.setText(viewModel.getIcon().getName());
        simpleImageView.setImageResource(viewModel.getIcon().getIcon());
    }
}
