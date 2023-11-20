package com.nentu.lab3.lab3.start.main;

import com.nentu.lab3.lab3.start.utils.CoordinateUtils;
import com.nentu.lab3.lab3.start.utils.ValidationResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CoordinateManagerBean {
    public CoordinateManagerBean() {
    }

    public RCoordinate getRCoordinate(CoordinateBean coordinateBean, float value) {
        for (var i : coordinateBean.getrList()) {
            if (i.getValue() == value)
                return i;
        }
        return null;
    }

    public ValidationResponse validateX(CoordinateBean bean) {
        if (bean.getX().equals("_"))
            return new ValidationResponse(false, "X must be set");
        return ValidationResponse.successResp;
    }

    public boolean isInvalid(CoordinateBean bean) {
        return bean.getX().equals("_") || bean.getY().isEmpty();
    }

    public void check(CoordinateBean bean) {
        bean.setSuccess(
                CoordinateUtils.check(
                        Float.parseFloat(bean.getX()),
                        Float.parseFloat(bean.getY()),
                        bean.getR()
                )
        );
    }
}
