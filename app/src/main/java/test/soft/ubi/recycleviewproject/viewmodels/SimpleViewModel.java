package test.soft.ubi.recycleviewproject.viewmodels;

import android.support.annotation.NonNull;

import test.soft.ubi.recycleviewproject.enums.Icons;

/**
 * Created by David on 2018. 01. 29..
 */

public class SimpleViewModel {
    private Icons icon;

    public SimpleViewModel(@NonNull final Icons icon) {
        setIcon(icon);
    }

    @NonNull
    public Icons getIcon() {
        return icon;
    }

    public void setIcon(@NonNull final Icons icon) {
        this.icon = icon;
    }
}
