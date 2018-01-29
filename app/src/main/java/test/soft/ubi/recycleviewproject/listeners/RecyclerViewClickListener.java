package test.soft.ubi.recycleviewproject.listeners;

import android.view.View;

/**
 * Created by David on 2018. 01. 29..
 */

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
