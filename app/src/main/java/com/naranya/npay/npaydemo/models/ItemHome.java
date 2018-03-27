package com.naranya.npay.npaydemo.models;

/**
 * Company name: Naranya Corp,
 * Company email: npaydevs@naranya.com
 */
public class ItemHome {
    private String title;
    private int iconResource;

    public ItemHome() {
    }

    public ItemHome(String title, int iconResource) {
        this.title = title;
        this.iconResource = iconResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
