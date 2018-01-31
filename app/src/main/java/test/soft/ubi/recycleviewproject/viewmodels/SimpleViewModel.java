package test.soft.ubi.recycleviewproject.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import test.soft.ubi.recycleviewproject.enums.Icons;
import test.soft.ubi.recycleviewproject.items.SimpleItem;

/**
 * Created by David on 2018. 01. 29..
 */

public class SimpleViewModel extends ViewModel {
    private final MutableLiveData<SimpleItem> selected = new MutableLiveData<SimpleItem>();
    private MutableLiveData<List<SimpleItem>> items;
    private Icons icon;

    public SimpleViewModel() {
    }

    public LiveData<List<SimpleItem>> getItems() {
        if (items == null) {
            items = new MutableLiveData<List<SimpleItem>>();
            generateSimpleList();
        }
        return items;
    }

    @NonNull
    public Icons getIcon() {
        return icon;
    }

    public void setIcon(@NonNull final Icons icon) {
        this.icon = icon;
    }

    public void select(SimpleItem simpleItem) {
        selected.setValue(simpleItem);
    }

    public LiveData<SimpleItem> getSelected() {
        return selected;
    }

    private void generateSimpleList() {
        List<SimpleItem> simpleViewModelList = new ArrayList<>();
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.OFFSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.ONSITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TICKET));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_GRAY));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.TRAIN_WHITE));
        simpleViewModelList.add(new SimpleItem().setIcon(Icons.VIGNETTE));
        items.setValue(simpleViewModelList);
    }
}
