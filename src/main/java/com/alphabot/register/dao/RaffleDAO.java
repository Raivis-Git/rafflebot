package com.alphabot.register.dao;

public class RaffleDAO {
    String slug;
    String raffleName;

    public RaffleDAO(String slug, String raffleName) {
        this.slug = slug;
        this.raffleName = raffleName;
    }

    @Override
    public String toString() {
        return "RaffleDAO{" +
                "slug='" + slug + '\'' +
                ", raffleName='" + raffleName + '\'' +
                '}';
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRaffleName() {
        return raffleName;
    }

    public void setRaffleName(String raffleName) {
        this.raffleName = raffleName;
    }
}
