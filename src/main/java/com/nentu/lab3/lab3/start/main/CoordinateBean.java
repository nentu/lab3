package com.nentu.lab3.lab3.start.main;

import com.nentu.lab3.lab3.start.storage.db.entity.CoordinateEntity;
import com.nentu.lab3.lab3.start.utils.ValidationDataStorage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CoordinateBean {
    private Float x = Float.NEGATIVE_INFINITY;
    private Float y = Float.NEGATIVE_INFINITY;
    private Long id = 1L;
    private boolean success = true;
    private Float r = 1f;

    public CoordinateBean() {
    }

    public CoordinateBean(Float x, Float y, Float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    private final List<RCoordinate> rList = new ArrayList<>() {{
        for (Float i : (new ValidationDataStorage()).getRValues()) {
            add(
                    new RCoordinate((newValue) -> {
                        for (var j : rList) {
                            j.checked = false;
                        }
                        if (newValue > 0)
                            r = newValue;
                    },
                            i, i.equals(r)));
        }

    }};

    public static CoordinateBean fromEntity(CoordinateEntity entity) {
        var t = new CoordinateBean();
        t.id = entity.getId();
        t.x = entity.getX();
        t.y = entity.getY();
        t.r = entity.getR();
        t.success = entity.isSuccess();
        return t;
    }

    public CoordinateEntity getEntity() {
        return new CoordinateEntity(x, y, r, success);
    }

    public List<RCoordinate> getrList() {
        return rList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getX() {
        return x.equals(Float.NEGATIVE_INFINITY) ? "_" : String.valueOf(x);
    }

    public void setX(Float x) {
        this.x = x;
    }

    public String getY() {
        return y.equals(Float.NEGATIVE_INFINITY) ? "" : String.valueOf(y);
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(Float r) {
        this.r = r;
        for (var i : rList)
            i.checked = i.getValue() == r;
    }

    public Float getR() {
        return r;
    }


}
