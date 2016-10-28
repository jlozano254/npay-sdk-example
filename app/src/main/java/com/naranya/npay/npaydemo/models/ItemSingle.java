package com.naranya.npay.npaydemo.models;

/**
 * Created by Anselmo Hernández Bazaldúa. on 8/10/16.
 * ----------------------------------------------------------
 * Additional Information
 * ----------------------------------------------------------
 * Company name: Naranya Corp,
 * Company email: anselmo.hernandez@naranya.com,
 * Personal email: chemo.baza@gmail.com,
 * Phone: +520448119163771,
 * Skype: chemo.baza,
 * ----------------------------------------------------------
 * Happy Coding :)
 */
public class ItemSingle {
    private String name;
    private String description;
    private String sku;
    private int type;

    public ItemSingle() {}

    public ItemSingle(String name, String description, String sku, int type) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
