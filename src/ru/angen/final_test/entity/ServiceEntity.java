package ru.angen.final_test.entity;

import java.util.Objects;

public class ServiceEntity {
    private int id;
    private String title;
    private String imagePath;
    private int cost;
    private int duration;
    private int discount;

    public ServiceEntity(int id,String title, String imagePath, int cost, int duration, int discount) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.cost = cost;
        this.duration = duration;
        this.discount = discount;
    }

    public ServiceEntity(String title, String imagePath, int cost, int duration, int discount) {
        this.id = -1;
        this.title = title;
        this.imagePath = imagePath;
        this.cost = cost;
        this.duration = duration;
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceEntity that = (ServiceEntity) o;
        return id == that.id && cost == that.cost && duration == that.duration && discount == that.discount && Objects.equals(title, that.title) && Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imagePath, cost, duration, discount);
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                ", discount=" + discount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
