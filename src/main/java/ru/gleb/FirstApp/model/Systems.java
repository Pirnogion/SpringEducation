package ru.gleb.FirstApp.model;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Resource System");

    private final String description;

    Systems(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
