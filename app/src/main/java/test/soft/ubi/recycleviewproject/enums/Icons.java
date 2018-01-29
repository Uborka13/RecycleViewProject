package test.soft.ubi.recycleviewproject.enums;

import test.soft.ubi.recycleviewproject.R;

/**
 * Created by David on 2018. 01. 29..
 */

public enum Icons {

    OFFSITE("Offsite", R.drawable.offsite_parking_list),
    ONSITE("Onsite", R.drawable.parking_list_icon),
    TICKET("Ticket", R.drawable.ticket_list_icon),
    TRAIN_WHITE("Train one", R.drawable.train_hexa_white),
    TRAIN_GRAY("Train two", R.drawable.train_list_icon),
    VIGNETTE("Vignette", R.drawable.vignette_list_icon);

    private String name;
    private int icon;

    Icons(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

}

