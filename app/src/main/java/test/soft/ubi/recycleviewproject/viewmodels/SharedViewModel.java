package test.soft.ubi.recycleviewproject.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import test.soft.ubi.recycleviewproject.items.SimpleItem;

/**
 * Created by David on 2018. 01. 31..
 */

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<SimpleItem> selected = new MutableLiveData<SimpleItem>();

    public void select(SimpleItem simpleItem) {
        selected.setValue(simpleItem);
    }


    public LiveData<SimpleItem> getSelected() {
        return selected;
    }
}
