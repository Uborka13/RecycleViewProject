package test.soft.ubi.recycleviewproject.mashedup.items;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.soft.ubi.recycleviewproject.R;
import test.soft.ubi.recycleviewproject.mashedup.enums.Icons;

/**
 * Created by David on 2018. 01. 30..
 */

public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> {
    private StringHolder name;
    private int iconResource;
    private ImageView imageView;
    private Icons icons;

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_simple_itemview;
    }

    public SimpleItem setIcon(Icons icons) {
        this.icons = icons;
        withName(icons.getName());
        withLogo(icons.getIcon());
        return this;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    public SimpleItem withName(String name) {
        this.name = new StringHolder(name);
        return this;
    }

    public SimpleItem withName(@StringRes int nameRes) {
        this.name = new StringHolder(nameRes);
        return this;
    }

    public SimpleItem withLogo(int iconResource) {
        this.iconResource = iconResource;
        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Icons getIcon() {
        return icons;
    }

    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem> implements View.OnClickListener {
        @BindView(R.id.simple_text)
        public TextView name;
        @BindView(R.id.simple_image)
        public ImageView image;
        @BindView(R.id.simple_description)
        public TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(@NonNull SimpleItem item, List<Object> payloads) {
            StringHolder.applyTo(item.name, name);
            image.setImageResource(item.iconResource);
            item.imageView = image;
        }

        @Override
        public void unbindView(SimpleItem item) {
            name.setText(null);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
