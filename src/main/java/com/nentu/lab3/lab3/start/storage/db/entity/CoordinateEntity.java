package com.nentu.lab3.lab3.start.storage.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coordinate")
public class CoordinateEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Float x;
    private Float y;
    private Float r;
    private Boolean success;

    public CoordinateEntity() {
    }

    public CoordinateEntity(Float x, Float y, Float r, Boolean success) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
