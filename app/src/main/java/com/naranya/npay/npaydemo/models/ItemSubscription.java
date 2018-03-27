package com.naranya.npay.npaydemo.models;

/**
 * Company name: Naranya Corp,
 * Company email: npaydevs@naranya.com
 */
public class ItemSubscription {
    private String title;
    private String description;
    private String idService;
    private String country;

    public ItemSubscription() {
    }

    public ItemSubscription(String title, String description, String idService, String country) {
        this.title = title;
        this.description = description;
        this.idService = idService;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
